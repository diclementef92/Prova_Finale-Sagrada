package it.polimi.ingsw.model.cards.toolcards.actions;

import it.polimi.ingsw.model.Model;

import java.io.Serializable;


public abstract class Action implements Serializable {

    private Model model;

    public Action(Model model) {
        this.model = model;
    }

    public abstract String getActionString();

    public abstract boolean checkActionValidity();

    public abstract void applyAction();

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public abstract void performRequest();
}
