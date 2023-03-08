package kg.mega.natv.controller;

import kg.mega.natv.model.dto.TextOrderDto;
import kg.mega.natv.service.ChannelService;
import kg.mega.natv.service.TextOrderService;
import kg.mega.natv.service.ValidateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/text-order")
public class TextOrderController {

    private final ValidateService validateService;
    private final TextOrderService textOrderService;

    public TextOrderController(ValidateService validateService,
                               TextOrderService textOrderService) {
        this.validateService = validateService;
        this.textOrderService = textOrderService;
    }

    @GetMapping("/get-info")
    public ResponseEntity<TextOrderDto> getOrderPrice(
                        @RequestBody TextOrderDto textOrderDto) {
        validateService.checkInputData(textOrderDto);
        return ResponseEntity.ok(textOrderService.getOrderPrice(textOrderDto));
    }
}
