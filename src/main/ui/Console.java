package ui;

import java.io.IOException;
import java.io.OutputStream;

import javafx.scene.control.TextArea;

//Code Reference
//https://stackoverflow.com/questions/13841884/redirecting-system-out-to-a-textarea-in-javafx
public class Console extends OutputStream {
    private TextArea output;

    public Console(TextArea ta) {
        this.output = ta;
    }


    //MODIFIES: this
    //EFFECTS: reditect system.out.println to the TextArea Output

    @Override
    public void write(int i) throws IOException {
        output.appendText(String.valueOf((char) i));
    }

}