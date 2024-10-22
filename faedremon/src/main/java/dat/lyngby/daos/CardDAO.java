package dat.lyngby.daos;

import dat.lyngby.dtos.CardDTO;
import dat.lyngby.entities.Card;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import lombok.NoArgsConstructor;

import java.util.List;


/**
 * Purpose:
 *
 * @author: Kevin Løvstad Schou
 */
@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class CardDAO implements IDAO<CardDTO,Integer> {
    private static CardDAO instance;

    private static EntityManagerFactory emf;

  public static CardDAO getInstance(EntityManagerFactory factory) {
        if (instance == null) {
            instance = new CardDAO();
            emf = factory;
        }
        return instance;
    }


    @Override
    public CardDTO create(CardDTO cardDTO) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Card card = new Card(cardDTO);
            em.persist(card);
            em.getTransaction().commit();
            return new CardDTO(card);
        }

    }

    @Override
    public CardDTO getById(int id) {
        try (EntityManager em = emf.createEntityManager()) {
            Card card = em.find(Card.class, id);
            return new CardDTO(card);
        }
    }

    @Override
    public CardDTO update(Integer integer, CardDTO cardDTO) {
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Card card = em.find(Card.class, integer);
            card.setCardName(cardDTO.getCardName());
            card.setDescription(cardDTO.getDescription());
            card.setRarity(cardDTO.getRarity());
            card.setPrice(cardDTO.getPrice());
            card.setShiny(cardDTO.isShiny());
            card.setAttack(cardDTO.getAttack());
            card.setDefense(cardDTO.getDefense());
            card.setChance(cardDTO.getChance());
            card.setAura(cardDTO.getAura());
            card.setEvolutionStage(cardDTO.getEvolutionStage());
           Card mergedCard = em.merge(card);
            em.getTransaction().commit();
            return mergedCard != null ? new CardDTO(mergedCard) : null;
        }

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
    public List<CardDTO> getAll() {
        try (var em = emf.createEntityManager()) {
           TypedQuery<CardDTO> query = em.createQuery("SELECT new dat.lyngby.dtos.CardDTO(c) FROM Card c", CardDTO.class);
            return query.getResultList();
        }
    }

    public Card getByRarity(String rarity) {
        try (var em = emf.createEntityManager()) {
            return em.createQuery("SELECT c FROM Card c WHERE c.rarity = :rarity", Card.class).setParameter("rarity", rarity).getSingleResult();
        }
    }

    public Card getByMinPrice(int price) {
        try (var em = emf.createEntityManager()) {
            return em.createQuery("SELECT c FROM Card c WHERE c.price >= :price", Card.class).setParameter("price", price).getSingleResult();
        }
    }

    public Card getByMaxPrice(int price) {
        try (var em = emf.createEntityManager()) {
            return em.createQuery("SELECT c FROM Card c WHERE c.price <= :price", Card.class).setParameter("price", price).getSingleResult();
        }
    }

    public Card getByMinAndMaxPrice(int minPrice, int maxPrice) {
        try (var em = emf.createEntityManager()) {
            return em.createQuery("SELECT c FROM Card c WHERE c.price >= :minPrice AND c.price <= :maxPrice", Card.class).setParameter("minPrice", minPrice).setParameter("maxPrice", maxPrice).getSingleResult();
        }
    }
}
