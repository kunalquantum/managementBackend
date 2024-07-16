package management5.com.management5.exception;

public class InterruptionException extends RuntimeException{
    public InterruptionException(String messsage){
        super(messsage);
    }

    public InterruptionException(){
        super("The operation was interrupted. Please try again later.");
    }
}
