package UI;

public class PlayerLog {
     private String name;
     private int overall;

     public PlayerLog(){
     name = "";
     overall = 60;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRating(int rating) {
        this.overall = rating;
    }

    public String toString() {
        return name + " " + overall;
    }

}
