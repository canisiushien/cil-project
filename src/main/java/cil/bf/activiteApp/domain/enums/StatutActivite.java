package cil.bf.activiteApp.domain.enums;

public enum StatutActivite {

    PASCOMMENCEE("Pas commencee"), ENCOURS("En cours"), TERMINEE("Terminee");

    String label;

    StatutActivite(String _label) {
        this.label = _label;
    }

    public String getLabel() {
        return label;
    }
}
