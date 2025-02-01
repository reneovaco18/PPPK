package hr.algebra.medicalsystem.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ExaminationFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ExaminationFile.java
    @ManyToOne
    @JoinColumn(name = "examination_id", nullable = false)
    @JsonBackReference
    private Examination examination;


    private String filePath;  // e.g., URL to the image/file in S3
}
