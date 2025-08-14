package ttrpg.encounterGen.models;

import jakarta.persistence.*;
import java.util.ArrayList;
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
    @CollectionTable(
            name = "monster_biomes",
            joinColumns = @JoinColumn(name = "monster_id")
    )
    @Column(name = "biome", nullable = false)
    private List<String> biomes = new ArrayList<>();

    private String link;

    public Monster() {
    }

    public Monster(String name, double cr, int exp, List<String> biomes, String link) {
        this.name = name;
        this.cr = cr;
        this.exp = exp;
        this.gold = (int) (cr * 50);
        this.biomes = biomes != null ? biomes : new ArrayList<>();
        this.link = link;
    }

    /**
     * Convenience constructor to parse CSV-like input:
     * name,cr,exp,biome1,biome2,biome3,link
     */
    public Monster(String csvData) {
        String[] parts = csvData.split(",");
        this.name = parts[0].trim();
        this.cr = Double.parseDouble(parts[1].trim());
        this.exp = Integer.parseInt(parts[2].trim());

        this.biomes = new ArrayList<>();
        // Safely add up to three biome columns if present & non-empty
        for (int i = 3; i <= 5 && i < parts.length; i++) {
            String b = parts[i].trim();
            if (!b.isEmpty()) {
                this.biomes.add(b);
            }
        }

        if (parts.length > 6) {
            this.link = parts[6].trim();
        } else {
            this.link = null;
        }

        this.gold = (int) (cr * 50);
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getCr() { return cr; }
    public void setCr(double cr) {
        this.cr = cr;
        // keep gold consistent if cr changes
        this.gold = (int) (cr * 50);
    }

    public int getExp() { return exp; }
    public void setExp(int exp) { this.exp = exp; }

    public int getGold() { return gold; }
    public void setGold(int gold) { this.gold = gold; }

    public List<String> getBiomes() { return biomes; }
    public void setBiomes(List<String> biomes) {
        this.biomes = biomes != null ? biomes : new ArrayList<>();
    }

    public String getLink() { return link; }
    public void setLink(String link) { this.link = link; }
}
