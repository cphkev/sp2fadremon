package dat.lyngby.entities;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cards")
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "pack_cards",
            joinColumns = @JoinColumn(name = "card_id"),
            inverseJoinColumns = @JoinColumn(name = "pack_id"))
    private Pack pack;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "inventory_cards",
            joinColumns = @JoinColumn(name = "card_id"),
            inverseJoinColumns = @JoinColumn(name = "inventory_id"))
    private Inventory inventory;
}
