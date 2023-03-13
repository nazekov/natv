package kg.mega.natv.model.dto.response_dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TextOrderResponseDto {

    String text;

    Integer daysCount;

    Long channelId;

    Double price;

    Double priceWithDiscount;
}
