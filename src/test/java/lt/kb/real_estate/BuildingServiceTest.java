package lt.kb.real_estate;


import lt.kb.real_estate.dao.BuildingDao;
import lt.kb.real_estate.dao.OwnerDao;
import lt.kb.real_estate.model.Building;
import lt.kb.real_estate.model.PropertyType;
import lt.kb.real_estate.service.BuildingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BuildingServiceTest {
    @Mock
    BuildingDao buildingDao;

    @Mock
    OwnerDao ownerDao;

    @InjectMocks
    BuildingService buildingService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllBuildingsTest() {
        List<Building> buildingList = LoadTestData.loadBuildingData();
        when(buildingDao.findAll()).thenReturn(buildingList);
        List<Building> allBuildings = buildingService.findAll();
        assertEquals(5, allBuildings.size());
    }

    @Test
    void getBuildingByIDTest() {
        List<Building> buildingList = LoadTestData.loadBuildingData();
        when(buildingDao.findById(1L)).thenReturn(Optional.of(buildingList.get(0)));
        Building building = buildingService.findById(1L).get();
        assertEquals(1, building.getId());
        assertEquals("Kaunas", building.getAddress().getCity());
        assertEquals("Taikos", building.getAddress().getStreet());
        assertEquals("199", building.getAddress().getNumber());
        assertEquals(1L, building.getOwner().getId());
        assertEquals(PropertyType.APARTMENT, building.getPropertyType());


    }

    @Test
    void addOrUpdateBuildingTest() {
        List<Building> buildingList = LoadTestData.loadBuildingData();
        Building building = buildingList.get(0);
        buildingService.save(building);
        verify(buildingDao, times(1)).save(any());
    }

    @Test
    void deletesBuildingTest() {
        buildingService.delete(anyLong());
        verify(buildingDao, times(1)).deleteById(anyLong());

    }
}

