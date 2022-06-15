package horse.latte.exceptionhandler;

public class PasswordContainsUsernameException extends RuntimeException{

    private static final String MESSAGE = "비밀번호에 ID를 포함할 수 없습니다.";
    public PasswordContainsUsernameException() {
        super(MESSAGE);
    }

}
