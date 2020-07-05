package lt.kb.real_estate.service;


import lt.kb.real_estate.dao.BuildingDao;
import lt.kb.real_estate.model.Building;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BuildingService {
    @Autowired
    BuildingDao buildingDao;

    public List<Building> findAll() {
        return buildingDao.findAll();
    }

    public Optional<Building> findById(Long id) {
        return buildingDao.findById(id);
    }


    public void save(Building building) {
        buildingDao.save(building);
    }

    public void delete(Long id) {
        buildingDao.deleteById(id);
    }

   public List<Building> findAllByOwnerId(Long id){
        return buildingDao.findByFilter(id);    }


}

