package data;

import model.Team;

import java.io.*;
import java.util.ArrayList;

public class Eventlog implements Serializable, Loadable, Saveable {

    public ArrayList<String> allEvents;

    public Eventlog() {
        allEvents = new ArrayList<>();
    }

    @Override
    public void load() throws IOException {
        try {
            FileInputStream fis = new FileInputStream("e.tmp");
            ObjectInputStream ois = new ObjectInputStream(fis);
            allEvents = (ArrayList<String>) ois.readObject();
            ois.close();
        } catch (FileNotFoundException | ClassNotFoundException e) {
            System.out.println("No file available for load");
            allEvents = new ArrayList<>();
        }

    }

    @Override
    public void save() throws IOException {
        FileOutputStream fos = new FileOutputStream("e.tmp");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(allEvents);
        oos.close();
    }

    public void addEvent(String event) {
        allEvents.add(event);
    }

//    public void removeEvent(String event) {
//        allEvents.remove(event);
//    }


    public ArrayList<String> getAllEvents() {
        return allEvents;
    }

    public void printAll() {
        for (String event : allEvents) {
            System.out.println(event);
            System.out.println("==============================\n");
        }
    }

}
