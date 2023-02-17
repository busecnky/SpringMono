package com.busecnky.SpringMono.dto.request;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UrunSaveRequestDto {

    Long id;
    String ad;
    String fiyat;
    String kdv;
    String barkod;


}
