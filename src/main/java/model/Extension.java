package model;

public enum Extension {
    EXCEL(".xlsx"),
    CSV(".csv");

    String extStr;
    Extension(String extStr) {
        this.extStr = extStr;
    }

    @Override
    public String toString() {
        return this.extStr;
    }
}
