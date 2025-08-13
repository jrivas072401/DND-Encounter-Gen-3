package ttrpg.encounterGen.models;

import jakarta.persistence.*;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "monsters")
public class Monster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double cr;
    private int exp;
    private int gold;

    @ElementCollection
    @CollectionTable(name = "monster_biomes", joinColumns = @JoinColumn(name = "monster_id"))
    @Column(name = "biome")
    private List<String> biomes;

    private String link;

    public Monster() {}

    public Monster(String name, double cr, int exp, List<String> biomes, String link) {
        this.name = name;
        this.cr = cr;
        this.exp = exp;
        this.gold = (int)(cr * 50);
        this.biomes = biomes;
        this.link = link;
    }

    // Convenience constructor to parse CSV-like input
    public Monster(String csvData) {
        String[] parts = csvData.split(",");
        this.name = parts[0];
        this.cr = Double.parseDouble(parts[1]);
        this.exp = Integer.parseInt(parts[2]);
        this.biomes = Arrays.asList(parts[3], parts[4], parts[5]);
        this.link = parts[6];
        this.gold = (int)(cr * 50);
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getCr() { return cr; }
    public void setCr(double cr) { this.cr = cr; }

    public int getExp() { return exp; }
    public void setExp(int exp) { this.exp = exp; }

    public int getGold() { return gold; }
    public void setGold(int gold) { this.gold = gold; }

    public List<String> getBiomes() { return biomes; }
    public void setBiomes(List<String> biomes) { this.biomes = biomes; }

    public String getLink() { return link; }
    public void setLink(String link) { this.link = link; }
}
