package model;

enum Extension {
    EXCEL(".xlsx"),
    CSV(".csv"),
    JSON(".json");
    String extStr;
    Extension(String extStr) {
        this.extStr = extStr;
    }

    @Override
    public String toString() {
        return this.extStr;
    }
}
