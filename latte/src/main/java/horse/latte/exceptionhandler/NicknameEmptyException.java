package horse.latte.exceptionhandler;

public class NicknameEmptyException extends RuntimeException{

    private static final String MESSAGE = "닉네임을 입력해주세요.";
    public NicknameEmptyException() {
        super(MESSAGE);
    }
}
