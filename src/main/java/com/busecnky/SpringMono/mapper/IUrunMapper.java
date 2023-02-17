package com.busecnky.SpringMono.mapper;

import com.busecnky.SpringMono.dto.request.UrunSaveRequestDto;
import com.busecnky.SpringMono.repository.entity.Urun;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface IUrunMapper {

    IUrunMapper INSTANCE = Mappers.getMapper(IUrunMapper.class);

    Urun urunFromDto(final UrunSaveRequestDto dto);

}
