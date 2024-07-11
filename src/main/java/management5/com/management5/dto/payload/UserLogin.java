package management5.com.management5.dto.payload;

import lombok.*;
import org.springframework.context.annotation.Bean;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLogin {
    private String username;
    private String email;
    private String password;
}

