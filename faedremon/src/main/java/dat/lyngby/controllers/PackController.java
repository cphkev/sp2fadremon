package dat.lyngby.controllers;

import dat.lyngby.config.HibernateConfig;
import dat.lyngby.daos.PackDAO;
import dat.lyngby.dtos.CardDTO;
import dat.lyngby.dtos.PackDTO;
import dat.lyngby.entities.Message;
import dat.lyngby.entities.Pack;
import io.javalin.apibuilder.EndpointGroup;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

/**
 * Purpose:
 *
 * @author: Kevin Løvstad Schou
 */
public class PackController {
    private final PackDAO packDAO;

    public PackController(PackDAO packDAO) {
        this.packDAO = packDAO;
    }

    public void createPack(Context ctx) {
        PackDTO packDTO = ctx.bodyAsClass(PackDTO.class);

        if (packDTO == null) {
            ctx.status(HttpStatus.BAD_REQUEST).result("packDTO is required.");
            return;
        }
        PackDTO createdCard = packDAO.create(packDTO);

        ctx.status(HttpStatus.CREATED).json(createdCard);
    }

    public void updatePack(Context ctx) {
        Integer id = Integer.valueOf(ctx.pathParam("id"));
        PackDTO packDTO = ctx.bodyAsClass(PackDTO.class);

        if (packDTO == null) {
            ctx.status(HttpStatus.BAD_REQUEST).result("Pack data is required.");
            return;
        }
        PackDTO updatedPack = packDAO.update(id, packDTO);

        if (updatedPack == null) {
            ctx.status(HttpStatus.NOT_FOUND).result("Pack not found.");
        } else {
            ctx.json(updatedPack);
        }
    }
    public void getAllPacks(Context ctx) {
        List<PackDTO> packs = packDAO.getAll();
        ctx.json(packs);
    }
}
