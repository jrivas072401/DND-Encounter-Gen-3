package ttrpg.encounterGen.ui;

import ttrpg.encounterGen.models.Monster;
import ttrpg.encounterGen.models.Reward;
import ttrpg.encounterGen.models.Encounter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class View {

    private final Scanner scanner = new Scanner(System.in);

    public int mainMenu() {
        System.out.println("\nSelect an option:");
        System.out.println("1. Generate Encounter");
        System.out.println("2. List Monsters");
        System.out.println("3. Manage Monsters");  // NEW
        System.out.println("4. List Rewards");
        System.out.println("5. Exit");              // shifted for new option
        return Integer.parseInt(scanner.nextLine());
    }

    public double promptMinCr() {
        System.out.println("Enter minimum CR for encounter:");
        return Double.parseDouble(scanner.nextLine());
    }

    public String promptBiome() {
        System.out.println("Enter biome (e.g., Forest, Desert, Swamp):");
        return scanner.nextLine();
    }

    public int promptMonsterCount() {
        System.out.println("Enter number of monsters to generate:");
        return Integer.parseInt(scanner.nextLine());
    }

    public void displayEncounter(Encounter encounter) {
        System.out.println("\nGenerated Encounter:");
        for (Monster monster : encounter.getMonsters()) {
            System.out.printf("- %s (CR: %.2f, EXP: %d)\n",
                    monster.getName(), monster.getCr(), monster.getExp());
        }
    }

    public void displayMonsters(List<Monster> monsters) {
        if (monsters.isEmpty()) {
            System.out.println("No monsters in database.");
            return;
        }
        for (Monster m : monsters) {
            System.out.printf("- %s (CR: %.2f, EXP: %d)\n", m.getName(), m.getCr(), m.getExp());
        }
    }

    public void displayRewards(List<Reward> rewards) {
        if (rewards.isEmpty()) {
            System.out.println("No rewards in database.");
            return;
        }
        for (int i = 0; i < rewards.size(); i++) {
            Reward r = rewards.get(i);
            System.out.printf("%d. %s (%s) - %s\n", i + 1, r.getName(), r.getRarity(), r.getDescription());
        }
    }

    public int promptRewardChoice(int max) {
        System.out.println("\nSelect a reward by number:");
        int choice = Integer.parseInt(scanner.nextLine()) - 1;
        if (choice < 0 || choice >= max) {
            System.out.println("Invalid selection.");
            return -1;
        }
        return choice;
    }

    public void displaySelectedReward(Reward reward) {
        System.out.println("You selected: " + reward.getName());
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public int monsterMenu() {
        System.out.println("\nMonster Management:");
        System.out.println("1. Add Monster");
        System.out.println("2. Update Monster");
        System.out.println("3. Delete Monster");
        System.out.println("4. List Monsters");
        System.out.println("5. Back to Main Menu");
        return Integer.parseInt(scanner.nextLine());
    }

    public Monster promptMonsterDetails() {
        System.out.println("Enter monster name:");
        String name = scanner.nextLine();

        System.out.println("Enter CR (>=0):");
        double cr = Double.parseDouble(scanner.nextLine());

        System.out.println("Enter EXP (>=0):");
        int exp = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter number of biomes (1-3):");
        int count = Integer.parseInt(scanner.nextLine());
        List<String> biomes = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            System.out.printf("Biome %d: ", i+1);
            biomes.add(scanner.nextLine());
        }

        System.out.println("Enter link:");
        String link = scanner.nextLine();

        return new Monster(name, cr, exp, biomes, link);
    }

    public Long promptMonsterId() {
        System.out.println("Enter monster ID:");
        return Long.parseLong(scanner.nextLine());
    }
}
