package kg.mega.natv.service.impl;

import kg.mega.natv.exception_handle.exception.InputInfoChannelException;
import kg.mega.natv.mapper.DiscountMapper;
import kg.mega.natv.model.dto.IDiscountDto;
import kg.mega.natv.model.dto.request_dto.DiscountCreateDto;
import kg.mega.natv.model.entity.Channel;
import kg.mega.natv.model.entity.Discount;
import kg.mega.natv.repository.DiscountRepository;
import kg.mega.natv.service.ChannelService;
import kg.mega.natv.service.DiscountService;
import kg.mega.natv.utils.DateUtil;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DiscountServiceImpl implements DiscountService {

    private final DiscountRepository discountRepository;
    private final ChannelService channelService;
    private final DiscountMapper discountMapper = DiscountMapper.INSTANCE;

    public DiscountServiceImpl(DiscountRepository discountRepository,
                               @Lazy ChannelService channelService) {
        this.discountRepository = discountRepository;
        this.channelService = channelService;
    }

    @Override
    public double findDiscountText(Long channelId, int countDays) {
        return discountRepository
                .findDiscount(channelId, countDays)
                .orElse(0.0);
    }

    @Override
    public List<IDiscountDto> findDiscounts(Long channelId) {
        return discountRepository.findDiscounts(channelId);
    }

    @Override
    public Channel save(DiscountCreateDto discountDto) {
        long id = discountDto.getChannelId();
        Channel channel = channelService.findById(id);
        double discountPercent = discountDto.getDiscountPercent();
        Optional<Discount> optionalDiscount =
                discountRepository.existsDiscount(id, discountPercent);
        if (optionalDiscount.isPresent()) {
            throw new InputInfoChannelException("channel id = " + id +
                    " already exists such discount.");
        }
        Discount newDiscount = discountMapper.dtoToDiscountEntity(discountDto);
        newDiscount.setChannel(channel);
        List<Discount> discountList = channel.getDiscounts();
        discountList.add(newDiscount);
        return channelService.addNewDiscount(channel);
    }
}
