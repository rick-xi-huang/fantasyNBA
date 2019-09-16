package UI;

public class PlayerLog {
     private int id;
     private String name;
     private double overall;

     PlayerLog(int id, String name, double overall){
         this.id = id;
         this.name = name;
         this.overall = overall;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRating(double overall) {
        this.overall = overall;
    }

    public String toString() {
        return "(" + id + ")" + " " + name + " " + overall;
    }

}
