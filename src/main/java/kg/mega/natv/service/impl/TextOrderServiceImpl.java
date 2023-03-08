package kg.mega.natv.service.impl;

import kg.mega.natv.model.dto.TextOrderDto;
import kg.mega.natv.service.DiscountTextService;
import kg.mega.natv.service.PriceTextService;
import kg.mega.natv.service.TextOrderService;
import org.springframework.stereotype.Service;

@Service
public class TextOrderServiceImpl implements TextOrderService {

    private final PriceTextService priceTextService;
    private final DiscountTextService discountTextService;

    public TextOrderServiceImpl(PriceTextService priceTextService,
                                DiscountTextService discountTextService) {
        this.priceTextService = priceTextService;
        this.discountTextService = discountTextService;
    }

    @Override
    public TextOrderDto getOrderPrice(TextOrderDto textOrderDto) {
        System.out.println("TextOrderServiceImpl  getOrderPrice(");
        String text = textOrderDto.getText()
                .trim().replaceAll("\\s++", " ");
        int textSize = text.replaceAll(" ", "").length();
        Long channelId = textOrderDto.getChannelId();
        int daysCount = textOrderDto.getDaysCount();

        System.out.println("1 getPricePerLetter(");
        Double pricePerLetter = priceTextService.findPriceByChannelIdAndEndDate(channelId);
        System.out.println("PriceText price = : " + pricePerLetter);

        Double discountPercent = discountTextService.findDiscountText(channelId, daysCount);
        System.out.println("discountText: " + discountPercent);

        double priceCalc = pricePerLetter * textSize;
        double priceCalcWithDiscount = priceCalc * (1.0 - discountPercent / 100.0);

        textOrderDto.setText(text);
        textOrderDto.setPrice(priceCalc);
        textOrderDto.setPriceWithDiscount(priceCalcWithDiscount);
        return textOrderDto;
    }
}
