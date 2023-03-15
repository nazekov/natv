package kg.mega.natv.controller;

import kg.mega.natv.model.dto.request_dto.DiscountCreateDto;
import kg.mega.natv.model.dto.request_dto.DiscountRemoveDto;
import kg.mega.natv.model.entity.Channel;
import kg.mega.natv.service.DiscountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/discount")
public class DiscountController {

    private final DiscountService discountService;

    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @PostMapping("/add")
    public ResponseEntity<Channel> addDiscount(
                @RequestBody DiscountCreateDto discountDto) {
        return ResponseEntity.ok(discountService.save(discountDto));
    }

    @PutMapping("/remove")
    public ResponseEntity<Channel> removeDiscount(
            @RequestBody DiscountRemoveDto discountDto) {
        return ResponseEntity.ok(discountService.remove(discountDto));
    }
}
