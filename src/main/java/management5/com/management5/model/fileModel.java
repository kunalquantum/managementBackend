package management5.com.management5.model;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "files")
public class fileModel {



    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "name",unique = true)
    private String name;

    @Column(name = "path",nullable = false)
    private String path;


    @Column(name = "File name ")
    private String origin;
}
