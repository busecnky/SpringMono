package com.busecnky.SpringMono.controller;

import com.busecnky.SpringMono.dto.request.LoginRequestDto;
import com.busecnky.SpringMono.dto.request.RegisterRequestDto;
import com.busecnky.SpringMono.dto.response.UserControllerFindAllResponseDto;
import com.busecnky.SpringMono.repository.entity.User;
import com.busecnky.SpringMono.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.busecnky.SpringMono.constants.EndPoints.*;

@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
public class UserController {

    //BURASI FINAL OLMAZSA REQUIREDARGSCONSTRUCTOR ÇALIŞMAZ BU PRIVATE FINAL OLMALI!!
    private final UserService userService;

    /**
     * http://localhost/v1/dev/user/register?username=buse&password=123&email=b@b.com
     * @param dto
     * @return
     */
    @GetMapping(REGISTER)
    public ResponseEntity<Boolean> register(RegisterRequestDto dto){
        return ResponseEntity.ok( userService.register(dto));

        //Bu kısım service e haber vermeli o yüzden service katmamına gidiyoruz.
    }

    public ResponseEntity<Boolean> doLogin(LoginRequestDto dto){
        return ResponseEntity.ok(true);
    }

    /**
     * http://localhost/v1/dev/user/findall
     * @return
     */
     @GetMapping(FINDALL)
    public ResponseEntity<List<UserControllerFindAllResponseDto>> findAll(){
        return ResponseEntity.ok(userService.findAllResponseDtos());
    }

}
