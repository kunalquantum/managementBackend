package management5.com.management5.model;

import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "offers")
public class OfferModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID",nullable = false)
    private Long id;

    @Column(name ="Offer_Name",length = 80,nullable = false)
    private String name;

    @Column(name="Start_Date",nullable = false)
    private LocalDateTime startdate;

    @Column(name = "End_Date",nullable = false)
    private LocalDateTime endate;

    @Column(name = "description", nullable = false)
    private String description;
}
