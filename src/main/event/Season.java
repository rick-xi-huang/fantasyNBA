package event;

import exception.InvalidMatch;
import model.Team;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        matches = new ArrayList<>();
        trades = new ArrayList<>();
    }

    public void newMatchDay() {

        try {
            allTeamsMatch();
        } catch (InvalidMatch e) {
            System.out.println("Not enough teams");
        }
    }


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

}
