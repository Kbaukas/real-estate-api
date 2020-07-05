package lt.kb.real_estate;

import lt.kb.real_estate.controller.BuildingRestController;
import lt.kb.real_estate.dao.BuildingDao;
import lt.kb.real_estate.model.Building;
import lt.kb.real_estate.model.PropertyType;
import lt.kb.real_estate.service.BuildingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BuildingControllerTest {
    @Mock
    BuildingDao buildingDao;


    @Mock
    private BuildingService buildingService;

    @InjectMocks
    private BuildingRestController buildingRestController;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllBuildingsTest() {
        List<Building> buildings = LoadTestData.loadBuildingData();
        System.out.println(buildings);
        Mockito.when(buildingService.findAll()).thenReturn(buildings);
        List<Building> buildingList = buildingRestController.getAllBuildings();
        assertEquals(5, buildingList.size(), "checking if correct size of data list");
    }

    @Test
    public void getdBuildingByIdTest() {
        Building building = LoadTestData.loadBuildingData().get(0);
        Mockito.when(buildingService.findById(1L)).thenReturn(Optional.of(building));
        Building buildingRecordById = buildingRestController.getdBuildingById(1L);
        assertEquals(1, building.getId());
        assertEquals("Kaunas", building.getAddress().getCity());
        assertEquals("Taikos", building.getAddress().getStreet());
        assertEquals("199", building.getAddress().getNumber());
        assertEquals(1L, building.getOwner().getId());
        assertEquals(PropertyType.APARTMENT, building.getPropertyType());
    }

    @Test
    public void updateBuildingTest() {
        Building building = LoadTestData.loadBuildingData().get(0);
        buildingRestController.updateBuilding(building);
        Mockito.verify(buildingService).save(building);
    }

    @Test
    public void addBuildingTest() {
        Building building = LoadTestData.loadBuildingData().get(0);
        buildingRestController.addBuilding(building);
        Mockito.verify(buildingService).save(building);
    }

    @Test
    public void deleteBuilding() {
        Building building = LoadTestData.loadBuildingData().get(0);
        Mockito.when(buildingService.findById(1L)).thenReturn(Optional.of(building));
        Building buildingRecordById = buildingRestController.getdBuildingById(1L);
        assertEquals(1, building.getId());
        buildingRestController.deleteBuilding(1L);
        Mockito.verify(buildingService).delete(1L);
    }
//TODO exeption haldling tests

}
