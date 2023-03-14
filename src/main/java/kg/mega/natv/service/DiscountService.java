package kg.mega.natv.service;

import kg.mega.natv.model.dto.IDiscountDto;
import java.util.List;

public interface DiscountService {

    Double findDiscountText(Long channelId, Integer countDays);

    List<IDiscountDto> findDiscounts(Long channelId);
}
