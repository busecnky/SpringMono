package com.busecnky.SpringMono.mvccontroller;

import com.busecnky.SpringMono.dto.request.UrunSaveRequestDto;
import com.busecnky.SpringMono.dto.request.UrunUpdateRequestDto;
import com.busecnky.SpringMono.mvccontroller.models.UrunIndexModel;
import com.busecnky.SpringMono.service.UrunService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

import static com.busecnky.SpringMono.constants.EndPoints.*;

@Controller
@RequestMapping(URUN)
@RequiredArgsConstructor
public class UrunMvcController {

    private final UrunService urunService;
    @GetMapping(INDEX)
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("urun");
        UrunIndexModel model = new UrunIndexModel();
        model.setTitle("Ürün İşlemleri");
        model.setMenuListesi(Arrays.asList("Ürün Ekleme", "Ürün Listesi", "Firma Ekleme", "Stok Listesi"));
        model.setUrunListesi(urunService.findAll());
        modelAndView.addObject("model", model);
        return modelAndView;
    }
    @PostMapping (SAVE)
    public ModelAndView save(UrunSaveRequestDto dto){
        /**
         * NOT!!!
         * sunucuya iletilen dosyalar sunucunun kendisinde tutulabilir. Tüm
         * resim ve dosyaarl static klasöründe kulanılabilir ve ulaşılabilir.
         * Çok tavsiye edilen bir yöntem değildir. Bunun yerine
         * bulut sistemlerde yada kendi fileserver sisteminizde bu dosyaları
         * barındırmanız önerilir.
         */
        //Files.copy(dto.getProfileimg().getInputStream(), Path.of("d:\\resim.jpg"));
        urunService.save(dto);
        return new ModelAndView("redirect:index");
    }

    @PostMapping (DELETE)
    public ModelAndView delete(Long id){
        System.out.println("Silme işlemi çalıştı silinen id : "+ id);
        try{
            if(id != null && id>0){
                urunService.deleteById(id);
            }
        }catch (Exception exception){
            System.out.println("Hata oluştu..." + exception.toString());
        }


        return new ModelAndView("redirect:index");
    }

    @PostMapping (UPDATE)
    public ModelAndView update(UrunUpdateRequestDto dto){
        urunService.update(dto);
        return new ModelAndView("redirect:index");
    }
}
