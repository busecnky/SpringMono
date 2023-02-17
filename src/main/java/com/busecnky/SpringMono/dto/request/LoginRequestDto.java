package com.busecnky.SpringMono.dto.request;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginRequestDto {

    String username;
    String password;
}
