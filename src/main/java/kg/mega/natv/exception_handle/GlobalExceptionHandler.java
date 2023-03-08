package kg.mega.natv.exception_handle;

import kg.mega.natv.exception_handle.exception.ImageException;
import kg.mega.natv.exception_handle.exception.InputInfoChannelException;
import kg.mega.natv.exception_handle.exception.TextOrderException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<IncorrectInputData> handleException(
                                ImageException exception) {
        IncorrectInputData error = new IncorrectInputData();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exception.getMessage());
        error.setErrorType(exception.getClass().toString());
        error.setDateTime(new Date());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<IncorrectInputData> handleException(
                        InputInfoChannelException exception) {
        IncorrectInputData error = new IncorrectInputData();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exception.getMessage());
        error.setErrorType(exception.getClass().toString());
        error.setDateTime(new Date());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<IncorrectInputData> handleException(
                        TextOrderException exception) {
        IncorrectInputData error = new IncorrectInputData();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exception.getMessage());
        error.setErrorType(exception.getClass().toString());
        error.setDateTime(new Date());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
