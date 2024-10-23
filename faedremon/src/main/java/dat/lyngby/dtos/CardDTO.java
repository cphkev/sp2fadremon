package dat.lyngby.dtos;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dat.lyngby.entities.Card;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CardDTO {
    private Integer id;
    private String cardName;
    private String rarity;
    private boolean isShiny;
    private int attack;
    private int defense;
    private int price;
    private int chance;
    private String description;
    private int aura;
    private int evolutionStage;

    public CardDTO(Card card) {
        this.id = card.getId();
        this.cardName = card.getCardName();
        this.rarity = card.getRarity();
        this.isShiny = card.isShiny();
        this.attack = card.getAttack();
        this.defense = card.getDefense();
        this.price = card.getPrice();
        this.chance = card.getChance();
        this.description = card.getDescription();
        this.aura = card.getAura();
        this.evolutionStage = card.getEvolutionStage();
    }


    public static List<CardDTO> toCardDTOList(List<Card> cards) {
        return List.of(cards.stream().map(CardDTO::new).toArray(CardDTO[]::new));
    }




}
