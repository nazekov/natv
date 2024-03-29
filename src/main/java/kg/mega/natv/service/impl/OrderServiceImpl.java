package kg.mega.natv.service.impl;

import kg.mega.natv.enums.StatusOrder;
import kg.mega.natv.exception_handle.exception.OrderException;
import kg.mega.natv.mapper.OrderDetailsMapper;
import kg.mega.natv.mapper.OrderMapper;
import kg.mega.natv.model.dto.request_dto.OrderRequestDto;
import kg.mega.natv.model.dto.request_dto.TextOrderRequestDto;
import kg.mega.natv.model.dto.response_dto.ChannelPriceResponseDto;
import kg.mega.natv.model.dto.response_dto.OrderResponseDto;
import kg.mega.natv.model.dto.response_dto.TextOrderResponseDto;
import kg.mega.natv.model.entity.DatesOrderDetails;
import kg.mega.natv.model.entity.Order;
import kg.mega.natv.model.entity.OrderDetail;
import kg.mega.natv.repository.OrderRepository;
import kg.mega.natv.service.ChannelService;
import kg.mega.natv.service.DiscountService;
import kg.mega.natv.service.PriceService;
import kg.mega.natv.service.OrderService;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final PriceService priceService;
    private final DiscountService discountService;
    private final OrderRepository orderRepository;
    private final ChannelService channelService;
    private final OrderMapper orderMapper = OrderMapper.INSTANCE;

    public OrderServiceImpl(PriceService priceService,
                            DiscountService discountService,
                            OrderRepository orderRepository,
                            ChannelService channelService) {
        this.priceService = priceService;
        this.discountService = discountService;
        this.orderRepository = orderRepository;
        this.channelService = channelService;
    }

    @Override
    public TextOrderResponseDto getOrderPrice(TextOrderRequestDto textOrderRequestDto) {
        String text = getFormattedText(textOrderRequestDto.getText());
        textOrderRequestDto.setText(text);
        int textSize = getTextSize(text);
        Long channelId = textOrderRequestDto.getChannelId();
        int daysCount = textOrderRequestDto.getDaysCount();

        double priceCalc = getPriceCalculate(channelId, daysCount, textSize);
        double priceCalcWithDiscount =
                getPriceCalculateWithDiscount(channelId, daysCount, priceCalc);
        priceCalcWithDiscount = Math.round(priceCalcWithDiscount * 100) / 100.0;
        TextOrderResponseDto textOrderResponseDto =
                orderMapper.requstToResponseDto(textOrderRequestDto);

        textOrderResponseDto.setPrice(priceCalc);
        textOrderResponseDto.setPriceWithDiscount(priceCalcWithDiscount);
        return textOrderResponseDto;
    }

    private double getPriceCalculateWithDiscount(Long channelId,
                                                 Integer daysCount,
                                                 double priceCalc) {
        double discountPercent = getDiscountPercent(channelId, daysCount);
        return priceCalc * (1.0 - discountPercent / 100.0);
    }

    private double getPriceCalculate(Long channelId, int daysCount, int textSize) {
        double pricePerLetter = getPricePerLetter(channelId);
        return pricePerLetter * daysCount * textSize;
    }

    private double getDiscountPercent(Long channelId, int daysCount) {
        return discountService.findDiscountText(channelId, daysCount);
    }

    private double getPricePerLetter(Long channelId) {
        return priceService.findActualPriceByChannelId(channelId);
    }

    private int getTextSize(String text) {
        return text.replaceAll(" ", "").length();
    }

    private String getFormattedText(String text) {
        return text.trim().replaceAll("\\s++", " ");
    }

    @Transactional
    @Override
    public OrderResponseDto createAdvertisement(OrderRequestDto orderRequestDto) {
        String text = getFormattedText(orderRequestDto.getText());
        int textSize = getTextSize(text);

        Order order = orderMapper.requestDtoToOrderEntity(orderRequestDto);
        order.setText(text);
        order.setCountSymbols(textSize);
        order.setCreatedDate(new Date());
        order.setStatus(StatusOrder.CREATED);

        double[] totalPrice = {0.0};
        List<OrderDetail> orderDetailList = new ArrayList<>();
        orderRequestDto.getChannels().forEach(
            channelDto -> {
                int daysCount = channelDto.getDays().size();
                Long channelId = channelDto.getChannelId();
                double priceCalc = getPriceCalculate(channelId, daysCount, textSize);
                double priceWithDiscount =
                        getPriceCalculateWithDiscount(channelId, daysCount, priceCalc);
                priceWithDiscount = Math.round(priceWithDiscount * 100) / 100.0;
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrder(order);
                orderDetail.setChannel(channelService.findById(channelId));
                orderDetail.setPrice(priceCalc);
                orderDetail.setPriceWithDiscount(priceWithDiscount);
                totalPrice[0] += priceWithDiscount;

                List<DatesOrderDetails> datesOrderDetailsList = new ArrayList<>();
                List<Date> days = channelDto.getDays();
                days.forEach(
                    date -> {
                        DatesOrderDetails datesOrderDetails = new DatesOrderDetails();
                        datesOrderDetails.setDate(date);
                        datesOrderDetails.setOrderDetail(orderDetail);
                        datesOrderDetailsList.add(datesOrderDetails);
                    }
                );
                orderDetail.setDays(datesOrderDetailsList);

                orderDetailList.add(orderDetail);
            }
        );
        double totalPriceRound = Math.round(totalPrice[0] * 100) / 100.0;
        order.setTotalPrice(totalPriceRound);
        order.setOrderDetailList(orderDetailList);

        orderRepository.save(order);
        return convertToResponseDto(order);
    }

    private OrderResponseDto convertToResponseDto(Order order) {
        OrderResponseDto orderResponseDto =
                orderMapper.orderToResponseDto(order);

        List<ChannelPriceResponseDto> channelsPriceList = new ArrayList<>();//

        List<OrderDetail> orderDetailList = order.getOrderDetailList();
        orderDetailList.forEach(
                orderDetail -> {
                ChannelPriceResponseDto channelPriceResponseDto =
                        OrderDetailsMapper
                            .INSTANCE
                            .orderDetailsToChannelPriceResponseDto(orderDetail);

                List<DatesOrderDetails> datesOrderDetailsList = orderDetail.getDays();

                List<Date> dates = new ArrayList<>();
                datesOrderDetailsList.forEach(
                    datesOrderDetails -> {
                        dates.add(datesOrderDetails.getDate());
                    }
                );
                channelPriceResponseDto.setDates(dates);

                channelsPriceList.add(channelPriceResponseDto);
            }
        );
        orderResponseDto.setChannels(channelsPriceList);
        return orderResponseDto;
    }

    public OrderResponseDto findById(Long id) {
        Order order = orderRepository.findById(id)
                        .orElseThrow(
                            () -> new OrderException("order not found")
                        );
        return convertToResponseDto(order);
    }
}
