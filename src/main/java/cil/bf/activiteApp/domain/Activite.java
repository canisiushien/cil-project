package cil.bf.activiteApp.domain;

import cil.bf.activiteApp.domain.enums.StatutActivite;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SQLDelete(sql = "UPDATE activite SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
public class Activite extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private  String code;
    private  String libelle;
    private StatutActivite statut;
    private  Double coutPrevisionnel;
    private  Double coutReel;
    private  String uniteReel;
    private  Double cibleReel;
    private  String responsable;
    private LocalDate dateDebut;
    private  LocalDate dateFin;
    private  String lieu;
    private boolean deleted = false;

    @ManyToOne
    @JoinColumn(name = "idObjectifOperationnel")
    private ObjectifOperationnel idObjectifOperationnel;

    @ManyToOne
    @JoinColumn(name = "indicateur_id")
    private Indicateur indicateur;

    @ManyToMany
    @JoinTable(name = "activite_document",
            joinColumns = {@JoinColumn(name = "activite_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "document_id", referencedColumnName = "id")})
    private List<Document> documents;
}
