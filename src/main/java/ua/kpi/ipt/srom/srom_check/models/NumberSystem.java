package ua.kpi.ipt.srom.srom_check.models;

public enum NumberSystem {
    BIN(2), HEX(16), OCTAL(8), DECIMAL(10);

    private final int numberSystem;

    NumberSystem(int numberSystem) {
        this.numberSystem = numberSystem;
    }

    public int getValue() {
        return numberSystem;
    }
}
