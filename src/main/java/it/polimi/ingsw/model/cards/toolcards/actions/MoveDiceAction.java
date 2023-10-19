package it.polimi.ingsw.model.cards.toolcards.actions;

import it.polimi.ingsw.controller.message.responses.MoveDiceResponse;
import it.polimi.ingsw.controller.message.responses.PrivateResponse;
import it.polimi.ingsw.controller.message.responses.Response;
import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.cards.toolcards.ToolCard;
import it.polimi.ingsw.model.dice.Dice;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.PlayerBoard;
import it.polimi.ingsw.utils.Position;
import it.polimi.ingsw.utils.Utils;

import java.util.List;

/**
 * Move dice action class
 */
public class MoveDiceAction extends Action {


    private int finalRow;
    private int finalCol;
    private int startRow;
    private int startCol;
    private String action;
    private String actionType;
    private Dice oldDice;

    /**
     * Main constructor of the Move Dice Action
     *
     * @param model
     * @param action
     * @param actionType
     */
    public MoveDiceAction(Model model, String action, String actionType) {
        super(model);
        this.action = action;
        this.actionType = actionType;
    }

    /**
     * Method used to perform a request to the user
     */
    @Override
    public void performRequest() {
        sendPlayerboard();
        getModel().notifyViews(new MoveDiceResponse(Utils.ACTION_MOVE_DICE, this.actionType, getModel().getTournament().getCurrentTurn().getDiceToBePlaced()));

    }

    @Override
    public String getActionString() {
        return action;
    }

    /**
     * Method used to check the validity of the action performed
     * <p>
     * As first thing it is checked if the move action made by the user is not trying to put 2 dices
     * in the same position using the same toolcard
     *
     * @return a boolean to know if the action is valid
     */
    @Override
    public boolean checkActionValidity() {
        Player currentPlayer = getModel().getTournament().getRoundPlayers().get(getModel().getTournament().getCurrentRoundPlayerNumber());
        PlayerBoard playerBoard = currentPlayer.getPlayerBoard();
        ToolCard currentToolcard = getModel().getTournament().getCurrentTurn().getCurrentToolCard();

        if(finalCol== startCol && finalRow == startRow){
            getModel().notifyViews(new Response("Sovrapposto"));
            return false;
        }


        //Check for final position
        if(currentToolcard.getMoveDiceFinalPositions().isEmpty()){
            currentToolcard.getMoveDiceFinalPositions().add(new Position(finalRow, finalCol));
        }else {
            for (Position finalPostion : currentToolcard.getMoveDiceFinalPositions()) {
                if ((finalPostion.getCol() == finalCol && finalPostion.getRow() == finalRow) || (finalRow == startRow && finalCol == startCol)) {
                    return false;
                } else {
                    currentToolcard.getMoveDiceFinalPositions().add(new Position(finalRow, finalCol));
                }
            }
        }

        //Check for initial position
        if(currentToolcard.getMoveDiceFinalPositions().isEmpty()){
            currentToolcard.getMoveDiceStartingPositions().add(new Position(startRow, startCol));
        }else {
            for (Position startingPosition : currentToolcard.getMoveDiceStartingPositions()) {
                if ((startingPosition.getRow() == startRow && startingPosition.getCol() == startCol) || (finalCol == startCol && startRow == finalRow)) {
                    return false;
                } else {
                    currentToolcard.getMoveDiceStartingPositions().add(new Position(startRow, startCol));
                }
            }
        }

        if (actionType.equals(Utils.ACTIONTYPE_MOVE_IGNORE_COLOR)) {
            if (playerBoard.checkIfCellIsEmpty(finalRow, finalCol)) {
                if (playerBoard.checkValueValidity(playerBoard.getWindowboard().getCell(startRow, startCol).getDice(), finalRow, finalCol)
                        || playerBoard.checkIfWhiteCell(playerBoard.getWindowboard().getCell(startRow, startCol).getDice(), finalRow, finalCol)) {
                    if (playerBoard.checkIfDicesAround(finalRow, finalCol, startRow, startCol)) {
                        if(playerBoard.checkIfOrthogonalIgnoreSelf(playerBoard.getWindowboard().getCell(startRow, startCol).getDice(), finalRow, finalCol, startRow, startCol)){
                            makePlacement();
                            return true;
                        }
                    }
                }
            }
            return false;
        }

        if (actionType.equals(Utils.ACTIONTYPE_MOVE_IGNORE_VALUE)) {
            if (playerBoard.checkIfCellIsEmpty(finalRow, finalCol)) {
                if (playerBoard.checkColorValidity(playerBoard.getWindowboard().getCell(startRow, startCol).getDice(), finalRow, finalCol)
                        || playerBoard.checkIfWhiteCell(playerBoard.getWindowboard().getCell(startRow, startCol).getDice(), finalRow, finalCol)) {
                    if (playerBoard.checkIfDicesAround(finalRow, finalCol, startRow, startCol)) {
                        if(playerBoard.checkIfOrthogonalIgnoreSelf(playerBoard.getWindowboard().getCell(startRow, startCol).getDice(), finalRow, finalCol, startRow, startCol)){
                            makePlacement();
                            return true;
                        }
                    }
                }
            }
            return false;
        }

        if (actionType.equals(Utils.ACTIONTYPE_MOVE_WITH_ALL_RESTRICTIONS)) {
            if (playerBoard.checkValidityPlacement(playerBoard.getWindowboard().getCell(startRow, startCol).getDice(), finalRow, finalCol, startRow, startCol)){
                makePlacement();
                return true;
            } else {
                return false;
            }
        }

        if (actionType.equals(Utils.ACTIONTYPE_MOVE_MATCHING_ROUNDTRACKDICE)) {

            //currentToolcard.setChoseColorFromRoundrack(playerBoard.getWindowboard().getCell(startRow, startCol).getDice().getColor());
            System.out.println(getModel().getTournament().getCurrentTurn().getCurrentToolCard().getChoseColorFromRoundrack() + " valore in 1 ");
            int round = getModel().getGameBoard().getRoundTrack().getNumberofRound();
            if (getModel().getTournament().getCurrentTurn().getCurrentToolCard().getChoseColorFromRoundrack().equals("default")) {
                getModel().getTournament().getCurrentTurn().getCurrentToolCard().setChoseColorFromRoundrack(
                        playerBoard.getWindowboard().getCell(startRow, startCol).getDice().getColor()
                );

                System.out.println(getModel().getTournament().getCurrentTurn().getCurrentToolCard().getChoseColorFromRoundrack() + " valore in 2 ");
            } else {

                System.out.println(getModel().getTournament().getCurrentTurn().getCurrentToolCard().getChoseColorFromRoundrack() + " valore in 3 ");
                if (!getModel().getTournament().getCurrentTurn().getCurrentToolCard().getChoseColorFromRoundrack().equals(
                        playerBoard.getWindowboard().getCell(startRow, startCol).getDice().getColor())) {
                    System.out.println("not same color or value");
                    return false;
                }
            }

            if (playerBoard.checkIfCellIsEmpty(finalRow, finalCol)) {
                for (int i = 1; i < round; i++) {

                    for (Dice dice : getModel().getGameBoard().getRoundTrack().getDicePerRound(i)) {
                        if (dice.getColor().equals(playerBoard.getWindowboard().getCell(startRow, startCol).getDice().getColor())) {
                            if(playerBoard.checkValidityPlacement(playerBoard.getWindowboard().getCell(startRow, startCol).getDice(), finalRow, finalCol, startRow, startCol)){
                                makePlacement();
                                return true;
                            }
                        }
                    }
                }
            }
            return false;
        }

        return true;

    }

