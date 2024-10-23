package dat.lyngby.routes;


import dat.lyngby.controllers.CardController;
import io.javalin.apibuilder.EndpointGroup;
import static io.javalin.apibuilder.ApiBuilder.*;
public class CardRoutes {

   // private final CardController cardController = new CardController();


    protected EndpointGroup getRoutes(){
        return () -> {
//            post("/", cardController::create);
//            get("/", cardController::readAll);
//            get("/{id}", cardController::read);
//            put("/{id}", cardController::update);
//            delete("/{id}", cardController::delete);
        };
    }


}
