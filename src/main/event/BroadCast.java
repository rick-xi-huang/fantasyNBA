package event;

import model.Team;

public class BroadCast implements Observer {

    //MODIFIES: match
    //EFFECTS: print match results and update the win loss record

    @Override
    public void update(Match match) {
        System.out.println(match.getMessage());
        match.getWinner().addwin();
        match.getLoser().addloss();
    }
}
