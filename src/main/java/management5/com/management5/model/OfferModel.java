package management5.com.management5.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
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

    @Column(name ="Offer Name",length = 80,nullable = false)
    private String offer_name;

    @Column(name="Start Date",nullable = false)
    private LocalDateTime start_date;

    @Column(name = "End Date",nullable = false)
    private LocalDateTime end_date;

    @Column(name = "Description", nullable = false)
    private String Description;
}
