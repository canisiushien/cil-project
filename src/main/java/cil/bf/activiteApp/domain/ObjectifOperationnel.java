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
@SQLDelete(sql = "UPDATE objectif_operationnel SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
public class ObjectifOperationnel extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String libelle;
    private Double cout;
    private Double coutReel;
    private Double cibleReel;
    private String uniteReel;
    private boolean deleted = false;

    @ManyToOne
    @JoinColumn(name = "objectifOperationnelParent")
    private ObjectifOperationnel objectifOperationnelParent;

    @ManyToOne
    @JoinColumn(name = "action_id")
    private Action action;

    @ManyToOne
    @JoinColumn(name = "indicateur_id")
    private Indicateur indicateur;
}
