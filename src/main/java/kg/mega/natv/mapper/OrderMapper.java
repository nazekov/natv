package kg.mega.natv.mapper;

import kg.mega.natv.model.dto.request_dto.OrderRequestDto;
import kg.mega.natv.model.dto.request_dto.TextOrderRequestDto;
import kg.mega.natv.model.dto.response_dto.OrderResponseDto;
import kg.mega.natv.model.dto.response_dto.TextOrderResponseDto;
import kg.mega.natv.model.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    TextOrderResponseDto requstToResponseDto(TextOrderRequestDto requestDto);

    Order requestDtoToOrderEntity(OrderRequestDto requestDto);

    OrderResponseDto orderToResponseDto(Order order);
}
