package lt.kb.real_estate.controller;

import lt.kb.real_estate.exception.CustomNotFoundException;
import lt.kb.real_estate.model.Building;
import lt.kb.real_estate.service.BuildingService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * Class provides all http responses  associated with Building entity.
 */
@RestController
@RequestMapping("/api")
public class BuildingRestController {
    @Resource
    private BuildingService buildingService;

    //get all building records
    @GetMapping("/buildings")
    public List<Building> getAllBuildings() {
        return buildingService.findAll();
    }

    //get building record by id
    @GetMapping("/buildings/{id}")
    public Building getdBuildingById(@PathVariable Long id) {

        return buildingService.findById(id).orElseThrow(() -> new CustomNotFoundException("building not found  with id " + id));
    }

    //Update building record
    @PutMapping("/buildings")
    public Building updateBuilding(@RequestBody Building building) {

        buildingService.save(building);
        return building;
    }

    //delete building record
    @DeleteMapping("/buildings/{id}")
    public void deleteBuilding(@PathVariable Long id) {
        Optional<Building> building = buildingService.findById(id);
        if (!building.isPresent()) {
            throw new CustomNotFoundException("building not found  with id " + id);
        }
        buildingService.delete(id);

    }

    //add building record
    @PostMapping("/buildings")
    public Building addBuilding(@RequestBody Building building) {
        buildingService.save(building);
        return building;
    }


}

