package kg.mega.natv.controller;

import kg.mega.natv.model.dto.request_dto.OrderRequestDto;
import kg.mega.natv.model.dto.response_dto.OrderResponseDto;
import kg.mega.natv.service.OrderService;
import kg.mega.natv.service.ValidateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    private final ValidateService validateService;
    private final OrderService orderService;

    public OrderController(ValidateService validateService,
                           OrderService orderService) {
        this.validateService = validateService;
        this.orderService = orderService;
    }

    @PostMapping("/save")
    public ResponseEntity<OrderResponseDto> createAdvertisement(
                        @RequestBody OrderRequestDto orderDto) {
        validateService.checkInputData(orderDto);
        return ResponseEntity.ok(orderService.createAdvertisement(orderDto));
    }

    @GetMapping("/{id}")
    public OrderResponseDto getOrder(@PathVariable long id) {
        return orderService.findById(id);
    }
}
