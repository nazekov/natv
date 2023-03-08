package kg.mega.natv.service.impl;

import kg.mega.natv.repository.DiscountTextRepository;
import kg.mega.natv.service.DiscountTextService;
import kg.mega.natv.utils.DateUtil;
import org.springframework.stereotype.Service;

@Service
public class DiscountTextServiceImpl implements DiscountTextService {

    private final DiscountTextRepository discountTextRepository;

    public DiscountTextServiceImpl(DiscountTextRepository discountTextRepository) {
        this.discountTextRepository = discountTextRepository;
    }

    @Override
    public Double findDiscountText(Long channelId, Integer countDays) {
        return discountTextRepository.findDiscount(
                    channelId,
                    DateUtil.getInstance().getEndDate(),
                    countDays)
                .orElse(0.0);
    }
}
