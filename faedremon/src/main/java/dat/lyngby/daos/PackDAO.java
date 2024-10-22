package dat.lyngby.daos;

import dat.lyngby.entities.Pack;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

/**
 * Purpose:
 *
 * @author: Kevin Løvstad Schou
 */
public class PackDAO implements IDAO<Pack>{

    private final EntityManagerFactory emf;

    public PackDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    @Override
    public Pack create(Pack pack) {
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.persist(pack);
            em.getTransaction().commit();
        }
        return pack;
    }

    @Override
    public Pack getById(int id) {
        try(EntityManager em = emf.createEntityManager()){
            return em.find(Pack.class, id);
        }
    }

    @Override
    public Pack update(Pack pack, Pack updatePack) {
        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            pack.setName(updatePack.getName());
            pack.setDescription(updatePack.getDescription());
            em.merge(pack);
            em.getTransaction().commit();
        }
        return pack;
    }

    @Override
    public void delete(int id) {
        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            Pack pack = em.find(Pack.class, id);
            em.remove(pack);
            em.getTransaction().commit();
        }
    }

    @Override
    public List<Pack> getAll() {
        try(var em = emf.createEntityManager()){
            return em.createQuery("SELECT p FROM Pack p", Pack.class).getResultList();
        }
    }
}
