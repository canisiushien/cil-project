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
@SQLDelete(sql = "UPDATE indicateur SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
public class Indicateur extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private  Long valeur;
    private  String libelle;
    private  String reference;
    private  Double cible;
    private  boolean deleted = false;

    @ManyToOne
    @JoinColumn(name = "type_indicateur_id")
    private TypeIndicateur typeIndicateur;

    @ManyToOne
    @JoinColumn(name = "unite_id")
    private Unite unite;






}
