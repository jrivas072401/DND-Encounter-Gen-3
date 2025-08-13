package ttrpg.encounterGen.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ttrpg.encounterGen.models.Monster;

import java.util.List;

@Repository
public interface MonsterRepository extends JpaRepository<Monster, Long> {
    // Find monsters containing a specific biome
    List<Monster> findByBiomesContaining(String biome);

    // Example of other query methods
    List<Monster> findByCrGreaterThan(double cr);
}
