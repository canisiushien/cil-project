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
import cil.bf.activiteApp.domain.enums.EtatMission;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SQLDelete(sql = "UPDATE mission SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
public class Mission extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private  String numMission;
    private  Double budget;
    private  LocalDate dateDebut;
    private  LocalDate dateFin;
    private  String lieu;
    private  EtatMission etat;
    private  String immatriculation;
    private  boolean deleted = false;

    @OneToMany
    private List<Utilisateur> participants;

    @ManyToMany
    @JoinTable(name = "mission_document",
            joinColumns = {@JoinColumn(name = "mission_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "document_id", referencedColumnName = "id")})
    private List<Document> documents;
}
