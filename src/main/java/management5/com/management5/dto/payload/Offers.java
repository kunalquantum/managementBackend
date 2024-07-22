package management5.com.management5.dto.payload;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Offers {

    private String offer_name;
    private LocalDateTime start_date;
    private LocalDateTime end_date;
    private String Description;
}
