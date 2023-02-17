package com.busecnky.SpringMono.service;

import com.busecnky.SpringMono.repository.ISatisRepository;
import com.busecnky.SpringMono.repository.entity.Satis;
import com.busecnky.SpringMono.utility.ServiceManager;

public class SatisService extends ServiceManager<Satis,Long> {

    private final ISatisRepository repository;

    public SatisService(ISatisRepository repository){
        super(repository);
        this.repository = repository;
    }
}
