package management5.com.management5.aspect;


import lombok.extern.slf4j.Slf4j;
import management5.com.management5.model.UserModel;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class GeneralAspectInspector
{

    @Pointcut("execution(* management5.com.management5.controller.*.*(..))")
    public void loggingpoint(){

    }


    @Before("loggingpoint()")
    public void before(JoinPoint joinPoint)
    {
        log.info("Before method invoked "+joinPoint.getSignature());
    }


    @After("loggingpoint()")
    public void after(JoinPoint joinPoint){
        log.info("after method invoked "+joinPoint.getSignature());

    }

    @AfterThrowing(value = "execution(* management5.com.management5.controller.*.*(..))",
            throwing = "userModel")
    public void returning(JoinPoint joinPoint, UserModel userModel){
        log.info("AfterReturning"+userModel);
        log.warn("hello");
    }

    @AfterReturning(value ="execution(* management5.com.management5.controller.*.*(..))", returning = "userModel")
    public void throwing(JoinPoint joinPoint,UserModel userModel)
    {
        log.info("AfterThrowing"+userModel);
    }




}
