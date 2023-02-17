package com.busecnky.SpringMono.mvccontroller;

import com.busecnky.SpringMono.dto.request.LoginRequestDto;
import com.busecnky.SpringMono.dto.request.RegisterRequestDto;
import com.busecnky.SpringMono.repository.entity.User;
import com.busecnky.SpringMono.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

import static com.busecnky.SpringMono.constants.EndPoints.*;

//RestContoller değil burda sadece Controller
@Controller
@RequestMapping(MVCUSER)
@RequiredArgsConstructor
public class UserMvcController {

    private final UserService userService;

    /**
     * http://localhost/mvc/user/login
     * MVC sayfalarına gelen isteklerde kullanıcıya bir HTML sayfası dönmeniz gerekmektedir.
     * bu nedenşe MVC yapısında tema ile birleştirilen model kullanıcıya ModelAndView üzerinden dönülür.
     * ModelAndView -> bir HTML sayfası ve model talep eder(model zorunlu değildir)
     * Bu bilgiler ile bir html sayfası oluşturarak kullanıcıya dönüş yapar.
     */
    @GetMapping(LOGIN)
    public ModelAndView login(){
        ModelAndView model = new ModelAndView();
        /**
         * templates içinde bulunan html sayfasının adıdır.
         */
        model.setViewName("login");
        model.addObject("title", "Giriş Sayfası");
        model.addObject("error", "");

        return model;
    }

    @PostMapping(LOGIN)
    public ModelAndView login(LoginRequestDto dto){
        ModelAndView model = new ModelAndView();
        Optional<User> user = userService.findOptionalByUsernameAndPassword(dto);
        /**
         * Kullanıcı adı ya da şifre hatalı ise, yok ise if durumu
         * varsa else durumu çalışacak
         */
        if(user.isEmpty()){
            model.setViewName("login");
            model.addObject("error", "Kullanıcı adı ya da şifre hatalıdır");
            return model;
        }else{
            return new ModelAndView("redirect:/v1/dev/urun/index");
        }
    }

    @GetMapping(REGISTER)
    public ModelAndView register(){
        ModelAndView model = new ModelAndView();
        model.setViewName("register");
        model.addObject("error", "");
        return model;
    }
    @PostMapping(REGISTER)
    public ModelAndView register(RegisterRequestDto dto){
        ModelAndView model = new ModelAndView();

        /**
         * Eğer kullanıcı mevcut ise uyarı bildirimi yap.
         * Değilse kaydet
         */
        if (userService.existsUserByUsername(dto.getUsername())){
            model.setViewName("register");
            model.addObject("error",
                    dto.getUsername() + "Kullanıcı adı daha önce başkası tarafından alınmıştır.");
        }else{
            userService.register(dto);
            return new ModelAndView("redirect:login");
        }

        return model;
    }

    @GetMapping(INDEX)
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }


}
