package management5.com.management5.dto.payload;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Adminfile {
    private String username;
    private String path;
    private String origin;
}
