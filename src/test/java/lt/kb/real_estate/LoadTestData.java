package lt.kb.real_estate;

import lt.kb.real_estate.model.Address;
import lt.kb.real_estate.model.Building;
import lt.kb.real_estate.model.Owner;
import lt.kb.real_estate.model.PropertyType;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class LoadTestData {
    public static List<Building> loadBuildingData() {
        List<Owner> ownerList = Arrays.asList(
                new Owner(1L, "Petras", "Petraitis"),
                new Owner(2L, "Antanas", "Antanaitis"),
                new Owner(3L, "Jonas", "Jonaitis")
        );
        List<Address> addressList = Arrays.asList(
                new Address("Kaunas", "Taikos", "199"),
                new Address("Vilnius", "Basanavičiaus", "99a"),
                new Address("Prienai", "Liepų", "19"),
                new Address("Alytus", "Kaštonų", "22"),
                new Address("Kaunas", "Baranausko", "2b")
        );

       List<Building> buildinglist=Arrays.asList(
                new Building(1L, addressList.get(0), ownerList.get(0), 55.88, BigDecimal.valueOf(250_000.60), PropertyType.APARTMENT),
                new Building(2L, addressList.get(1), ownerList.get(1), 45.69, BigDecimal.valueOf(500_000.99), PropertyType.APARTMENT),
                new Building(3L, addressList.get(2), ownerList.get(1), 120.50, BigDecimal.valueOf(38000.00), PropertyType.HOUSE),
                new Building(4L, addressList.get(3), ownerList.get(2), 400.62, BigDecimal.valueOf(1000_000.00), PropertyType.INDUSTRIAL),
                new Building(5L, addressList.get(4), ownerList.get(1), 35.20, BigDecimal.valueOf(120000.60), PropertyType.APARTMENT)
        );
        ownerList.get(0).setBuildingList(Arrays.asList(buildinglist.get(0)));
        ownerList.get(1).setBuildingList(Arrays.asList(buildinglist.get(1),buildinglist.get(2),buildinglist.get(4)));
        ownerList.get(2).setBuildingList(Arrays.asList(buildinglist.get(3)));

       return buildinglist;
    }
    public static List<Owner> loadOwnerData(){
        Set<Owner> ownerList=new HashSet<>();
       return loadBuildingData().stream().map(Building::getOwner).distinct(). collect(Collectors.toList());
    }
}
