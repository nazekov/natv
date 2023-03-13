package kg.mega.natv.model.dto.response_dto;

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
public class OrderResponseDto {

    String clientFIO;

    String clientEmail;

    String clientPhone;

    String text;

    Double totalPrice;

    String status;

    List<ChannelPriceResponseDto> channels;
}
