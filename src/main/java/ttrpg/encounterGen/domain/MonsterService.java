package ttrpg.encounterGen.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ttrpg.encounterGen.data.MonsterRepository;
import ttrpg.encounterGen.models.Monster;

import java.util.List;
import java.util.Optional;

@Service
public class MonsterService {

    @Autowired
    private MonsterRepository monsterRepository;

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
        return monsterRepository.findByBiomesContaining(biome);
    }

    public List<Monster> findByMinCr(double cr) {
        return monsterRepository.findByCrGreaterThan(cr);
    }
}
