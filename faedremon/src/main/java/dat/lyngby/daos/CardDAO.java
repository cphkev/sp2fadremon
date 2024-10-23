package dat.lyngby.daos;

import dat.lyngby.dtos.CardDTO;
import dat.lyngby.dtos.PackDTO;
import dat.lyngby.entities.Card;
import dat.lyngby.entities.Pack;
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
public class CardDAO implements IDAO<CardDTO, Integer> {
    private static CardDAO instance;

    private static EntityManagerFactory emf;

    public static CardDAO getInstance(EntityManagerFactory factory) {
        if (instance == null) {
            emf = factory;
            instance = new CardDAO();

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


    public PackDTO addCardToPack(int packId, CardDTO cardDTO) {
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Card card = new Card(cardDTO);
            Pack pack = em.find(Pack.class, packId);
            pack.addCard(card);
            em.persist(card);
            Pack mergedPack = em.merge(pack);
            em.getTransaction().commit();
            return new PackDTO(mergedPack);
        }
    }
    





    public Card getByRarity(String rarity) {
        try (var em = emf.createEntityManager()) {
            return em.createQuery("SELECT c FROM Card c WHERE c.rarity = :rarity", Card.class).setParameter("rarity", rarity).getSingleResult();
        }
    }

//    public Card getByMinPrice(int price) {
//        try (var em = emf.createEntityManager()) {
//            return em.createQuery("SELECT c FROM Card c WHERE c.price >= :price", Card.class).setParameter("price", price).getSingleResult();
//        }
public List<Card> getByMinPrice(int minPrice) {
    try (var em = emf.createEntityManager()) {
        String jpql = "SELECT c FROM Card c WHERE c.price >= :minPrice";
        return em.createQuery(jpql, Card.class)
                .setParameter("minPrice", minPrice)
                .getResultList();
    }
    }

//    public Card getByMaxPrice(int price) {
//        try (var em = emf.createEntityManager()) {
//            return em.createQuery("SELECT c FROM Card c WHERE c.price <= :price", Card.class).setParameter("price", price).getSingleResult();
//        }
public List<Card> getByMaxPrice(int maxPrice) {
    try (var em = emf.createEntityManager()) {
        String jpql = "SELECT c FROM Card c WHERE c.price <= :maxPrice";
        return em.createQuery(jpql, Card.class)
                .setParameter("maxPrice", maxPrice)
                .getResultList();
    }
    }

//    public Card getByMinAndMaxPrice(int minPrice, int maxPrice) {
//        try (var em = emf.createEntityManager()) {
//            return em.createQuery("SELECT c FROM Card c WHERE c.price >= :minPrice AND c.price <= :maxPrice", Card.class).setParameter("minPrice", minPrice).setParameter("maxPrice", maxPrice).getSingleResult();
//        }
//    }

    public List<Card> getByMinAndMaxPrice(int minPrice, int maxPrice) {
        try (var em = emf.createEntityManager()) {
            String jpql = "SELECT c FROM Card c WHERE c.price >= :minPrice AND c.price <= :maxPrice";
            return em.createQuery(jpql, Card.class)
                    .setParameter("minPrice", minPrice)
                    .setParameter("maxPrice", maxPrice)
                    .getResultList();
        }
    }
}
