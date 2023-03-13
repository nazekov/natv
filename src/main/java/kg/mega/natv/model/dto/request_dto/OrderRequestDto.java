package kg.mega.natv.model.dto.request_dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import java.util.List;

@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderRequestDto {

    String clientEmail;

    String clientFIO;

    String clientPhone;

    String text;

    List<ChannelRequestDto> channels;
}
