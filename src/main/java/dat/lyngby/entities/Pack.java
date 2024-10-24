package dat.lyngby.entities;

import dat.lyngby.dtos.PackDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * @author Rouvi
 */

@Data
@NoArgsConstructor
@Entity
@Table(name = "packs")
public class Pack {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private int id;

    private String name;

    private String description;


    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Card>cards;


    public Pack(PackDTO packDTO) {
        this.id = packDTO.getId();
        this.name = packDTO.getName();
        this.description = packDTO.getDescription();
    }


    public Pack(String name, String description, Set<Card> cards) {
        this.name = name;
        this.description = description;
        this.cards = cards;
    }


    public void addCard(Card card){
        if(card != null) {
            this.cards.add(card);
            card.setPacks(Set.of(this));
        }

    }

}
