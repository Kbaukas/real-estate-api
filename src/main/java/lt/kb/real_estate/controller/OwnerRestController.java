package lt.kb.real_estate.controller;

import lt.kb.real_estate.exception.CustomNotFoundException;
import lt.kb.real_estate.model.Owner;
import lt.kb.real_estate.service.OwnerService;
import lt.kb.real_estate.utils.OutputOwnerAndTax;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

import static lt.kb.real_estate.utils.TaxCalculator.getYearlyTax;

/**
 * This class provides http responses associated with Owner entity and provides response for yearly tax  calculation.
 * for requested owner and for all owners.
 */
@RestController
@RequestMapping("/api")

public class OwnerRestController {
    @Resource
    private OwnerService ownerService;

    @GetMapping("/owners")
    public List<Owner> getAllOwners() {
        return ownerService.getAll();
    }

    @GetMapping("/owners/{id}")
    public Owner getOwnerById(@PathVariable Long id) {
        System.out.println(ownerService.findById(id));
        return ownerService.findById(id).orElseThrow(() -> new CustomNotFoundException("owner not found  with id " + id));
    }

    @DeleteMapping("/owners/{id}")
    public void deleteBuiding(@PathVariable Long id) {
        ownerService.delete(id);
    }

    //    Method show  yearly tax for requested owner
    @GetMapping("/owners/tax/{id}")
    public OutputOwnerAndTax getTaxByOwnerId(@PathVariable Long id) {

        return ownerService.ownerAndTaxOutput(id);


    }

    //    Method shows yearly tax for all owners
    @GetMapping("/owners/tax")
    public List<OutputOwnerAndTax> getTaxForAllOwners() {
        return ownerService.getAll().stream()
                .map(owner -> new OutputOwnerAndTax(owner, getYearlyTax(owner.getBuildingList())))
                .collect(Collectors.toList());

    }
}





