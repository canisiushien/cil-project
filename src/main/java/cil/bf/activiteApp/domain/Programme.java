package cil.bf.activiteApp.domain;

import jakarta.persistence.*;
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
@SQLDelete(sql = "UPDATE programme SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
public class Programme extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String libelle;
    private Double cout;
    private Double coutReel;
    private String uniteReel;
    private Double cibleReel;
    private boolean deleted = false;

    @ManyToOne
    @JoinColumn(name = "exercice_id")
    private Exercice exercice;

}
