package com.busecnky.SpringMono.controller;


import com.busecnky.SpringMono.repository.IMusteriRepository;
import com.busecnky.SpringMono.repository.entity.Musteri;
import com.busecnky.SpringMono.service.MusteriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
/**
 * Uygulama ayağa kalktığı andan itibaren bir ip ve port üzerinde yayın yaoar.
 * localhost:80 -> localhost
 * Uygulamamızın alt kırılımlarına yön vermek ve belli Class lara yönlendirme yapmak için
 * Mapping yaparız. Bunun için @RequestMapping anotasonu kullanılır.
 * http://localhost/musteri   ----> bu kısmı burası yakalar
 */
@RequestMapping("/musteri")
public class MusteriController {

    @Autowired
    MusteriService musteriService;

    /**
     *  http://localhost/musteri/save ---> bu kısmı da burası yakalar
     *  Get -> bir sayfaya erişme ve ondan bilhi alma isteğidir.
     *  Özel bir gereksinimi yoktur.
     *  Browser ların tümü GET isteği gönderir.
     *
     * @param ad
     * @param adres
     * @param telefon
     */

    @GetMapping("/save")
    public void save(String ad, String adres, String telefon){
        Musteri musteri = Musteri.builder()
                .ad(ad)
                .adres(adres)
                .telefon(telefon)
                .build();
        musteriService.save(musteri);
    }

    /**
     * ResponseEntity -> Pojo JSonObject olarak Return type kullan
     * localhost/musteri/findall
     * @return
     */

    @GetMapping("/findall")
    public ResponseEntity<List<Musteri>> findAll(){
        return ResponseEntity.ok(musteriService.findAll());
    }

    @GetMapping("/findbyad")
    public ResponseEntity<List<Musteri>> findByAd(String ad,String adres){
        if(adres==null)
            return ResponseEntity.ok(musteriService.adinaGoreGetir(ad));
        return ResponseEntity.ok(musteriService.adVeAdreseGoreGetir(ad,adres));
    }

    /**
     * select * from tblmusteri where ad like '%ad' - 'a%'
     * @param ad
     * @return
     */

    @GetMapping("/findallbyadlike")
    public ResponseEntity<List<Musteri>> findAllByStartwithAd(String ad){
        return ResponseEntity.ok(musteriService.findAllByAdLike(ad+"%"));
    }
}
