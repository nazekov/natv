package kg.mega.natv.service;

import kg.mega.natv.model.dto.request_dto.PriceRequestDto;
import kg.mega.natv.model.entity.Channel;

public interface PriceService {

    Double findPriceByChannelIdAndEndDate(Long id);

    Channel addPrice(PriceRequestDto priceRequestDto);
}
