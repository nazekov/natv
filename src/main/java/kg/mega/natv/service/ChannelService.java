package kg.mega.natv.service;

import kg.mega.natv.model.dto.ChannelDto;
import kg.mega.natv.model.entity.Channel;
import org.springframework.web.multipart.MultipartFile;

public interface ChannelService {

    Channel save(MultipartFile file, ChannelDto channelDto);

    boolean existsByName(String name);

    boolean existsById(Long id);
}
