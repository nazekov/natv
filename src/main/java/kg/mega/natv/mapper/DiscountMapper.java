package kg.mega.natv.mapper;

import kg.mega.natv.model.dto.DiscountDto;
import kg.mega.natv.model.dto.IDiscountDto;
import kg.mega.natv.model.dto.request_dto.DiscountCreateDto;
import kg.mega.natv.model.entity.Discount;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper
public interface DiscountMapper {

    DiscountMapper INSTANCE = Mappers.getMapper(DiscountMapper.class);

    List<Discount> dtoToDiscountEntityList(List<DiscountDto> discounts);

    List<DiscountDto> idtosToDiscountDtoList(List<IDiscountDto> iDiscountDtos);

    Discount dtoToDiscountEntity(DiscountCreateDto discountDto);
}
