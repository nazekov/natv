package kg.mega.natv.service.impl;

import kg.mega.natv.model.entity.PriceText;
import kg.mega.natv.repository.PriceTextRepository;
import kg.mega.natv.service.PriceTextService;
import kg.mega.natv.utils.DateUtil;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class PriceTextServiceImpl implements PriceTextService {

    private final PriceTextRepository priceTextRepository;

    public PriceTextServiceImpl(PriceTextRepository priceTextRepository) {
        this.priceTextRepository = priceTextRepository;
    }

    @Override
    public Double findPriceByChannelIdAndEndDate(Long id) {
        return priceTextRepository.findByChannelIdAndEndDate(id,
                DateUtil.getInstance().getEndDate());
    }
}
