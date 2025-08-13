package ttrpg.encounterGen.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ttrpg.encounterGen.data.RewardRepository;
import ttrpg.encounterGen.models.Reward;

import java.util.List;
import java.util.Optional;

@Service
public class RewardService {

    @Autowired
    private RewardRepository rewardRepository;

    public Reward save(Reward reward) {
        return rewardRepository.save(reward);
    }

    public List<Reward> findAll() {
        return rewardRepository.findAll();
    }

    public Optional<Reward> findById(Long id) {
        return rewardRepository.findById(id);
    }

    public void delete(Long id) {
        rewardRepository.deleteById(id);
    }

    public List<Reward> findByRarity(String rarity) {
        return rewardRepository.findByRarity(rarity);
    }
}
