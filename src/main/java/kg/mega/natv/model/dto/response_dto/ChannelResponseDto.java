package kg.mega.natv.model.dto.response_dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class ChannelResponseDto {

    @JsonIgnore
    Long id;

    String name;

    String pathLogo;

    Double pricePerLetter;

    List<DiscountDto> discounts;
}
