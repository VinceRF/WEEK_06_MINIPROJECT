package horse.latte.exceptionhandler;

public class TokenInvalidException extends RuntimeException {
    public TokenInvalidException(String message) {
        super(message);
    }
}
