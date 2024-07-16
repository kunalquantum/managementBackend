package management5.com.management5.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNahiMilaException extends RuntimeException {
    public ResourceNahiMilaException(String message) {
        super(message);


    }
    public ResourceNahiMilaException(){
        super("The requested resource could not be found.");
    }
}
