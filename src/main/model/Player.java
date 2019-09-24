package model;

public class Player {
    private int id;
    private String name;
    private double overall;

    public Player(int id, String name, double overall) {
        this.id = id;
        this.name = name;
        this.overall = overall;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getOverall() {
        return overall;
    }

    public void setOverall(double overall) {
        this.overall = overall;
    }

    public String toString() {
        return "(" + id + ")" + " " + name + " " + overall;
    }

}
