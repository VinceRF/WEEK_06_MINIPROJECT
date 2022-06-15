package horse.latte.exceptionhandler;

public class CommentNotFoundException extends RuntimeException{
    public CommentNotFoundException (String message) {
        super(message);
    }
}
