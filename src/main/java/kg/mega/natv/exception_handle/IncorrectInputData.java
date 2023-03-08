package kg.mega.natv.exception_handle;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class IncorrectInputData {

    Integer status;

    String message;

    String errorType;

    @JsonFormat(pattern = "dd.MM.yyyy HH:mm", timezone = "Asia/Bishkek")
    Date dateTime;
}
