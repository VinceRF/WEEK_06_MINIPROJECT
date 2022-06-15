package horse.latte.exceptionhandler;

public class BoardNotFoundException extends RuntimeException{
    public BoardNotFoundException (String message) {
        super(message);
    }
}
