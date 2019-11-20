package model;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class TeamDisplay {

    private SimpleStringProperty teamname;
    private SimpleIntegerProperty win;
    private SimpleIntegerProperty loss;


    public TeamDisplay(Team team) {
        teamname = new SimpleStringProperty(team.getTeamname());
        win = new SimpleIntegerProperty(team.getWin());
        loss = new SimpleIntegerProperty(team.getLoss());
    }

    public String getTeamname() {
        return teamname.get();
    }


    public void setTeamname(String teamname) {
        this.teamname.set(teamname);
    }

    public int getWin() {
        return win.get();
    }


    public void setWin(int win) {
        this.win.set(win);
    }

    public int getLoss() {
        return loss.get();
    }

    public void setLoss(int loss) {
        this.loss.set(loss);
    }
}

