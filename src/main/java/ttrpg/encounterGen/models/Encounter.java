package ttrpg.encounterGen.models;

import java.util.ArrayList;
import java.util.List;

public class Encounter {

    private List<Monster> monsters;
    private List<Monster> objects;
    private List<Reward> selectedRewards;

    private int totalEXP;
    private int totalGold;
    private double minCR;
    private String difficulty;

    public Encounter() {
        this.monsters = new ArrayList<>();
        this.objects = new ArrayList<>();
        this.selectedRewards = new ArrayList<>();
        this.totalEXP = 0;
        this.totalGold = 0;
        this.minCR = 0;
        this.difficulty = "Easy";
    }

    // Getters and Setters
    public List<Monster> getMonsters() {
        return monsters;
    }

    public void setMonsters(List<Monster> monsters) {
        this.monsters = monsters;
    }

    public List<Monster> getObjects() {
        return objects;
    }

    public void setObjects(List<Monster> objects) {
        this.objects = objects;
    }

    public List<Reward> getSelectedRewards() {
        return selectedRewards;
    }

    public void setSelectedRewards(List<Reward> selectedRewards) {
        this.selectedRewards = selectedRewards;
    }

    public int getTotalEXP() {
        return totalEXP;
    }

    public void setTotalEXP(int totalEXP) {
        this.totalEXP = totalEXP;
    }

    public int getTotalGold() {
        return totalGold;
    }

    public void setTotalGold(int totalGold) {
        this.totalGold = totalGold;
    }

    public double getMinCR() {
        return minCR;
    }

    public void setMinCR(double minCR) {
        this.minCR = minCR;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    // Convenience methods for adding monsters and rewards
    public void addMonster(Monster monster) {
        this.monsters.add(monster);
        this.totalEXP += monster.getExp();
        this.totalGold += monster.getGold();
    }

    public void addObject(Monster object) {
        this.objects.add(object);
        this.totalEXP += object.getExp();
        this.totalGold += object.getGold();
    }

    public void addReward(Reward reward) {
        this.selectedRewards.add(reward);
    }
}
