package lt.kb.real_estate.utils;

import lt.kb.real_estate.model.Owner;

import java.math.BigDecimal;

public class OutputOwnerAndTax {
    private Owner owner;
    private BigDecimal yearlyTax;

    public OutputOwnerAndTax() {
    }

    public OutputOwnerAndTax(Owner owner, BigDecimal yearlyTax) {
        this.owner = owner;
        this.yearlyTax = yearlyTax;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public BigDecimal getYearlyTax() {
        return yearlyTax;
    }

    public void setYearlyTax(BigDecimal yearlyTax) {
        this.yearlyTax = yearlyTax;
    }
}
