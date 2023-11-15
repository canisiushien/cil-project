package cil.bf.activiteApp.domain.enums;

public enum EtatMandatControle {

    INIITIE("initie"), ANNULE("annule"), VALIDE("valide"),CLOTURE("cloture"), REPROGRAMME("reprogramme");

    String label;

    EtatMandatControle(String _label) {
        this.label = _label;
    }

    public String getLabel() {
        return label;
    }
}
