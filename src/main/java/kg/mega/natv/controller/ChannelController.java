package kg.mega.natv.controller;

import kg.mega.natv.model.dto.ChannelDto;
import kg.mega.natv.model.entity.Channel;
import kg.mega.natv.service.ChannelService;
import kg.mega.natv.service.ValidateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/channel")
public class ChannelController {

    private final ChannelService channelService;
    private final ValidateService validateService;

    public ChannelController(ChannelService channelService,
                             ValidateService validateService) {
        this.channelService = channelService;
        this.validateService = validateService;
    }

    @PostMapping("/save")
    public ResponseEntity<Channel> save(
            @RequestPart("image") MultipartFile file,
            @RequestPart("dto") ChannelDto channelDto) {
        validateService.checkInputData(file, channelDto);
        return ResponseEntity.ok(channelService.save(file, channelDto));
    }
}