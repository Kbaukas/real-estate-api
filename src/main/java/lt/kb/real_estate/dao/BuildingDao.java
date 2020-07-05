package lt.kb.real_estate.dao;


import lt.kb.real_estate.model.Building;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BuildingDao extends JpaRepository<Building, Long> {
    @Override
    @EntityGraph(attributePaths={"owner"})
    List<Building> findAll();
    @Override
    @EntityGraph(attributePaths={"owner"})
    Optional<Building> findById(Long id);

    @Query("select b from buildings b where b.owner.id= :filter")
    List<Building> findByFilter(@Param("filter") Long filter);

}

