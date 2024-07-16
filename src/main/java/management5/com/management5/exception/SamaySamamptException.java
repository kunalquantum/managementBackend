package management5.com.management5.exception;

public class SamaySamamptException extends RuntimeException{
    public SamaySamamptException(String message){
        super(message);

    }
    public SamaySamamptException(){
        super("Operation timed out. Please try again later.");
    }
}
