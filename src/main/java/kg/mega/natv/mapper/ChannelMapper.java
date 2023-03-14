package kg.mega.natv.mapper;

import kg.mega.natv.model.dto.response_dto.ChannelResponseDto;
import kg.mega.natv.model.entity.Channel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper
public interface ChannelMapper {

    ChannelMapper INSTANCE = Mappers.getMapper(ChannelMapper.class);

    @Mapping(source = "id", target = "id")
    ChannelResponseDto dtoToChannelEntity(Channel channel);

    List<ChannelResponseDto> channelListToListDto(List<Channel> channels);
}
