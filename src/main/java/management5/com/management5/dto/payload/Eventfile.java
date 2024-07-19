package management5.com.management5.dto.payload;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Eventfile {

    private String username;
    private String description;
    private Date date;
    private String link;

}
