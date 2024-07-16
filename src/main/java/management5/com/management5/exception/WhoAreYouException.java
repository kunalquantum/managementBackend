package management5.com.management5.exception;


public class WhoAreYouException extends RuntimeException{
    public WhoAreYouException(String message){super(message);}

    public WhoAreYouException(){
        super("Error 401: Unauthorized. You do not have access to this resource.");
    }
}
