package kg.mega.natv.service.impl;

import kg.mega.natv.exception_handle.exception.ImageException;
import kg.mega.natv.exception_handle.exception.InputInfoChannelException;
import kg.mega.natv.mapper.ChannelMapper;
import kg.mega.natv.mapper.DiscountMapper;
import kg.mega.natv.model.dto.request_dto.PriceRequestDto;
import kg.mega.natv.model.dto.response_dto.ChannelResponseDto;
import kg.mega.natv.model.dto.request_dto.ChannelCreateDto;
import kg.mega.natv.model.entity.Channel;
import kg.mega.natv.model.entity.Discount;
import kg.mega.natv.model.entity.Price;
import kg.mega.natv.repository.ChannelRepository;
import kg.mega.natv.service.ChannelService;
import kg.mega.natv.service.DiscountService;
import kg.mega.natv.service.PriceService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.transaction.Transactional;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

@Service
public class ChannelServiceImpl implements ChannelService {

    private final ChannelRepository channelRepository;

    private final PriceService priceService;

    private final DiscountService discountService;

    private final ChannelMapper channelMapper = ChannelMapper.INSTANCE;

    private final DiscountMapper discountMapper = DiscountMapper.INSTANCE;

    public ChannelServiceImpl(ChannelRepository channelRepository,
                              PriceService priceService,
                              DiscountService discountService) {
        this.channelRepository = channelRepository;
        this.priceService = priceService;
        this.discountService = discountService;
    }

    @Transactional
    @Override
    public Channel save(MultipartFile file, ChannelCreateDto channelDto) {
        Channel channel = new Channel();
        channel.setName(channelDto.getName());
        channel.setPathLogo(saveLogo(file));
        Price price = new Price();
        price.setPricePerLetter(channelDto.getPricePerLetter());
        List<Discount> discounts = DiscountMapper.INSTANCE
                        .dtoToDiscountEntityList(channelDto.getDiscounts());
        channel.addNewPriceText(price);
        channel.setDiscountsText(discounts);
        if (discounts != null) {
            discounts.forEach(discount -> discount.setChannel(channel));
        }
        return channelRepository.save(channel);
    }

    private String saveLogo(MultipartFile file) {
        Path path = Paths.get("DataSet/" + file.getOriginalFilename());

        try (OutputStream out = new BufferedOutputStream(
                Files.newOutputStream(path, CREATE, APPEND))
        ) {
            byte[] imageBytes = file.getBytes();
            out.write(imageBytes, 0, imageBytes.length);
        } catch (IOException ioe) {
            throw new ImageException("file image not saved.");
        }
        return path.toAbsolutePath().toString();
    }

    @Override
    public boolean existsByName(String name) {
        return channelRepository.findByNameIgnoreCase(name).isPresent();
    }

    @Override
    public boolean existsById(Long id) {
        return channelRepository.existsById(id);
    }

    @Override
    public Channel findById(Long id) {
        return channelRepository
                .findById(id)
                .orElseThrow(
                    () -> new InputInfoChannelException("channel not found")
                );
    }

    @Override
    public boolean existsByLogoName(String logoName) {
        return channelRepository
                .findDistinctByPathLogoEndingWith(logoName)
                .isPresent();
    }

    @Override
    public List<ChannelResponseDto> showAllChannels() {
        List<Channel> channelList = channelRepository.findAllByActive(true);

        List<ChannelResponseDto> channelDtos
                = channelMapper.channelListToListDto(channelList);

        channelDtos.forEach(
            channelDto -> {
                channelDto.setPricePerLetter(
                    priceService.findActualPriceByChannelId(channelDto.getId())
                );
                channelDto.setDiscounts(
                    discountMapper.idtosToDiscountDtoList(
                        discountService.findDiscounts(channelDto.getId())
                    )
                );
            }
        );
        return channelDtos;
    }

    @Override
    public Channel addNewPrice(PriceRequestDto priceRequestDto) {
        Long channelId = priceRequestDto.getChannelId();
        Channel channel = channelRepository.findById(channelId)
                .orElseThrow(() -> new InputInfoChannelException("Channel not found"));
        Price price = new Price();
        price.setPricePerLetter(priceRequestDto.getPricePerLetter());
        channel.addNewPriceText(price);
        return channel;
    }

    @Override
    public Channel setActiveById(long id, boolean newActive) {
        Channel channel = findById(id);
        channel.setActive(newActive);
        return channelRepository.save(channel);
    }
}
