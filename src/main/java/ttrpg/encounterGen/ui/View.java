package ttrpg.encounterGen.ui;

import ttrpg.encounterGen.models.Monster;
import ttrpg.encounterGen.models.Reward;
import ttrpg.encounterGen.models.Encounter;

import java.util.List;
import java.util.Scanner;

public class View {

    private final Scanner scanner = new Scanner(System.in);

    public int mainMenu() {
        System.out.println("\nSelect an option:");
        System.out.println("1. Generate Encounter");
        System.out.println("2. List Monsters");
        System.out.println("3. List Rewards");
        System.out.println("4. Exit");
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
}
