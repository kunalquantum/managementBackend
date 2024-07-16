package management5.com.management5;

import management5.com.management5.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(value = WhoAreYouException.class)
    public ResponseEntity<?> UnAuthorizedException(WhoAreYouException e){

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }
    @ExceptionHandler(value = ResourceNahiMilaException.class)
    public ResponseEntity<?> resourseNotFoundException(ResourceNahiMilaException e){

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(value = DatabaseConnectivityException.class)
    public ResponseEntity<?> exceptionhandlerbadrequest(DatabaseConnectivityException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(value = InterruptionException.class)
    public ResponseEntity<?> exceptionhandlerbadrequest(InterruptionException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(value = SamaySamamptException.class)
    public ResponseEntity<?> exceptionhandlerbadrequest(SamaySamamptException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(value = ParsingException.class)
    public ResponseEntity<?> exceptionhandlerbadrequest(ParsingException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }








}
