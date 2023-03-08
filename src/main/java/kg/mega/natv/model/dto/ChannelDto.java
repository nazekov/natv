package kg.mega.natv.model.dto;

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
public class ChannelDto {

    String name;

    String logo;

    Double pricePerLetter;

    List<DiscountDto> discounts;
}
