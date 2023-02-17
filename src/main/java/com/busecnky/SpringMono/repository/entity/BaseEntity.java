package com.busecnky.SpringMono.repository.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;


import javax.persistence.MappedSuperclass;


@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@SuperBuilder
public class BaseEntity {

    boolean state;

    Long createdate;

    Long updatedate;

}
