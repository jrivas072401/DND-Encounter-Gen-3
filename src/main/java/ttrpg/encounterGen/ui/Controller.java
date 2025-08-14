package ttrpg.encounterGen.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ttrpg.encounterGen.models.Monster;
import ttrpg.encounterGen.models.Reward;
import ttrpg.encounterGen.models.Encounter;
import ttrpg.encounterGen.domain.MonsterService;
import ttrpg.encounterGen.domain.RewardService;
import ttrpg.encounterGen.util.AliasMethod;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;

@Component
public class Controller {

    @Autowired
    private MonsterService monsterService;

    @Autowired
    private RewardService rewardService;

    private final View view = new View();

    public void run() {
        boolean running = true;

        while (running) {
            int choice = view.mainMenu();

            switch (choice) {
                case 1 -> generateEncounter();
                case 2 -> view.displayMonsters(monsterService.findAll());
                case 3 -> manageMonsters();
                case 4 -> view.displayRewards(rewardService.findAll());
                case 5 -> running = false;
                default -> view.showMessage("Invalid option, try again.");
            }
        }

        view.showMessage("Goodbye!");
    }

    private void generateEncounter() {
        double minCr = view.promptMinCr();
        String biome = view.promptBiome();
        int count = view.promptMonsterCount();

        List<Monster> filteredMonsters = monsterService.findByBiome(biome).stream()
                .filter(m -> m.getCr() >= minCr)
                .collect(Collectors.toList());

        if (filteredMonsters.isEmpty()) {
            view.showMessage("No monsters match the criteria.");
            return;
        }

        AliasMethod aliasMethod = new AliasMethod(
                filteredMonsters.stream().map(m -> (double) m.getExp()).toList()
        );

        Encounter encounter = new Encounter();
        Set<Integer> selectedIndices = new HashSet<>();
        while (selectedIndices.size() < count) {
            selectedIndices.add(aliasMethod.next());
        }
        for (int index : selectedIndices) {
            encounter.addMonster(filteredMonsters.get(index));
        }

        view.displayEncounter(encounter);

        selectReward(encounter);
    }

    private void selectReward(Encounter encounter) {
        List<Reward> rewards = rewardService.findAll();
        if (rewards.isEmpty()) {
            view.showMessage("No rewards available.");
            return;
        }

        view.displayRewards(rewards);
        int choice = view.promptRewardChoice(rewards.size());
        if (choice != -1) {
            Reward selected = rewards.get(choice);
            encounter.addReward(selected);
            view.displaySelectedReward(selected);
        }
    }

    // monster management

    private void manageMonsters() {
        boolean managing = true;
        while (managing) {
            int choice = view.monsterMenu();
            switch (choice) {
                case 1 -> addMonster();
                case 2 -> updateMonster();
                case 3 -> deleteMonster();
                case 4 -> view.displayMonsters(monsterService.findAll());
                case 5 -> managing = false;
                default -> view.showMessage("Invalid option.");
            }
        }
    }

    private void addMonster() {
        try {
            Monster monster = view.promptMonsterDetails();
            Monster saved = monsterService.save(monster);
            view.showMessage("Monster added with ID: " + saved.getId());
        } catch (Exception e) {
            view.showMessage("Error: " + e.getMessage());
        }
    }

    private void updateMonster() {
        try {
            Long id = view.promptMonsterId();
            Optional<Monster> existingOpt = monsterService.findById(id);

            if (existingOpt.isEmpty()) {
                view.showMessage("Monster not found.");
                return;
            }

            Monster existing = existingOpt.get(); // unwrap safely
            Monster updated = view.promptMonsterDetails();
            updated.setId(existing.getId());  // preserve ID
            monsterService.save(updated);
            view.showMessage("Monster updated successfully.");
        } catch (Exception e) {
            view.showMessage("Error: " + e.getMessage());
        }
    }

    private void deleteMonster() {
        try {
            Long id = view.promptMonsterId();
            Optional<Monster> existingOpt = monsterService.findById(id);

            if (existingOpt.isEmpty()) {
                view.showMessage("Monster not found.");
                return;
            }

            monsterService.delete(id);
            view.showMessage("Monster deleted successfully.");
        } catch (Exception e) {
            view.showMessage("Error: " + e.getMessage());
        }
    }
}
