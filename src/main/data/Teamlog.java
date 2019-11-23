package data;

import model.Team;

import java.io.*;
import java.util.ArrayList;

public class Teamlog implements Serializable, Loadable, Saveable {

    public ArrayList<Team> currentTeams;

    public Teamlog() {
        currentTeams = new ArrayList<>();
    }

    public ArrayList<Team> getCurrentTeams() {
        return currentTeams;
    }

//    code reference
//    https://docs.oracle.com/javase/7/docs/api/java/io/ObjectInputStream.html
//    https://docs.oracle.com/javase/7/docs/api/java/io/ObjectOutputStream.html

    //load current teams from the file
    //MODIFIES: this
    //EFFECTS: load the arraylist of currentTeams objects from the file

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

    //save current teams to the file
    //MODIFIES: this
    //EFFECTS: save the arraylist of currentTeams objects to the file
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

}
