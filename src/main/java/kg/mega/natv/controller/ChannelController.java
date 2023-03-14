package kg.mega.natv.controller;

import kg.mega.natv.model.dto.request_dto.ChannelCreateDto;
import kg.mega.natv.model.dto.request_dto.TextOrderRequestDto;
import kg.mega.natv.model.dto.response_dto.ChannelResponseDto;
import kg.mega.natv.model.dto.response_dto.TextOrderResponseDto;
import kg.mega.natv.model.entity.Channel;
import kg.mega.natv.service.ChannelService;
import kg.mega.natv.service.OrderService;
import kg.mega.natv.service.ValidateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequestMapping("/api/v1/channel")
public class ChannelController {

    private final ChannelService channelService;
    private final OrderService orderService;
    private final ValidateService validateService;

    public ChannelController(ChannelService channelService,
                             OrderService orderService,
                             ValidateService validateService) {
        this.channelService = channelService;
        this.orderService = orderService;
        this.validateService = validateService;
    }

    @PostMapping("/save")
    public ResponseEntity<Channel> save(
                        @RequestPart("image") MultipartFile file,
                        @RequestPart("dto") ChannelCreateDto channelDto) {
        validateService.checkInputData(file, channelDto);
        return ResponseEntity.ok(channelService.save(file, channelDto));
    }

    @GetMapping("/list")
    public ResponseEntity<List<ChannelResponseDto>> showAllChannels() {
        return ResponseEntity.ok(channelService.showAllChannels());
    }

    @GetMapping("/calculate")
    public ResponseEntity<TextOrderResponseDto> getOrderPrice(
            @RequestBody TextOrderRequestDto textOrderRequestDto) {
        validateService.checkInputData(textOrderRequestDto);
        return ResponseEntity.ok(orderService.getOrderPrice(textOrderRequestDto));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Channel> getChannel(@PathVariable long id) {
        return ResponseEntity.ok(channelService.findById(id));
    }

    @PutMapping("hide/{id}")
    public ResponseEntity<Channel> hideActiveChannel(@PathVariable long id) {
        return ResponseEntity.ok(channelService.setActiveById(id, false));
    }

    @PutMapping("active/{id}")
    public ResponseEntity<Channel> activateChannel(@PathVariable long id) {
        return ResponseEntity.ok(channelService.setActiveById(id, true));
    }
}