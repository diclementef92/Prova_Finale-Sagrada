package it.polimi.ingsw.view;

import it.polimi.ingsw.controller.message.Message;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

public abstract class View extends Observable implements Observer, Serializable {

    public void notifyAllTheObservers(Message message) {
        this.setChanged();
        this.notifyObservers(message);
    }
}
