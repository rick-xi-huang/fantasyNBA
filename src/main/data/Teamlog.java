package data;

import model.Player;
import model.Team;
import network.FantasyWebData;

import java.io.*;
import java.util.ArrayList;

public class Teamlog implements Serializable, Loadable, Saveable {

    public ArrayList<Team> currentTeams;
    FantasyWebData fantasyWebData;

    public Teamlog() {
        currentTeams = new ArrayList<>();
        fantasyWebData = new FantasyWebData();
    }

    public ArrayList<Team> getCurrentTeams() {
        return currentTeams;
    }



    @Override
    public void load() throws IOException {
        try {
            FileInputStream fis = new FileInputStream("t.tmp");
            ObjectInputStream ois = new ObjectInputStream(fis);
            currentTeams = (ArrayList<Team>) ois.readObject();
            ois.close();
        } catch (FileNotFoundException | ClassNotFoundException e) {
            System.out.println("No file available for load");
            currentTeams = new ArrayList<>();
        }

    }

    @Override
    public void save() throws IOException {
        FileOutputStream fos = new FileOutputStream("t.tmp");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(currentTeams);
        oos.close();
    }

    public void addTeam(Team team) {
        currentTeams.add(team);
    }

    public void removeTeam(Team team) {
        currentTeams.remove(team);
    }

    public Team getTeam(int choice) {
        return currentTeams.get(choice);
    }

    public void updateFromWeb() {
        ArrayList<Player> temp = fantasyWebData.getWebpool();
        for (Team team: currentTeams) {
            for (Player player: team.getTeamplayers()) {
                for (int i = 0; i < temp.size(); i++) {

                    Player next = temp.get(i);
                    if (player.getName().equals(next.getName())) {
                        player.setOverall(next.getOverall());
                        System.out.println(player);
                    }
                }
            }
        }
    }

}
