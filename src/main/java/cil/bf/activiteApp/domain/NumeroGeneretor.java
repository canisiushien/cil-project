package cil.bf.activiteApp.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
@Entity
//@SQLDelete(sql = "UPDATE mission SET deleted = true WHERE id=?")
//@Where(clause = "deleted = false")
public class NumeroGeneretor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer numero;
    private Integer typeNumero;
    private int annee;

    public NumeroGeneretor() {
    }

    public NumeroGeneretor(Integer numero, Integer typeNumero, int annee) {
        this.numero = numero;
        this.typeNumero = typeNumero;
        this.annee = annee;
    }

    @Override
    public String toString() {
        return "NumeroGeneretor{" + "id=" + id + ", numero=" + numero + ", typeNumero=" + typeNumero + ", annee=" + annee + '}';
    }
}
