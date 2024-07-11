package management5.com.management5.dto.payload;


import lombok.*;
import management5.com.management5.Enum.Role;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisteration {
    private String username;
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private String confirm_password;
    private String role;
    //private Role role;
}
