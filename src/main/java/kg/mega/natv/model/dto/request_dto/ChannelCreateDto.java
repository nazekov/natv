package kg.mega.natv.model.dto.request_dto;

import kg.mega.natv.model.dto.DiscountDto;
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
public class ChannelCreateDto {

    String name;

    Double pricePerLetter;

    List<DiscountDto> discounts;
}
