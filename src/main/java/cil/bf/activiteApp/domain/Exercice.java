package cil.bf.activiteApp.domain;

import cil.bf.activiteApp.domain.enums.StatutExercice;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SQLDelete(sql = "UPDATE exercice SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
public class Exercice extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private int annee;
    private StatutExercice statut;
    private boolean actif = false;
    private boolean deleted = false;

//    @OneToMany(mappedBy = "idExercice")
//    private List<Programme> programmes;
}
