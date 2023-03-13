package kg.mega.natv.model.dto.response_dto;

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
public class ChannelPriceResponseDto {

    Long channelId;

    @JsonFormat(pattern = "dd.MM.yyyy")
    List<Date> dates;

    Double price;

    Double priceWithDiscount;
}
