package kg.mega.natv.service;

import kg.mega.natv.model.dto.ChannelDto;
import kg.mega.natv.model.dto.TextOrderDto;
import org.springframework.web.multipart.MultipartFile;

public interface ValidateService {

    void checkInputData(MultipartFile file, ChannelDto channelDto);

    void checkInputData(TextOrderDto textOrderDto);
}
