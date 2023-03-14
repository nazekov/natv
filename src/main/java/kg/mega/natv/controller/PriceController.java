package kg.mega.natv.controller;

import kg.mega.natv.exception_handle.exception.InputInfoChannelException;
import kg.mega.natv.model.dto.request_dto.PriceRequestDto;
import kg.mega.natv.model.entity.Channel;
import kg.mega.natv.service.PriceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/price")
public class PriceController {

    private final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @PostMapping("/save")
    public ResponseEntity<Channel> addNewPriceToChannel(
            @RequestBody PriceRequestDto priceRequestDto) {
        if (priceRequestDto.getPricePerLetter() <= 0) {
            throw new InputInfoChannelException("price must be positive number.");
        }
        return ResponseEntity.ok(priceService.addPrice(priceRequestDto));
    }
}
