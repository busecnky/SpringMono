package com.busecnky.SpringMono.mvccontroller.models;

import com.busecnky.SpringMono.repository.entity.Urun;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Ürün sayfasına yönlendirilecek ne varsa buraya yazıyoruz? 17şubat 2:10
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UrunIndexModel {

    String title;
    List<String> menuListesi;
    List<Urun> urunListesi;
}
