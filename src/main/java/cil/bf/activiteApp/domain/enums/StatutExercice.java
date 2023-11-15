package cil.bf.activiteApp.domain.enums;

public enum StatutExercice {


    ENCOURS("En cours"), CLOTURE("Cloture"), REOUVERT("Reouvert");

    String label;

    StatutExercice(String _label) {
        this.label = _label;
    }

    public String getLabel() {
        return label;
    }
}
