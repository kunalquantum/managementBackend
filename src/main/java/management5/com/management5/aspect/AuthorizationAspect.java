package management5.com.management5.aspect;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import management5.com.management5.annotations.Authorization;
import management5.com.management5.exception.UnauthorizedException;
import management5.com.management5.model.UserModel;
import management5.com.management5.repository.UserRepository;
import management5.com.management5.service.UserService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Optional;

@AllArgsConstructor
@Component
@Aspect
public class AuthorizationAspect {

    private final UserService userService;
    private final UserRepository userRepository;

    @Around("@annotation(authorization)")
    public Object auditMethod(ProceedingJoinPoint joinPoint, Authorization authorization) throws Throwable {
        HttpServletRequest request = getRequestFromJoinPoint(joinPoint);

        String jwtToken = resolveToken(request);

        String username = userService.checkUsernameByToken(jwtToken);

        Optional<UserModel> userOptional = userRepository.findByUsername(username);

        if (userOptional.isPresent()) {
            // User is authenticated, proceed with the original method execution
            Object result = joinPoint.proceed();
            return result;
        } else {
            throw new UnauthorizedException("Unauthorized: Invalid user");
        }
    }

    private HttpServletRequest getRequestFromJoinPoint(ProceedingJoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof HttpServletRequest) {
                return (HttpServletRequest) arg;
            }
        }
        throw new IllegalArgumentException("No HttpServletRequest found in method arguments");
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7); // Remove "Bearer " prefix
        }
        return null; // Invalid or missing Authorization header
    }
}
