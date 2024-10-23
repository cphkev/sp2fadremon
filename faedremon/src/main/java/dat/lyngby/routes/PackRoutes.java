package dat.lyngby.routes;

import dat.lyngby.config.HibernateConfig;
import dat.lyngby.controllers.PackController;
import dat.lyngby.daos.PackDAO;
import jakarta.persistence.EntityManagerFactory;
import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.*;

/**
 * Purpose:
 *
 * @author: Kevin Løvstad Schou
 */
public class PackRoutes {

    private final EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
    private final PackDAO packDAO = new PackDAO(emf);

    private final PackController packController = new PackController(packDAO);

    public EndpointGroup getRoutes(){
        return () -> {
//            post("/", packController::create);
//            get("/", packController::readAll);
//            get("/{id}", packController::read);
//            put("/{id}", packController::update);
//            delete("/{id}", packController::delete);
        };
    }


}
