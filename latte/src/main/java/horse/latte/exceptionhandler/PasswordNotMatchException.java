package horse.latte.exceptionhandler;

public class PasswordNotMatchException extends RuntimeException{
    private static final String MESSAGE = "비밀번호와 비밀번호 확인의 입력값이 일치하지 않습니다.";
    public PasswordNotMatchException() {
        super(MESSAGE);
    }
}
