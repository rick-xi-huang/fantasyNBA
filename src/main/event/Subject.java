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

    public void notifyObservers(Team team1, int teamscore1, Team team2, int teamscore2) {
        for (Observer observer: observerList) {
            observer.update(team1,teamscore1,team2,teamscore2);
        }
    }

}
