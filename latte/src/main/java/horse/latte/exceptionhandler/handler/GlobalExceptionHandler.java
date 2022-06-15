package horse.latte.exceptionhandler.handler;

import horse.latte.dto.ErrorResponse;
import horse.latte.exceptionhandler.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DuplicateUsernameException.class)
    public ErrorResponse handleDuplicateUsernameException(DuplicateUsernameException ex) {
        return ErrorResponse.of(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DuplicationNicknameException.class)
    public ErrorResponse handleDuplicateNicknameException(DuplicationNicknameException ex) {
        return ErrorResponse.of(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UsernameLengthException.class)
    public ErrorResponse handleUsernameLengthException(UsernameLengthException ex) {
        return ErrorResponse.of(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UsernamePatternException.class)
    public ErrorResponse handleUsernamePatternException(UsernamePatternException ex) {
        return ErrorResponse.of(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PasswordNotMatchException.class)
    public ErrorResponse handlePasswordNotMatchException(PasswordNotMatchException ex) {
        return ErrorResponse.of(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PasswordLengthException.class)
    public ErrorResponse handlePasswordLengthException(PasswordLengthException ex) {
        return ErrorResponse.of(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PasswordContainsUsernameException.class)
    public ErrorResponse handlePasswordContainsUsernameException(PasswordContainsUsernameException ex) {
        return ErrorResponse.of(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NicknameEmptyException.class)
    public ErrorResponse handleNicknameEmptyException(NicknameEmptyException ex) {
        return ErrorResponse.of(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BoardNotFoundException.class)
    public ErrorResponse BoardNotFoundException(BoardNotFoundException ex) {
        return ErrorResponse.of(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CommentNotFoundException.class)
    public ErrorResponse CommentNotFoundException(CommentNotFoundException ex) {
        return ErrorResponse.of(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NotAuthorizedException.class)
    public ErrorResponse NotAuthorizedException(NotAuthorizedException ex) {
        return ErrorResponse.of(HttpStatus.BAD_REQUEST, ex.getMessage());
    }
}
