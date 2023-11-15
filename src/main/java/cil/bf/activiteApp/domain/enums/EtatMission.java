package cil.bf.activiteApp.domain.enums;

public enum EtatMission {

    INIITIE("initie"), ANNULE("annule"), VALIDE("valide"),CLOTURE("cloture"), REPROGRAMME("reprogramme");

    String label;

    EtatMission(String _label) {
        this.label = _label;
    }

    public String getLabel() {
        return label;
    }
}
