package management5.com.management5.dto.payload;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateOfferEndDate {
    private String name;
    private LocalDateTime end_date;
}
