package horse.latte.exceptionhandler;

import org.springframework.web.bind.annotation.ResponseStatus;

public class ApiRequestException extends IllegalArgumentException{
    public ApiRequestException(String message) {
        super(message);
    }

    public ApiRequestException(String message, Throwable cause) {
        super(message, cause);
    }

}
