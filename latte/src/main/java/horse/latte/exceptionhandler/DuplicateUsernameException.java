package horse.latte.exceptionhandler;

public class DuplicateUsernameException extends RuntimeException {

    private static final String MESSAGE = "중복된 ID가 있습니다.";
    public DuplicateUsernameException() {
        super(MESSAGE);
    }
}
