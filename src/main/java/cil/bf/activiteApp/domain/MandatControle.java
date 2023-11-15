package cil.bf.activiteApp.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import java.time.LocalDate;
import java.util.List;
import cil.bf.activiteApp.domain.enums.EtatMandatControle;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SQLDelete(sql = "UPDATE mandat_controle SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
public class MandatControle extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private  String libelle;
    private  String numSession;
    private  String numDossierDecla;
    private  String motif;
    private  LocalDate dateDebut;
    private  LocalDate dateFin;
    private  String typeMission;
    private  EtatMandatControle etat;
    private  boolean deleted = false;


    @ManyToMany
    private List<Utilisateur> participants;

    @OneToMany
    private List<TypeControleMission> typeControleMissions;

    @ManyToMany
    @JoinTable(name = "mandat_document",
            joinColumns = {@JoinColumn(name = "mandatControle_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "document_id", referencedColumnName = "id")})
    private List<Document> documents;
}
