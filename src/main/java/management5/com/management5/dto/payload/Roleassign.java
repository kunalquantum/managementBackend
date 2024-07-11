package management5.com.management5.dto.payload;

import lombok.*;
import management5.com.management5.Enum.Role;

@Getter

@Setter

@AllArgsConstructor

@NoArgsConstructor

@Builder

public class Roleassign {

    private String username;

    private Role role;
}
