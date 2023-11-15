package cil.bf.activiteApp.domain;

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
@SQLDelete(sql = "UPDATE interim SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
public class Interim extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private String motif;
    private boolean actif = true;
    private boolean deleted = false;

    @ManyToOne
    @JoinColumn(name = "interimaire")
    private Utilisateur interimaire;

    @ManyToOne
    @JoinColumn(name = "responsable")
    private Utilisateur responsable;
}
