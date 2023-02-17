package com.busecnky.SpringMono.service;

import com.busecnky.SpringMono.repository.IMusteriRepository;
import com.busecnky.SpringMono.repository.entity.Musteri;
import com.busecnky.SpringMono.utility.ServiceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MusteriService extends ServiceManager<Musteri,Long> {



    /**
     * * Repository nin eniekte edilme yöntemleni
     * 1- Field Injection -> @Autowired
    */
    //@Autowired
    //private MusteriRepository repository;
    /**
     * 2- Constructor Indeciten
     *
     */

    private final IMusteriRepository repository;

    public MusteriService(IMusteriRepository repository){
        super(repository);
        this.repository = repository;
    }


    public void delete(Musteri musteri){
        repository.delete(musteri);
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public List<Musteri> findAll(){
        return repository.findAll();
    }

    public Optional<Musteri> findById(Long id){
        return repository.findById(id);
    }

    public boolean isExist(Long id){
        return repository.existsById(id);
    }

    public List<Musteri> adinaGoreGetir(String musteriadi){
        return repository.findByAd(musteriadi);
    }

    public List<Musteri> adVeAdreseGoreGetir(String ad, String adres){
        return repository.findAllByAdAndAdres(ad,adres);
    }

    public List<Musteri> findAllByAdLike(String ad){
        return repository.findAllByAdLike(ad);
    }

}
