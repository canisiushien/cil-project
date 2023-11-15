package cil.bf.activiteApp.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SQLDelete(sql = "UPDATE periodicite SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
public class Periodicite extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private  String libelle;
    private  boolean deleted = false;

    @ManyToMany
    @JoinTable(name = "periodicite_activite",
            joinColumns = {@JoinColumn(name = "periodicite_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "activite_id", referencedColumnName = "id")})
    private List<Activite> activites;
}
