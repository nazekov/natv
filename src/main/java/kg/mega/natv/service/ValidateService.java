package kg.mega.natv.service;

import kg.mega.natv.model.dto.request_dto.ChannelCreateDto;
import kg.mega.natv.model.dto.request_dto.OrderRequestDto;
import kg.mega.natv.model.dto.request_dto.TextOrderRequestDto;
import org.springframework.web.multipart.MultipartFile;

public interface ValidateService {

    void checkInputData(MultipartFile file, ChannelCreateDto channelDto);

    void checkInputData(TextOrderRequestDto textOrderRequestDto);

    void checkInputData(OrderRequestDto orderDto);
}
