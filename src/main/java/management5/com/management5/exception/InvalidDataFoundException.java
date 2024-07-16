package management5.com.management5.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvalidDataFoundException extends RuntimeException{
    public InvalidDataFoundException(String message){super(message);}

    public InvalidDataFoundException(){
        super("Error: Invalid data. Please check the data and try again.");
    }
}
