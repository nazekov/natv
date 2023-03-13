package kg.mega.natv.model.dto.request_dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChannelRequestDto {

    Long channelId;

    @JsonFormat(pattern = "dd.MM.yyyy")
    List<Date> days;
}
