package horse.latte.exceptionhandler;

public class UsernameLengthException extends RuntimeException{

    private static final String MESSAGE = "ID는 4자 이상입니다.";
    public UsernameLengthException() {
        super(MESSAGE);
    }
}
