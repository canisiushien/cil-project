package cil.bf.activiteApp.domain;

import cil.bf.activiteApp.config.Constants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

/**
 * Les utilisateurs de l'appli ne sont pas tous des fonctionnaires. Donc on
 * prevoit un username en plus du matricule.
 *
 * Un utilisateur peut avoir plusieurs profils.
 *
 * @author Canisius <canisiushien@gmail.com>
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "utilisateur")
@SQLDelete(sql = "UPDATE utilisateur SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Utilisateur extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Pattern(regexp = Constants.LOGIN_REGEX)
    @Size(min = 1, max = 50)
    @Column(length = 50, unique = true)
    private String matricule;

    @Size(max = 50)
    @Column(name = "nom", length = 50)
    private String nom;

    @Size(max = 150)
    @Column(name = "prenom", length = 150)
    private String prenom;

    @Column(name = "contact", length = 20, unique = true)
    private String contact;

    @NotNull
    @Column(nullable = false)
    private boolean actif = false;

    @Size(max = 255)
    @Column(name = "activation_key", length = 255)
    @JsonIgnore
    private String activationKey;

    @Size(max = 255)
    @Column(name = "reset_key", length = 255)
    @JsonIgnore
    private String resetKey;

    @Column(name = "reset_date")
    private Instant resetDate = null;

    @Column(name = "confirmation_expire_date")
    private LocalDateTime confirmationExpireDate;

    @Column(name = "reset_expire_date")
    private LocalDateTime resetExpireDate;

    @NotNull
    @Pattern(regexp = Constants.LOGIN_REGEX)
    @Size(min = 1, max = 50)
    @Column(name = "login", length = 50, unique = true, nullable = false)
    private String login;

    @JsonIgnore
    @NotNull
    @Size(min = 2, max = 60)
    @Column(name = "password_hash", length = 60, nullable = false)
    private String password;

    @Email
    @Size(min = 5, max = 254)
    @Column(length = 254, unique = true)
    private String email;

    private boolean deleted = false;

    @ManyToOne
    @JoinColumn(name = "fonction_id")
    private Fonction fonction;

    @ManyToOne
    @JoinColumn(name = "direction_id", nullable = false)
    private Direction direction;

    @ManyToMany(fetch = FetchType.EAGER)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(
            name = "utilisateur_profil",
            joinColumns = @JoinColumn(name = "utilisateur_id"),
            inverseJoinColumns = @JoinColumn(name = "profil_id")
    )
    @BatchSize(size = 20)
    private Set<Profil> profils = new HashSet<>();

}
