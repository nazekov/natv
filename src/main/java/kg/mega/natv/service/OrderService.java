package kg.mega.natv.service;

import kg.mega.natv.model.dto.request_dto.OrderRequestDto;
import kg.mega.natv.model.dto.request_dto.TextOrderRequestDto;
import kg.mega.natv.model.dto.response_dto.OrderResponseDto;
import kg.mega.natv.model.dto.response_dto.TextOrderResponseDto;
import kg.mega.natv.repository.OrderRepository;

public interface OrderService {

    TextOrderResponseDto getOrderPrice(TextOrderRequestDto textOrderRequestDto);

    OrderResponseDto createAdvertisement(OrderRequestDto orderDto);

    OrderResponseDto findById(Long id);
}
