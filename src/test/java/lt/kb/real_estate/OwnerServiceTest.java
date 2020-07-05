package lt.kb.real_estate;

import lt.kb.real_estate.dao.OwnerDao;
import lt.kb.real_estate.model.Owner;
import lt.kb.real_estate.service.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class OwnerServiceTest {

    @Mock
    OwnerDao ownerDao;

    @InjectMocks
    OwnerService ownerService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllOwnerTest(){
        List<Owner> ownerList=LoadTestData.loadOwnerData();
        when(ownerDao.findAll()).thenReturn(ownerList);
        List<Owner> ownerServiceAll = ownerService.getAll();
        assertEquals(3, ownerServiceAll.size());
    }

    @Test
    void getOwnerByIdTest() {
        when(ownerDao.findById(1L)).thenReturn(Optional.of(LoadTestData.loadOwnerData().get(0)));
        Owner owner = ownerService.findById(1L).get();
        assertEquals("Petras", owner.getFirstName());
        assertEquals("Petraitis", owner.getLastName());
    }
    @Test
    void deletesOwnerTest() {
        ownerService.delete(1L);
        verify(ownerDao, times(1)).deleteById(anyLong());
    }

    @Test
    void addOrUpdateOwnerTest() {
        List<Owner> ownerList = LoadTestData.loadOwnerData();
        Owner owner = ownerList.get(0);
        ownerService.save(owner);
        verify(ownerDao, times(1)).save(any());
    }
    @Test
    void calculateTaxTest() {
        Owner owner = LoadTestData.loadOwnerData().get(1);
        when(ownerDao.findById(2L)).thenReturn(Optional.of(owner));
        BigDecimal yearlyTax = ownerService.ownerAndTaxOutput(2L).getYearlyTax();
        assertEquals(BigDecimal.valueOf(13046.04), yearlyTax, "checking if yearly tax calculation is correct");
    }

}
