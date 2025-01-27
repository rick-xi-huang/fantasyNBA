package event;

import exception.InvalidMatch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.TeamDisplay;
import model.Team;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class Season extends Subject {

    private ArrayList<Team> currentTeams;
    private int teamNum;
    private String date;
    private ArrayList<String> history;
    private ArrayList<Match> matches;
    private ArrayList<Trade> trades;



    public Season(ArrayList<Team> currentTeams) {

        this.currentTeams = currentTeams;
        teamNum = currentTeams.size();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        date = simpleDateFormat.format(new Date());
        history = new ArrayList<>();
        history.add(date + "\n New Season Start!\n");
        clearRecord();
        matches = new ArrayList<>();
        trades = new ArrayList<>();
    }

    //reset all win loss record for all current teams
    //MODIFIES: this
    //EFFECTS: loop over all current teams and reset win loss record for each team
    private void clearRecord() {
        for (Team team: currentTeams) {
            team.resetRecord();
        }
    }

    //start a new match day
    //MODIFIES: this
    //EFFECTS: call allTeamsMatch, catch exception if there are less than 2 teams

    public void newMatchDay() {

        try {
            allTeamsMatch();
        } catch (InvalidMatch e) {
            System.out.println("Not enough teams");
        }
    }

    //start matches among all teams
    //MODIFIES: this
    //EFFECTS: instantiate match for every two teams, add matches to the list, add results to the list
    public void allTeamsMatch() throws InvalidMatch {

        String result = date + "\n";

        if (currentTeams.size() < 2) {
            throw new InvalidMatch();
        }

        for (int i = 0; i < teamNum; i++) {

            for (int j = i + 1; j < teamNum; j++) {

                Match match = new Match(currentTeams.get(i), currentTeams.get(j));
                match.twoTeamsMatch();
                matches.add(match);
                result = result + match.getMessage() + "\n";
            }
        }

        history.add(result);

    }

    public ArrayList<String> getHistory() {
        return history;
    }

    //Convert and get back displayable team objects for Table views
    //MODIFIES: data teamDisplay
    //EFFECTS: Return a list of TeamDisplay objects for table display

    public ObservableList<TeamDisplay> getData() {
        final ObservableList<TeamDisplay> data = FXCollections.observableArrayList();
        for (Team team: currentTeams) {
            TeamDisplay teamDisplay = new TeamDisplay(team);
            data.add(teamDisplay);
        }
        return data;
    }

}
