package kg.mega.natv.model.dto.request_dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PriceRequestDto {

    long channelId;

    double pricePerLetter;
}
