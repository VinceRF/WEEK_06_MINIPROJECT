package horse.latte.exceptionhandler;

public class DuplicationNicknameException extends RuntimeException{
    public DuplicationNicknameException(String message) {
        super(message);
    }
}