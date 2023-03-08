package kg.mega.natv.mapper;

import kg.mega.natv.model.dto.ChannelDto;
import kg.mega.natv.model.dto.DiscountDto;
import kg.mega.natv.model.entity.Channel;
import kg.mega.natv.model.entity.DiscountText;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper
public interface DiscountMapper {

    DiscountMapper INSTANCE = Mappers.getMapper(DiscountMapper.class);


    DiscountText dtoToDiscountEntity(DiscountDto discount);

    List<DiscountText> dtoToDiscountEntityList(List<DiscountDto> discounts);
}
