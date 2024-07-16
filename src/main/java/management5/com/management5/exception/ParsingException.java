package management5.com.management5.exception;

public class ParsingException extends RuntimeException{
    public ParsingException(String message){
        super(message);
    }
    public ParsingException(){
        super("Invalid data format. Please check the input data and try again.");
    }
}
