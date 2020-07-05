package lt.kb.real_estate.utils;

import lt.kb.real_estate.model.Building;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class TaxCalculator {

    public static BigDecimal getYearlyTax(List<Building> buildingsList) {
        if(buildingsList.isEmpty()) return BigDecimal.valueOf(0);
        return  buildingsList.stream().map(building ->
                building.getMarketValue()
                        .multiply(BigDecimal.valueOf(building.getPropertyType()
                                .getTaxRate()))).reduce(BigDecimal::add).orElse(BigDecimal.valueOf(0)).setScale(2, RoundingMode.UP);
    }
}
