package horse.latte.exceptionhandler;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException (String message) {
        super(message);
    }
}
