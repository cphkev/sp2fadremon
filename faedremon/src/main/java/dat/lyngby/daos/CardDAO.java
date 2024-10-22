package dat.lyngby.daos;

import dat.lyngby.entities.Card;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;


/**
 * Purpose:
 *
 * @author: Kevin Løvstad Schou
 */
public class CardDAO implements IDAO<Card> {

    private final EntityManagerFactory emf;

    public CardDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }


    @Override
    public Card create(Card card) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(card);
            em.getTransaction().commit();
        }
        return card;
    }

    @Override
    public Card getById(int id) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.find(Card.class, id);
        }
    }

    @Override
    public Card update(Card card, Card updateCard) {
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            card.setCardName(updateCard.getCardName());
            card.setDescription(updateCard.getDescription());
            card.setRarity(updateCard.getRarity());
            card.setShiny(updateCard.isShiny());
            card.setPrice(updateCard.getPrice());
            card.setAttack(updateCard.getAttack());
            card.setDefense(updateCard.getDefense());
            card.setChance(updateCard.getChance());
            card.setAura(updateCard.getAura());
            card.setEvolutionStage(updateCard.getEvolutionStage());
            em.merge(card);
            em.getTransaction().commit();
        }
        return card;
    }

    @Override
    public void delete(int id) {
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Card card = em.find(Card.class, id);
            em.remove(card);
            em.getTransaction().commit();
        }

    }

    @Override
    public List<Card> getAll() {
        try (var em = emf.createEntityManager()) {
            return em.createQuery("SELECT c FROM Card c", Card.class).getResultList();
        }
    }


}
