package kg.mega.natv.service.impl;

import kg.mega.natv.exception_handle.exception.ImageException;
import kg.mega.natv.mapper.DiscountMapper;
import kg.mega.natv.model.dto.ChannelDto;
import kg.mega.natv.model.entity.Channel;
import kg.mega.natv.model.entity.DiscountText;
import kg.mega.natv.model.entity.PriceText;
import kg.mega.natv.repository.ChannelRepository;
import kg.mega.natv.service.ChannelService;
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
@Transactional
public class ChannelServiceImpl implements ChannelService {

    private final ChannelRepository channelRepository;

    public ChannelServiceImpl(ChannelRepository channelRepository) {
        this.channelRepository = channelRepository;
    }

    @Override
    public Channel save(MultipartFile file, ChannelDto channelDto) {
        Channel channel = new Channel();
        channel.setName(channelDto.getName());
        channel.setPathLogo(saveLogo(file));

        PriceText priceText = new PriceText();
        priceText.setPricePerLetter(channelDto.getPricePerLetter());

        List<DiscountText> discountTexts =
                DiscountMapper.INSTANCE.dtoToDiscountEntityList(channelDto.getDiscounts());

        channel.addNewPriceText(priceText);
//        List<PriceText> priceTexts = Arrays.asList(priceText);

//        channel.setPricesText(priceTexts);
        channel.setDiscountsText(discountTexts);

        channelRepository.save(channel);

//        priceTexts.stream()
//                .forEach(priceText1 -> priceText1.setChannel(channel));
        discountTexts.stream()
                .forEach(discountText -> discountText.setChannel(channel));

        return channel;
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
}
