package event;

import model.Team;

public class BroadCast implements Observer {


    @Override
    public void update(Match match) {
        System.out.println(match.getMessage());
        match.getWinner().addwin();
        match.getLoser().addloss();
    }
}
