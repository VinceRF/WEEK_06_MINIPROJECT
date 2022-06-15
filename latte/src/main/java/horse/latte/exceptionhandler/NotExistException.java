package horse.latte.exceptionhandler;

public class NotExistException extends RuntimeException{

    private static final String MESSAGE = "존재하지 않습니다.";
    public NotExistException() {
        super(MESSAGE);
    }
}
