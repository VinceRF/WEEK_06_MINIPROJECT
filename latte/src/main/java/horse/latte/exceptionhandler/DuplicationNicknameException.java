package horse.latte.exceptionhandler;

public class DuplicationNicknameException extends RuntimeException{

    private static final String MESSAGE = "중복된 닉네임이 있습니다.";
    public DuplicationNicknameException() {
        super(MESSAGE);
    }
}