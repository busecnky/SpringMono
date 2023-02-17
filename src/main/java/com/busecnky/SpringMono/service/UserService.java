package com.busecnky.SpringMono.service;

import com.busecnky.SpringMono.dto.request.LoginRequestDto;
import com.busecnky.SpringMono.dto.request.RegisterRequestDto;
import com.busecnky.SpringMono.dto.response.UserControllerFindAllResponseDto;
import com.busecnky.SpringMono.mapper.IUserMapper;
import com.busecnky.SpringMono.repository.IUserRepository;
import com.busecnky.SpringMono.repository.entity.User;
import com.busecnky.SpringMono.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService extends ServiceManager<User,Long> {

    private final IUserRepository repository;

    public UserService(IUserRepository repository){
        super(repository);
        this.repository = repository;
    }

    public Boolean register(RegisterRequestDto dto){
        User user = User.builder()
                .password(dto.getPassword())
                .username(dto.getUsername())
                .email(dto.getEmail())
                .build();
        save(user);
        return true;
    }

    public Boolean registerMapper(RegisterRequestDto dto) {
        save(IUserMapper.INSTANCE.toUser(dto));
        return true;
    }



    public List<UserControllerFindAllResponseDto> findAllResponseDtos(){
        /**
         * Boş list oluşturduk.
         */
        List<UserControllerFindAllResponseDto> result = new ArrayList<>();
        /**
         * Tüm kullanıcıların listesini çektik
         */
        findAll().forEach(x->{
            /**
             * Dto nesnesini oluşturmak için her kullanıcının bilgilerini alarak builder ile
             * dto nesnesi yarattık ve bu nesneyi listemize ekledik.
             */
          /*  result.add( UserControllerFindAllResponseDto.builder()
                    .avatar(x.getAvatar())
                    .username(x.getUsername())
                    .id(x.getId())
                    .build());*/
            result.add(IUserMapper.INSTANCE.userControllerFindAllResponseDtoFromUser(x));
        });
       return result;
    }

    public Boolean existsUserByUsername(String username){
        return repository.existsUserByUsername(username);
    }

    public Optional<User> findOptionalByUsernameAndPassword(LoginRequestDto dto){
        return repository.findOptionalByUsernameAndPassword(dto.getUsername(), dto.getPassword());
    }

}
