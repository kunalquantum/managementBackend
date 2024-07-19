package management5.com.management5.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "events")
public class eventModel {

    private String path;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;

    private String description;

    private Date date;

    private String link;
}
