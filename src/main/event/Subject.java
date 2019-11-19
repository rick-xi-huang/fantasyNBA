package event;

import model.Team;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {

    List<Observer> observerList;

    public Subject() {
        observerList = new ArrayList<>();
    }

    public void addObserver(Observer observer) {
        observerList.add(observer);
    }

    public void notifyObservers(Match match) {
        for (Observer observer: observerList) {
            observer.update(match);
        }
    }

}
