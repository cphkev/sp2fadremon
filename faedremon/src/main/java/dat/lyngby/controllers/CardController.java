package dat.lyngby.controllers;

import dat.lyngby.daos.CardDAO;
import dat.lyngby.dtos.CardDTO;
import dat.lyngby.entities.Card;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;

import java.util.List;

public class CardController {
    private final CardDAO cardDAO;

    public CardController(CardDAO cardDAO) {
        this.cardDAO = cardDAO;
    }

    public void createCard(Context ctx) {
        CardDTO cardDTO = ctx.bodyAsClass(CardDTO.class);

        if (cardDTO == null) {
            ctx.status(HttpStatus.BAD_REQUEST).result("CardDTO is required.");
            return;
        }
        CardDTO createdCard = cardDAO.create(cardDTO);

        ctx.status(HttpStatus.CREATED).json(createdCard);
    }

    public void updateCard(Context ctx) {
        Integer id = Integer.valueOf(ctx.pathParam("id"));
        CardDTO cardDTO = ctx.bodyAsClass(CardDTO.class);

        if (cardDTO == null) {
            ctx.status(HttpStatus.BAD_REQUEST).result("Card data is required.");
            return;
        }
        CardDTO updatedCard = cardDAO.update(id, cardDTO);

        if (updatedCard == null) {
            ctx.status(HttpStatus.NOT_FOUND).result("Card not found.");
        } else {
            ctx.json(updatedCard);
        }
    }

    public void getAllCards(Context ctx) {
        List<CardDTO> cards = cardDAO.getAll();
        ctx.json(cards);
    }



    public void getByMinAndMaxPrice(Context ctx) {
        String minPriceParam = ctx.queryParam("minPrice");
        String maxPriceParam = ctx.queryParam("maxPrice");

        if (minPriceParam == null || maxPriceParam == null) {
            ctx.status(HttpStatus.BAD_REQUEST).result("minPrice and maxPrice are required.");
            return;
        }
        try {
            int minPrice = Integer.parseInt(minPriceParam);
            int maxPrice = Integer.parseInt(maxPriceParam);
            List<Card> cards = cardDAO.getByMinAndMaxPrice(minPrice, maxPrice);

            if (cards.isEmpty()) {
                ctx.status(HttpStatus.NOT_FOUND).result("No cards found.");
            } else {
                ctx.json(CardDTO.toCardDTOList(cards));
            }
        } catch (NumberFormatException e) {
            ctx.status(HttpStatus.BAD_REQUEST).result("minPrice and maxPrice must be integers.");
        }
    }

    public void getByMaxPrice(Context ctx) {
        String maxPriceParam = ctx.queryParam("maxPrice");

        if (maxPriceParam == null) {
            ctx.status(HttpStatus.BAD_REQUEST).result("maxPrice is required.");
            return;
        }
        try {
            int maxPrice = Integer.parseInt(maxPriceParam);
            List<Card> cards = cardDAO.getByMaxPrice(maxPrice);

            if (cards.isEmpty()) {
                ctx.status(HttpStatus.NOT_FOUND).result("No cards found.");
            } else {
                ctx.json(CardDTO.toCardDTOList(cards));
            }
        } catch (NumberFormatException e) {
            ctx.status(HttpStatus.BAD_REQUEST).result("maxPrice must be an integer.");
        }
    }

    public void getByMinPrice(Context ctx) {
        String minPriceParam = ctx.queryParam("minPrice");

        if (minPriceParam == null) {
            ctx.status(HttpStatus.BAD_REQUEST).result("minPrice is required.");
            return;
        }
        try {
            int minPrice = Integer.parseInt(minPriceParam);
            List<Card> cards = cardDAO.getByMinPrice(minPrice);

            if (cards.isEmpty()) {
                ctx.status(HttpStatus.NOT_FOUND).result("No cards found.");
            } else {
                ctx.json(CardDTO.toCardDTOList(cards));
            }
        } catch (NumberFormatException e) {
            ctx.status(HttpStatus.BAD_REQUEST).result("minPrice must be an integer.");
        }
    }
}
