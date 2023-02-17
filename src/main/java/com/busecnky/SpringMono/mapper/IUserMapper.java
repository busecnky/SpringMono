package com.busecnky.SpringMono.mapper;

import com.busecnky.SpringMono.dto.request.RegisterRequestDto;
import com.busecnky.SpringMono.dto.response.UserControllerFindAllResponseDto;
import com.busecnky.SpringMono.repository.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * Class -> Dto ya da Dtp -> Class tür dönüşümleri için ilk olarak ilgili interface üzerinde
 * @Mapper anotasyonumuzu ekliyoruz. Burada dikkat edilmesi gereken iki nokta var
 * Bunlar:
 * 1- mapper spring framework un uygulama çatışına uygun olarak entegre olsun diye component
 * model olaral spring'i ekliyoruz.
 * 2- İster istemez tür dönüşümlerinde alan adları bire bir uyuşmamaktadır. Bu nedenle eşleşmeyen
 * alanların nasıl davranacağını belirlemesi gereklidir. Bunu sağlamak için hedef politikalarının
 * seçilmesine ihtiyaç vardır.
 *
 *
 * Mapperlar birebir isimlerden eşleştiriliyor!!!
 * Bu ignore da null gördüğü zaman null pointer hatası almayalım diye yazdık null görünce
 * hata vermektense ignore edicek.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserMapper {

    IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);

    /**
     * Burda metot isimlerinin bir anlamı yok. Burda önemli olan hangisinin hangisine gittiği.
     */
    User toUser(final RegisterRequestDto dto);

    UserControllerFindAllResponseDto userControllerFindAllResponseDtoFromUser(final User user);


}
