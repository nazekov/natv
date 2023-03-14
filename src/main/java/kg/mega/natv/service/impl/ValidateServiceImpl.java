package kg.mega.natv.service.impl;

import kg.mega.natv.exception_handle.exception.ImageException;
import kg.mega.natv.exception_handle.exception.InputInfoChannelException;
import kg.mega.natv.exception_handle.exception.OrderException;
import kg.mega.natv.model.dto.DiscountDto;
import kg.mega.natv.model.dto.request_dto.ChannelCreateDto;
import kg.mega.natv.model.dto.request_dto.OrderRequestDto;
import kg.mega.natv.model.dto.request_dto.TextOrderRequestDto;
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

    private final List<Consumer<ChannelCreateDto>> channelValidates = Arrays.asList(
        channelDto -> {
            if (channelDto == null) {
                throw new InputInfoChannelException(
                        "data is empty"
                );
            }
        },
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

    private final List<Consumer<MultipartFile>> fileValidates = Arrays.asList(
        file -> {
            if (file.getSize() == 0) {
                throw new ImageException("file image is empty.");
            }
        },
        file -> {
            if (isLogoExists(file.getOriginalFilename())) {
                throw new ImageException("file image with this name already exists.");
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
            if (!isCorrectFileFormat(file.getContentType())) {
                throw new ImageException(
                        "file must have .jpg, .png or .gif extension."
                );
            }
        }
    );

    private final List<Consumer<TextOrderRequestDto>> textOrderValidates = Arrays.asList(
        textOrderDto -> {
            if (textOrderDto == null) {
                throw new OrderException("data is empty.");
            }
        },
        textOrderDto -> {
            if (isChannelNotExist(textOrderDto.getChannelId())) {
                throw new OrderException("channel not exist.");
            }
        },
        textOrderDto -> {
            if (textOrderDto.getDaysCount() <= 0) {
                throw new OrderException("daysCount must be positive number");
            }
        },
        textOrderDto -> {
            if (textOrderDto.getText() == null
                || checkCountLetters(textOrderDto.getText())) {
                throw new OrderException(
                        "minimum count letters of advertisement text must be 20");
            }
        }
    );

    private final List<Consumer<OrderRequestDto>> orderValidates = Arrays.asList(
        orderDto -> {
            if (orderDto == null) {
                throw new OrderException("data is empty");
            }
        },
        orderDto -> {
            if (orderDto.getText() == null
                || checkCountLetters(orderDto.getText())) {
                throw new OrderException(
                        "minimum count letters of advertisement text must be 20");
            }
        },
        orderDto -> {
            if (isIncorrectPhone(orderDto.getClientPhone())) {
                throw new OrderException(
                        "Phone number must start with '+' and contains only digits." +
                        "Phone number length must be at least 10 characters.");
            }
        }
    );

    private boolean isIncorrectPhone(String phone) {
        boolean result = false;
        if (phone.length() >= 10 && phone.charAt(0) == '+') {
            for (int i = 1; i < phone.length(); i++) {
                char c = phone.charAt(i);
                if (c < '0' || c > '9') {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    @Override
    public void checkInputData(MultipartFile file, ChannelCreateDto channelDto) {
        fileValidates.forEach(action -> action.accept(file));
        channelValidates.forEach(action -> action.accept(channelDto));
    }

    @Override
    public void checkInputData(TextOrderRequestDto textOrderRequestDto) {
        textOrderValidates.forEach(action -> action.accept(textOrderRequestDto));
    }

    @Override
    public void checkInputData(OrderRequestDto orderDto) {
        orderValidates.forEach(action -> action.accept(orderDto));
    }

    private boolean isCorrectFileFormat(String contentType) {
        return contentType.endsWith("jpeg") ||
                contentType.endsWith("png") ||
                contentType.endsWith("gif");
    }

    private boolean isLogoExists(String logoName) {
        return channelService.existsByLogoName(logoName);
    }

    private boolean isWrongName(String name) {
        return name == null || name.equals("");
    }

    private boolean isWrongLengthName(String name) {
        return name.length() < 3 || name.length() > 40;
    }

    private boolean isWrongPrice(Double price) {
        return price <= 0 || price > 20;
    }

    private boolean isWrongDiscounts(List<DiscountDto> discounts) {
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
        return text.trim()
                .replaceAll("\\s+", "")
                .length() < 20;
    }
}
