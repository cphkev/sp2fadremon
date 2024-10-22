package dat.lyngby.dtos;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CardDTO {

    private String cardName;
    private String rarity;
    private boolean isShiny;
    private int attack;
    private int defense;
    private int price;
    private int chance;
    private String description;
    private int aura;


}
