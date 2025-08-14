package ttrpg.encounterGen.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import ttrpg.encounterGen.data.MonsterRepository;
import ttrpg.encounterGen.models.Monster;

import java.util.List;
import java.util.Optional;

@Service
public class MonsterService {


    private final MonsterRepository monsterRepository;

    @Autowired
    public MonsterService(MonsterRepository monsterRepository) {
        this.monsterRepository = monsterRepository;
    }

    @Transactional
    public Monster addMonster(Monster monster) {
        validateMonster(monster, false);
        return monsterRepository.save(monster);
    }

    @Transactional
    public Monster updateMonster(Long id, Monster updatedMonster) {
        if (!monsterRepository.existsById(id)) {
            throw new IllegalArgumentException("Monster with ID " + id + " does not exist.");
        }
        updatedMonster.setId(id);
        validateMonster(updatedMonster, true);
        return monsterRepository.save(updatedMonster);
    }

    public Monster save(Monster monster) {
        return monsterRepository.save(monster);
    }

    public List<Monster> findAll() {
        return monsterRepository.findAll();
    }

    public Optional<Monster> findById(Long id) {
        return monsterRepository.findById(id);
    }

    public void delete(Long id) {
        monsterRepository.deleteById(id);
    }

    public List<Monster> findByBiome(String biome) {
        return monsterRepository.findByBiome(biome);
    }

    public List<Monster> findByMinCr(double cr) {
        return monsterRepository.findByCrGreaterThan(cr);
    }

    private void validateMonster(Monster monster, boolean isUpdate) {
        if (monster.getName() == null || monster.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Monster name must not be null or empty.");
        }

        if (monster.getCr() < 0) {
            throw new IllegalArgumentException("CR must be zero or positive.");
        }

        if (monster.getExp() < 0) {
            throw new IllegalArgumentException("EXP must be zero or positive.");
        }

        if (monster.getBiomes() == null || monster.getBiomes().isEmpty()) {
            throw new IllegalArgumentException("Monster must have at least 1 biome.");
        }

        if (monster.getBiomes().size() > 3) {
            throw new IllegalArgumentException("Monster must not have more than 3 biomes.");
        }

        // When adding (not updating), check uniqueness
        if (!isUpdate && monsterRepository.existsByNameIgnoreCase(monster.getName())) {
            throw new IllegalArgumentException("Monster with name '" + monster.getName() + "' already exists.");
        }
    }
}
