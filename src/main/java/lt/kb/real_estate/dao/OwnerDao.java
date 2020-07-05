package lt.kb.real_estate.dao;

import lt.kb.real_estate.model.Owner;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OwnerDao extends JpaRepository<Owner, Long> {
    @Override
    @EntityGraph(attributePaths = {"buildingList"})
    List<Owner> findAll();

    @EntityGraph(attributePaths = {"buildingList"})
    Optional<Owner> findById(Long id);

}
