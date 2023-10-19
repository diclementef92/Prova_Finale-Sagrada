package it.polimi.ingsw.utils;

import it.polimi.ingsw.model.cards.Deck;
import it.polimi.ingsw.model.cards.privates.PrivateObjectiveCard;
import it.polimi.ingsw.model.cards.toolcards.ToolCard;
import it.polimi.ingsw.model.cards.toolcards.actions.*;
import it.polimi.ingsw.model.windowpattern.WindowPatternCard;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static it.polimi.ingsw.utils.Utils.RESOURCES_JSON_PATH;

public class Parser implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(Parser.class.getName());

    /**
     * @return
     */
    //passa i colori per generare i dadi
    public Iterator parserDicebag() {
        JSONParser parser = new JSONParser();
        Iterator iterator = null;

        JSONArray colors = null;
        try {

            Object obj = parser.parse(new FileReader(RESOURCES_JSON_PATH +
                    "dicecolors.json"));

            JSONObject jsonObject = (JSONObject) obj;
            colors = (JSONArray) jsonObject.get("colors");
            iterator = colors.iterator();

        } catch (Exception e) {
            LOGGER.log(Level.FINE, e.toString());
        }

        return iterator;

    }

    public Deck parserPrivateCard() {

        JSONParser parser = new JSONParser();
        JSONArray jsonarray = null;
        Deck deck = new Deck();

        try {
            jsonarray = (JSONArray) parser.parse(new FileReader(RESOURCES_JSON_PATH +
                    "privatecards.json"));

            for (Object a : jsonarray) {
                JSONObject jsonobject = (JSONObject) a;
                String name = (String) jsonobject.get("name");
                String descr = (String) jsonobject.get("description");
                String color = (String) jsonobject.get("color");

                PrivateObjectiveCard card = new PrivateObjectiveCard(name, descr, color);
                deck.getDeckCards().add(card);
            }
        } catch (ParseException | IOException e) {
            LOGGER.log(Level.FINE, e.toString());
        }

        return deck;

    }

    public Deck parserToolcard() {
        JSONParser parser = new JSONParser();
        JSONArray jsonarray = null;
        Deck deck = new Deck();


        try {
            jsonarray = (JSONArray) parser.parse(new FileReader(RESOURCES_JSON_PATH +
                    "toolcards.json"));

            for (Object a : jsonarray) {
                JSONObject jsonobject = (JSONObject) a;

                String name = (String) jsonobject.get("name");
                String number = (String) jsonobject.get("number");
                String color = (String) jsonobject.get("color");
                String descr = (String) jsonobject.get("description");

                JSONArray actions = (JSONArray) jsonobject.get("actions");
                ArrayList<Action> actionToPerform = new ArrayList<>();

                for (Object o : actions) {
                    JSONObject json = (JSONObject) o;

                    String action = (String) json.get("action");
                    String actiontype = (String) json.get("actiontype");

                    if (action.equals(Utils.ACTION_CHANGE_DICE_VALUE)) {
                        actionToPerform.add(new ChangeDiceValueAction(null, action, actiontype));
                    } else if (action.equals(Utils.ACTION_PLACE_DICE)) {
                        actionToPerform.add(new PlaceDiceAction(null, action, actiontype));
                    } else if (action.equals(Utils.ACTION_MOVE_DICE)) {
                        actionToPerform.add(new MoveDiceAction(null, action, actiontype));
                    } else if (action.equals(Utils.ACTION_SWAP_DICE)) {
                        actionToPerform.add(new SwapDiceAction(null, action, actiontype));
                    } else if (action.equals(Utils.ACTION_SKIP_TURN)){
                        actionToPerform.add(new SkipTurnAction(null, action,actiontype));
                    }
                }

                ToolCard card = new ToolCard(name, Integer.parseInt(number), color, descr, actionToPerform);
                deck.getDeckCards().add(card);

            }
        } catch (ParseException | IOException e) {
            LOGGER.log(Level.FINE, e.toString());
        }

        return deck;
    }


    public List<WindowPatternCard> parserWindowPatternCard() {
        JSONParser parser = new JSONParser();
        List<WindowPatternCard> deck = new ArrayList<>();

        try {
            JSONArray jsonarray = (JSONArray) parser.parse(new FileReader(RESOURCES_JSON_PATH +
                    "windowpatterns.json"));

            for (Object a : jsonarray) {
                JSONObject jsonobject = (JSONObject) a;


                String name = (String) jsonobject.get("name");
                String diff = (String) jsonobject.get("difficulty");

                JSONArray grid = (JSONArray) jsonobject.get("grid");
                String[] imported = new String[grid.size()];

                //riempio imported per creare la griglia di ogni singola wpcard
                for (int i = 0; i < grid.size(); i++) {
                    String element = (String) grid.get(i);
                    imported[i] = element;
                }

                WindowPatternCard card = new WindowPatternCard(name, Integer.parseInt(diff), imported);
                deck.add(card);
            }
        } catch (ParseException | IOException e) {
            LOGGER.log(Level.FINE, e.toString());
        }

        return deck;
    }


}






