package management5.com.management5.dto.payload;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class updateoffer {
    private String Description;
    private LocalDateTime start_date;
    private LocalDateTime end_date;
}
