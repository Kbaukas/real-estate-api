package lt.kb.real_estate.model;

public enum PropertyType {
    APARTMENT(0.02),
    HOUSE(0.017),
    INDUSTRIAL(0.035);
    private final Double taxRate;

    PropertyType(Double taxRate) {
        this.taxRate = taxRate;
    }

    public Double getTaxRate() {
        return taxRate;
    }
}
