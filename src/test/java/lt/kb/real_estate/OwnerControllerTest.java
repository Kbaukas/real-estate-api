package lt.kb.real_estate;


import lt.kb.real_estate.controller.OwnerRestController;
import lt.kb.real_estate.model.Owner;
import lt.kb.real_estate.service.OwnerService;
import lt.kb.real_estate.utils.OutputOwnerAndTax;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;

public class OwnerControllerTest {


    @Mock
    private OwnerService ownerService;

    @InjectMocks
    private OwnerRestController ownerRestController;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getTaxForAllOwnersTest() {
        List<Owner> ownerList = LoadTestData.loadOwnerData();
        when(ownerService.getAll()).thenReturn(ownerList);

        List<BigDecimal> yearlyTaxList = ownerRestController.getTaxForAllOwners().stream().map(OutputOwnerAndTax::getYearlyTax).collect(Collectors.toList());
        assertEquals(BigDecimal.valueOf(5000.02), yearlyTaxList.get(0));
        assertEquals(BigDecimal.valueOf(13046.04), yearlyTaxList.get(1));
        assertEquals(BigDecimal.valueOf(35000.00).setScale(2, RoundingMode.UP), yearlyTaxList.get(2));
    }

    @Test
    void getAllOwnerTest(){
        List<Owner> ownerList=LoadTestData.loadOwnerData();
        when(ownerService.getAll()).thenReturn(ownerList);
        List<Owner> ownerServiceAll = ownerRestController.getAllOwners();
        assertEquals(3, ownerServiceAll.size());
    }

    @Test
    void getOwnerByIdTest() {
        when(ownerService.findById(1L)).thenReturn(Optional.of(LoadTestData.loadOwnerData().get(0)));
        Owner owner = ownerRestController.getOwnerById(1L);
        assertEquals("Petras", owner.getFirstName());
        assertEquals("Petraitis", owner.getLastName());
    }
    @Test
    void deletesOwnerTest() {
        ownerRestController.deleteBuiding(1L);
        verify(ownerService, times(1)).delete(anyLong());
    }
//TODO exeption haldling tests
}
