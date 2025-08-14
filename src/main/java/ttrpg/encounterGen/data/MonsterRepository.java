package ttrpg.encounterGen.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ttrpg.encounterGen.models.Monster;

import java.util.List;

@Repository
public interface MonsterRepository extends JpaRepository<Monster, Long> {

    @Query("select m from Monster m where :biome member of m.biomes")
    List<Monster> findByBiome(String biome);

    List<Monster> findByCrGreaterThan(double cr);

    boolean existsByNameIgnoreCase(String name);
}
