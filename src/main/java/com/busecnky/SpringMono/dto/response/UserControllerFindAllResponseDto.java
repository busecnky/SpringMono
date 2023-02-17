package com.busecnky.SpringMono.dto.response;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserControllerFindAllResponseDto {

    String username;
    String avatar;

    Long id;
}
