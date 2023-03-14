package kg.mega.natv.controller;

import kg.mega.natv.model.dto.request_dto.PriceRequestDto;
import kg.mega.natv.model.entity.Channel;
import kg.mega.natv.service.PriceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.ok(priceService.addPrice(priceRequestDto));
    }
}
