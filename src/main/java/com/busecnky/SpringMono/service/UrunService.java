package com.busecnky.SpringMono.service;

import com.busecnky.SpringMono.dto.request.UrunSaveRequestDto;
import com.busecnky.SpringMono.mapper.IUrunMapper;
import com.busecnky.SpringMono.repository.IUrunRepository;
import com.busecnky.SpringMono.repository.entity.Urun;
import com.busecnky.SpringMono.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service

public class UrunService extends ServiceManager<Urun,Long> {

    public UrunService(IUrunRepository repository){
        super(repository);   //Bu sayede repository ServiceManager a gönderildi.
    }

    public void save(UrunSaveRequestDto dto){
        save(IUrunMapper.INSTANCE.urunFromDto(dto));

    }

}
