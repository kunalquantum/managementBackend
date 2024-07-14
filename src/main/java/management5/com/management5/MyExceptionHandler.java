package management5.com.management5;

import org.bouncycastle.math.raw.Mod;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class MyExceptionHandler {
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = NullPointerException.class)
    public String exceptionhandlerNull(Model m){
        m.addAttribute("msg","Null pointer excception");
        return "Null_page";
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = NumberFormatException.class)
    public String exceptionhandlerNumberFormatException(Model m){
        m.addAttribute("msg","Number format excception has occured");
        return "Null_page";
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public String exceptionhandlerGeneric(Model m){
        m.addAttribute("msg","excception has occured ");
        return "Null_page";
    }



}
