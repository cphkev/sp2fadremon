package dat.lyngby.dtos;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dat.lyngby.entities.Pack;
import lombok.Data;

import java.util.List;
import java.util.Set;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PackDTO {
    private String name;
    private String description;
    private Set<CardDTO> cards;

    public PackDTO(Pack pack) {
        this.name = pack.getName();
        this.description = pack.getDescription();
        //this.cards = pack.getCards().stream().map(CardDTO::new).collect(Collectors.toSet());
    }

    public static List<PackDTO>toPackDTOList(List<Pack> packs) {
        return packs.stream().map(PackDTO::new).toList();
    }
}
