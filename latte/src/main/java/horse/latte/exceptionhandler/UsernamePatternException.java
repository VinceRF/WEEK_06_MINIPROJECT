package horse.latte.exceptionhandler;

public class UsernamePatternException extends RuntimeException{
    private static final String MESSAGE = "ID는 알파벳 대소문자와 숫자로 입력해주세요.";
    public UsernamePatternException() {
        super(MESSAGE);
    }
}
