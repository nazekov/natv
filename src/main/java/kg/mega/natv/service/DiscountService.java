package kg.mega.natv.service;

import kg.mega.natv.model.dto.IDiscountDto;
import java.util.List;

public interface DiscountService {

    double findDiscountText(Long channelId, int countDays);

    List<IDiscountDto> findDiscounts(Long channelId);
}
