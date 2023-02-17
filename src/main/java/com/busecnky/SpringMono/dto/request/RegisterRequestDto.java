package com.busecnky.SpringMono.dto.request;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RegisterRequestDto {

    String username;
    String password;
    String repassword;
    String email;
}
