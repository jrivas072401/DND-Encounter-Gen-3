package ttrpg.encounterGen.models;

import jakarta.persistence.*;

@Entity
@Table(name = "rewards")
public class Reward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String rarity;

    public Reward() {}

    public Reward(String name, String description, String rarity) {
        this.name = name;
        this.description = description;
        this.rarity = rarity;
    }

    // Convenience constructor to parse CSV-like input
    public Reward(String csvData) {
        String[] parts = csvData.split(",");
        this.name = parts[0];
        this.description = parts[1];
        this.rarity = parts[2];
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getRarity() { return rarity; }
    public void setRarity(String rarity) { this.rarity = rarity; }
}
