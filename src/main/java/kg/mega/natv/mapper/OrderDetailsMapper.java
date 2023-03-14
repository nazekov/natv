package kg.mega.natv.mapper;

import kg.mega.natv.model.dto.response_dto.ChannelPriceResponseDto;
import kg.mega.natv.model.entity.OrderDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderDetailsMapper {

    OrderDetailsMapper INSTANCE = Mappers.getMapper(OrderDetailsMapper.class);

    @Mapping(source = "channel.id", target = "channelId")
    ChannelPriceResponseDto orderDetailsToChannelPriceResponseDto(OrderDetails orderDetails);
}
