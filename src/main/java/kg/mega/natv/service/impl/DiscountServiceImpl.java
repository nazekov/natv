package kg.mega.natv.service.impl;

import kg.mega.natv.model.dto.IDiscountDto;
import kg.mega.natv.repository.DiscountRepository;
import kg.mega.natv.service.DiscountService;
import kg.mega.natv.utils.DateUtil;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DiscountServiceImpl implements DiscountService {

    private final DiscountRepository discountRepository;

    public DiscountServiceImpl(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
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
}
