package event;

import model.Team;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public abstract class Event {

    protected ArrayList<Team> currentTeams;
    protected int teamNum;
    protected String date;
    public String event;


    public Event(ArrayList<Team> currentTeams) {

        this.currentTeams = currentTeams;
        teamNum = currentTeams.size();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        date = simpleDateFormat.format(new Date());
        event = date + "\n";

    }


    public void eventMessage() {
        System.out.println(date + "\n" + event);
    }

    public String getEvent() {
        return event;
    }

    public String getDate() {
        return date;
    }

}
