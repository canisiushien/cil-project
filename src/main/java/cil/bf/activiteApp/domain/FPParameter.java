package cil.bf.activiteApp.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import lombok.Data;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * parametrage et correspondance avec des cle/valeur (au lieu de
 * application.yml)
 *
 * @author Canisius <canisiushien@gmail.com>
 */
@Data
@Entity
@Table(name = "fp_parameter")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class FPParameter implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Integer id;

    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "code", length = 5, unique = true)
    private String code;

    @Column(name = "param_key", unique = true)
    private String key;

    @Column(name = "param_value")
    private String value;

    @Column(name = "param_desc")
    private String description;

    @Column(name = "param_parent", length = 5)
    private String parent;

}
