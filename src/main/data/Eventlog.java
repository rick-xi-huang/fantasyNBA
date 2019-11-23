package data;

import java.io.*;
import java.util.ArrayList;

public class Eventlog implements Serializable, Loadable, Saveable {

    private ArrayList<String> allEvents;

    public Eventlog() {
        allEvents = new ArrayList<>();
    }

//    code reference
//    https://docs.oracle.com/javase/7/docs/api/java/io/ObjectInputStream.html
//    https://docs.oracle.com/javase/7/docs/api/java/io/ObjectOutputStream.html

    //load event strings list from the file
    //MODIFIES: this
    //EFFECTS: load the arraylist of event String from the file
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

    //Save event strings list to the file
    //MODIFIES: this
    //EFFECTS: Save the arraylist of event String to the file

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

    public void update(ArrayList<String> allEvents) {
        this.allEvents = allEvents;
    }

    public ArrayList<String> getAllEvents() {
        return allEvents;
    }

    //MODIFIES:
    //EFFECTS: Print all historical events

    public void printAll() {
        for (String event : allEvents) {
            System.out.println(event);
            System.out.println("==============================\n");
        }
    }

}
