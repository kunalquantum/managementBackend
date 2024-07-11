package management5.com.management5.model;

import jakarta.persistence.*;
import lombok.*;
import management5.com.management5.Enum.Role;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "courses")
public class CourseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "java_fullstack",nullable = false,unique = true)
    private String JavaFullstack;

    @Column(name = "python_fullstack",nullable = false)
    private String PythonFullstack;

    @Column(name = "avaiation",nullable = false)
    private String Aviation;

    @Column(name = "data_science",nullable = false,unique = true)
    private String DataScience;

    @Column(name = "Designing",nullable = false)
    private String Designing;



}




