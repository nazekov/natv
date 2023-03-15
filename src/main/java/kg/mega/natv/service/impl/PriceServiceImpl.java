package kg.mega.natv.service.impl;

import kg.mega.natv.exception_handle.exception.InputInfoChannelException;
import kg.mega.natv.model.dto.request_dto.PriceRequestDto;
import kg.mega.natv.model.entity.Channel;
import kg.mega.natv.repository.PriceRepository;
import kg.mega.natv.service.ChannelService;
import kg.mega.natv.service.PriceService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;
    private final ChannelService channelService;

    public PriceServiceImpl(PriceRepository priceRepository,
                           @Lazy ChannelService channelService) {
        this.priceRepository = priceRepository;
        this.channelService = channelService;
    }

    @Override
    public Double findActualPriceByChannelId(Long id) {
        Optional<Double> optionalPrice = priceRepository.findByChannelIdAndEndDate(id);
        return optionalPrice.orElseThrow(
                    () -> new InputInfoChannelException("channel with id = " + id +
                                                        " not active")
                );
    }

    @Override
    public Channel addPrice(PriceRequestDto priceRequestDto) {
        return channelService.addNewPrice(priceRequestDto);
    }
}
