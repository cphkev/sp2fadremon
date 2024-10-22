package dat.lyngby.config;


import dat.lyngby.entities.Card;
import jakarta.persistence.EntityManagerFactory;
import org.jetbrains.annotations.NotNull;


import java.util.Set;

public class Populate {
    public static void main(String[] args) {

        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
        Set<Card> jonesCards = getJonesCards();
        Set<Card> magnusCards = getMagnusCards();

    }

    @NotNull
    private static Set<Card> getJonesCards() {
       return null;
    }


    @NotNull
    private static Set<Card> getMagnusCards() {
       return null;
    }
}