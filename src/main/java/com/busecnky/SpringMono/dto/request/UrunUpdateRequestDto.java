package com.busecnky.SpringMono.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UrunUpdateRequestDto {

    Long id;
    String ad;
    Double fiyat;
    String kdv;
    Integer barkod;

}
