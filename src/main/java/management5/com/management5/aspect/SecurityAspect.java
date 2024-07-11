package management5.com.management5.aspect;


import jakarta.ws.rs.core.SecurityContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component

public class SecurityAspect {


    private static final Logger logger = LoggerFactory.getLogger(SecurityAspect.class);

    @After("execution(* management5.com.management5.controller.*.*(..))")
    public void loggerUserActivity(JoinPoint joinPoint)
    {
        String username=getUsernameFromSecurityContext();

        logger.info("User:{} executed method {} in class {} sourceLocation {}",
                username,
                joinPoint.getSignature().getName(),
                joinPoint.getTarget().getClass().getSimpleName(),
                joinPoint.getSourceLocation()
        );
    }
    private String getUsernameFromSecurityContext(){

        return SecurityContext.BASIC_AUTH;
    }

}
