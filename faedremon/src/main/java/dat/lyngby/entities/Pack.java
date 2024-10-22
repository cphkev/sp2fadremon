package dat.lyngby.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

/**
 * @author Rouvi
 */

@Data
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

}