    /**
     * Method to apply all the modifications to the model
     */
    @Override
    public void applyAction() {
        Player currentPlayer = getModel().getTournament().getRoundPlayers().get(getModel().getTournament().getCurrentRoundPlayerNumber());
        getModel().notifyViews(new PrivateResponse("Dice moved correctly!\n", currentPlayer));

    }


    public void makePlacement(){
        Player currentPlayer = getModel().getTournament().getRoundPlayers().get(getModel().getTournament().getCurrentRoundPlayerNumber());
        PlayerBoard playerBoard = currentPlayer.getPlayerBoard();

        Dice toMove = playerBoard.getWindowboard().getCell(startRow, startCol).getDice();
        oldDice = new Dice(toMove.getColor());
        toMove.setValue(toMove.getValue());

        playerBoard.getWindowboard().getCell(startRow, startCol).setDice(null);
        playerBoard.getWindowboard().getCell(finalRow, finalCol).setDice(toMove);

        //sendPlayerboard();
    }

    public void rollback(){
        Player currentPlayer = getModel().getTournament().getRoundPlayers().get(getModel().getTournament().getCurrentRoundPlayerNumber());
        PlayerBoard playerBoard = currentPlayer.getPlayerBoard();

        playerBoard.getWindowboard().getCell(finalRow, finalCol).setDice(null);
        playerBoard.getWindowboard().getCell(startRow, startCol).setDice(oldDice);
    }

    public void sendPlayerboard(){
        StringBuilder sr = new StringBuilder();
        Player currentPlayer = getModel().getTournament().getRoundPlayers().get(getModel().getTournament().getCurrentRoundPlayerNumber());
        sr.append(Utils.YOUR_PLAYERBOARD + "\n");
        sr.append(currentPlayer.getPlayerBoard().fullColoredString());
        getModel().notifyViews(new PrivateResponse(sr.toString(), currentPlayer));

    }

    /**
     * Setter for the starting row
     *
     * @param startRow the starting row integer
     */
    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    /**
     * Setter for the final row
     *
     * @param finalRow the final row integer
     */
    public void setFinalRow(int finalRow) {
        this.finalRow = finalRow;
    }


    /**
     * Setter for the starting col
     *
     * @param startCol the starting col integer
     */
    public void setStartCol(int startCol) {
        this.startCol = startCol;
    }


    /**
     * Setter for the final col
     *
     * @param finalCol the final col integer
     */
    public void setFinalCol(int finalCol) {
        this.finalCol = finalCol;
    }


}
