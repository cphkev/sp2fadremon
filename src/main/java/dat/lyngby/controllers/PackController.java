package dat.lyngby.controllers;

import dat.lyngby.config.HibernateConfig;
import dat.lyngby.daos.PackDAO;
import dat.lyngby.dtos.CardDTO;
import dat.lyngby.dtos.PackDTO;
import dat.lyngby.entities.Card;
import dat.lyngby.entities.Message;
import dat.lyngby.entities.Pack;
import dat.lyngby.exceptions.ApiException;
import io.javalin.apibuilder.EndpointGroup;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import jakarta.persistence.EntityManagerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Purpose:
 *
 * @author: Kevin Løvstad Schou
 */
public class PackController {
    private final PackDAO packDAO;

    private final Logger log = LoggerFactory.getLogger(PackController.class);

    public PackController() {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
        this.packDAO = PackDAO.getInstance(emf);
    }

    public void createPack(Context ctx) {
        try {
            PackDTO packDTO = ctx.bodyAsClass(PackDTO.class);

            if (packDTO == null) {
                ctx.status(400);
                ctx.json(new Message(400, "PackDTO is required."));
                return;
            }

            PackDTO createdCard = packDAO.create(packDTO);
            ctx.res().setStatus(201);
            ctx.json(packDTO, PackDTO.class);
        }catch (Exception e){
            log.error("400 {}", e.getMessage());
            throw new ApiException(400, e.getMessage());

        }

    }

    public void updatePack(Context ctx) {
        try{
            Integer id = Integer.valueOf(ctx.pathParam("id"));
            PackDTO packDTO = ctx.bodyAsClass(PackDTO.class);


            if (packDTO == null) {
                ctx.status(400);
                ctx.json(new Message(400, "Pack data is required."));
                return;
            }

            PackDTO updatedPack = packDAO.update(id, packDTO);

            if (updatedPack == null) {
                ctx.status(404);
                ctx.json(new Message(404, "Pack not found."));
            } else {
                ctx.json(updatedPack);
                ctx.res().setStatus(200);
            }
        } catch (Exception e){
            log.error("400 {}", e.getMessage());
            throw new ApiException(400, e.getMessage());
        }

    }
    public void getAllPacks(Context ctx) {
        try {
            List<PackDTO> packDTOS = packDAO.getAll();
            ctx.res().setStatus(200);
            ctx.json(packDTOS, PackDTO.class);
        }catch (Exception e){
            log.error("400 {}", e.getMessage());
            throw new ApiException(400, e.getMessage());
        }

    }

    public void deletePack(Context ctx) {
        try {
            Integer id = Integer.valueOf(ctx.pathParam("id"));
            PackDTO pack = packDAO.getById(id);

            if (pack == null) {
                ctx.status(404);
                ctx.json(new Message(404, "Pack not found."));
                return;
            }

            for(CardDTO card : pack.getCards()){
                packDAO.delete(card.getId());
            }

            packDAO.delete(id);
            ctx.res().setStatus(204);
        }catch (Exception e){
            log.error("400 {}", e.getMessage());
            throw new ApiException(400, e.getMessage());
        }

    }




}
