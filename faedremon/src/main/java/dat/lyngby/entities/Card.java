package dat.lyngby.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private int id;

    private String cardName;
    private String description;
    private String rarity;
    private int price;
    private boolean isShiny;
    private int attack;
    private int defense;
    private int chance;
    private int aura;
    private int evolutionStage;

}
