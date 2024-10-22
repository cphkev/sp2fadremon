package dat.lyngby.daos;

import dat.lyngby.dtos.CardDTO;
import dat.lyngby.dtos.PackDTO;
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
public class PackDAO implements IDAO<PackDTO,Integer> {
    private static PackDAO instance;

    private static EntityManagerFactory emf;

    public static PackDAO getInstance(EntityManagerFactory factory) {
        if (instance == null) {
            instance = new PackDAO();
            emf = factory;
        }
        return instance;
    }

    @Override
    public PackDTO create(PackDTO packDTO) {
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            Pack pack = new Pack(packDTO);
            em.persist(pack);
            em.getTransaction().commit();
            return new PackDTO(pack);
        }
    }

    @Override
    public PackDTO getById(int id) {
        try(var em = emf.createEntityManager()){
            Pack pack = em.find(Pack.class, id);
            return new PackDTO(pack);
        }
    }

    @Override
    public PackDTO update(Integer integer, PackDTO packDTO) {
        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            Pack pack = em.find(Pack.class, integer);
            pack.setName(packDTO.getName());
            pack.setDescription(packDTO.getDescription());
            Pack mergedPack = em.merge(pack);
            em.getTransaction().commit();
            return mergedPack != null ? new PackDTO(mergedPack) : null;
        }

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
    public List<PackDTO> getAll() {
        try(var em = emf.createEntityManager()){
            TypedQuery<PackDTO> query = em.createQuery("SELECT new dat.lyngby.dtos.PackDTO(p) FROM Pack p", PackDTO.class);
            return query.getResultList();
        }
    }
}
