package kg.mega.natv.service.impl;

import kg.mega.natv.exception_handle.exception.ImageException;
import kg.mega.natv.exception_handle.exception.InputInfoChannelException;
import kg.mega.natv.exception_handle.exception.TextOrderException;
import kg.mega.natv.model.dto.ChannelDto;
import kg.mega.natv.model.dto.DiscountDto;
import kg.mega.natv.model.dto.TextOrderDto;
import kg.mega.natv.service.ChannelService;
import kg.mega.natv.service.ValidateService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

@Service
public class ValidateServiceImpl implements ValidateService {

    private final ChannelService channelService;

    public ValidateServiceImpl(ChannelService channelService) {
        this.channelService = channelService;
    }

    private final List<Consumer<ChannelDto>> infoChannelValidates = Arrays.asList(
            channelDto -> {
                if (isNameExist(channelDto.getName())) {
                    throw new InputInfoChannelException(
                            "Channel name already exists"
                    );
                }
            },
            channelDto -> {
                if (isWrongName(channelDto.getName())) {
                    throw new InputInfoChannelException(
                            "Channels name must not be empty"
                    );
                }
            },
            channelDto -> {
                if (isWrongLengthName(channelDto.getName())) {
                    throw new InputInfoChannelException(
                            "Channels name must have length between 3 and 40"
                    );
                }
            },
            channelDto -> {
                if (isWrongPrice(channelDto.getPricePerLetter())) {
                    throw new InputInfoChannelException(
                            "price per letter must be positive number and " +
                                    "must be between 1 and 20"
                    );
                }
            },
            channelDto -> {
                List<DiscountDto> discounts = channelDto.getDiscounts();
                if (discounts != null && isWrongDiscounts(discounts)) {
                    throw new InputInfoChannelException(
                            "fromDayCount and discount must be positive numbers"
                    );
                }
            }
    );

    private final List<Consumer<MultipartFile>> infoFileValidates = Arrays.asList(
            file -> {
                if (file.getSize() == 0) {
                    throw new ImageException("file image is empty.");
                }
            },
            file -> {
                if (file.getSize() > 10000) {
                    throw new ImageException(
                            "image of logo size must not be greater than 10 KB."
                    );
                }
            },
            file -> {
                if (!isCorrectFileFormat(file)) {
                    throw new ImageException(
                            "file must have .jpg, .png or .gif extension."
                    );
                }
            }
    );

    private final List<Consumer<TextOrderDto>> infoTextOrderValidates = Arrays.asList(
            textOrderDto -> {
                if (textOrderDto.getChannelId() < 0) {
                    throw new TextOrderException("channelId must be positive.");
                }
            },
            textOrderDto -> {
                if (isChannelNotExist(textOrderDto.getChannelId())) {
                    throw new TextOrderException("channel not exist.");
                }
            },
            textOrderDto -> {
                if (textOrderDto.getDaysCount() < 0) {
                    throw new TextOrderException("daysCount must be positive");
                }
            },
            textOrderDto -> {
                if (checkCountLetters(textOrderDto.getText())) {
                    throw new TextOrderException(
                            "minimum count letters of advertisement text must be 20");
                }
            }
    );

    @Override
    public void checkInputData(MultipartFile file, ChannelDto channelDto) {
        infoFileValidates.forEach(action -> action.accept(file));
        infoChannelValidates.forEach(action -> action.accept(channelDto));
    }

    @Override
    public void checkInputData(TextOrderDto textOrderDto) {
        infoTextOrderValidates.forEach(action -> action.accept(textOrderDto));
    }

    private boolean isCorrectFileFormat(MultipartFile file) {
        return file.getContentType().endsWith("jpeg") ||
                file.getContentType().endsWith("png") ||
                file.getContentType().endsWith("gif");
    }

    private boolean isWrongName(String name) {
        return name == null || name.equals("");
    }

    private boolean isWrongLengthName(String name) {
        return name.length() < 3 || name.length() > 40;
    }

    private boolean isWrongPrice(Double price) {
        return price < 0 || price > 20;
    }

    private boolean isWrongDiscounts(List<DiscountDto> discounts) {
//        System.out.println(discounts);
        return discounts.stream().anyMatch(discount ->
                discount.getFromDaysCount() < 0 ||
                        discount.getDiscountPercent() < 0);
    }

    private boolean isNameExist(String name) {
        return channelService.existsByName(name);
    }

    private boolean isChannelNotExist(Long id) {
        return !channelService.existsById(id);
    }

    private boolean checkCountLetters(String text) {
        int countLetters = text.trim()
                .replaceAll("\\s+", "")
                .length();
        return countLetters < 20;
    }
}
