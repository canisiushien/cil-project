package cil.bf.activiteApp.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import cil.bf.activiteApp.domain.enums.EtatReunion;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SQLDelete(sql = "UPDATE reunion SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
public class Reunion extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private  LocalDate dateReunion;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="hh:mm:ss")
    private  LocalTime heureDebut;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="hh:mm:ss")
    private  LocalTime heureFin;
    private  String ordreJour;
    private  String numeroReunion;
    private  EtatReunion etat;
    private  String intituleSession;
    private  String salleSession;
    private boolean deleted = false;

    @ManyToOne
    @JoinColumn(name = "type_reunion_id")
    private TypeReunion typeReunion;


    @ManyToOne
    @JoinColumn(name = "type_session_id")
    private TypeSession typeSession;

    @ManyToOne
    @JoinColumn(name = "rapporteur", referencedColumnName = "id")
    private Utilisateur rapporteur;

    @ManyToOne
    @JoinColumn(name = "president", referencedColumnName = "id")
    private Utilisateur president;

    @ManyToMany
    @JoinTable(name = "reunion_membres_reunion",
            joinColumns = {@JoinColumn(name = "reunion_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "membres_reunion_id", referencedColumnName = "id")})
    private List<Utilisateur> membresReunion;

    @ManyToMany
    @JoinTable(name = "reunion_document",
            joinColumns = {@JoinColumn(name = "reunion_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "document_id", referencedColumnName = "id")})
    private List<Document> documents;
}
