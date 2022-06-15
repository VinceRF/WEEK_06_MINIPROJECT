package horse.latte.exceptionhandler;

public class PasswordLengthException extends RuntimeException{

    private static final String MESSAGE = "비밀번호는 8자 이상입니다.";
    public PasswordLengthException() {
        super(MESSAGE);
    }
}
