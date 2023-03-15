package kg.mega.natv.service;

import kg.mega.natv.model.dto.IDiscountDto;
import kg.mega.natv.model.dto.request_dto.DiscountCreateDto;
import kg.mega.natv.model.dto.request_dto.DiscountRemoveDto;
import kg.mega.natv.model.entity.Channel;
import java.util.List;

public interface DiscountService {

    double findDiscountText(Long channelId, int countDays);

    List<IDiscountDto> findDiscounts(Long channelId);

    Channel save(DiscountCreateDto discountDto);

    Channel remove(DiscountRemoveDto discountDto);
}
