package cil.bf.activiteApp.domain.enums;

public enum EtatReunion {

    INIITIE("initiee"), ANNULE("annulee"), VALIDE("validee"),CLOTURE("cloturee"), REPROGRAMME("reprogrammee");

    String label;

    EtatReunion(String _label) {
        this.label = _label;
    }

    public String getLabel() {
        return label;
    }
}
