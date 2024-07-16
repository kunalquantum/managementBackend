    package management5.com.management5.globalException;


    import management5.com.management5.exception.WhoAreYouException;
    import org.springframework.http.HttpStatus;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.ControllerAdvice;
    import org.springframework.web.bind.annotation.ExceptionHandler;
    import org.springframework.web.bind.annotation.ResponseStatus;

    @ControllerAdvice
    public class MyExceptionHandler extends RuntimeException {




        @ResponseStatus(value = HttpStatus.FORBIDDEN)
        @ExceptionHandler(value = WhoAreYouException.class)
        public String UnAuthorizedexceptional(Model m)
        {
            m.addAttribute("msg","Unathorized Exception");
            return "UnAuthorized";
        }




    }
