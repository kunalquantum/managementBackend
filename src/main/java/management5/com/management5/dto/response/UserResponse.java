package management5.com.management5.dto.response;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private String first_name;
    private String last_name;
    private String email;
    private String username;
    private String password;
}
