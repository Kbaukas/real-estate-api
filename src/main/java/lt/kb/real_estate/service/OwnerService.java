package lt.kb.real_estate.service;


import lt.kb.real_estate.dao.OwnerDao;
import lt.kb.real_estate.exception.CustomNotFoundException;
import lt.kb.real_estate.model.Owner;
import lt.kb.real_estate.utils.OutputOwnerAndTax;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static lt.kb.real_estate.utils.TaxCalculator.getYearlyTax;

@Service
public class OwnerService {
    @Autowired
    OwnerDao ownerDao;

    public List<Owner> getAll() {
        return ownerDao.findAll();
    }

    public Optional<Owner> findById(Long id) {
        return ownerDao.findById(id);
    }

    public void save(Owner owner) {
        ownerDao.save(owner);
    }

    public void delete(Long id) {
        ownerDao.deleteById(id);
    }

    public OutputOwnerAndTax ownerAndTaxOutput(Long ownerId) {
        BigDecimal yearlyTax;
        Owner owner = ownerDao.findById(ownerId).orElseThrow(() -> new CustomNotFoundException("owner not found  with id " + ownerId));

        if (owner.getBuildingList().isEmpty()) {
            return new OutputOwnerAndTax(owner, new BigDecimal(0));

        } else {
            yearlyTax = getYearlyTax(owner.getBuildingList());
            return new OutputOwnerAndTax(owner, yearlyTax);


        }
    }
}


