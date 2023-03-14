package kg.mega.natv.service;

import kg.mega.natv.model.dto.request_dto.PriceRequestDto;
import kg.mega.natv.model.dto.response_dto.ChannelResponseDto;
import kg.mega.natv.model.dto.request_dto.ChannelCreateDto;
import kg.mega.natv.model.entity.Channel;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public interface ChannelService {

    Channel save(MultipartFile file, ChannelCreateDto channelDto);

    boolean existsByName(String name);

    boolean existsById(Long id);

    Channel findById(Long id);

    boolean existsByLogoName(String logoName);

    List<ChannelResponseDto> showAllChannels();

    Channel addNewPrice(PriceRequestDto priceRequestDto);
}
