package data;

import model.Team;

import java.io.*;
import java.util.ArrayList;

public class Worldlog implements Serializable, Loadable, Saveable {

    public ArrayList<Team> currentTeams;


    public Worldlog() {
    }

    public Worldlog(ArrayList<Team> currentTeams) {
        this.currentTeams = currentTeams;
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
        }

    }

    @Override
    public void save() throws IOException {
        FileOutputStream fos = new FileOutputStream("t.tmp");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(currentTeams);
        oos.close();
    }
}
