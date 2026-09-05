package ApplicationFX;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class Terminal extends ScrollPane{
    private VBox items = new VBox();

    public void LogToTerminal(TerminalCard card){
        items.getChildren().add(card);
    }

    public Terminal(){
        this.setFitToWidth(true);
        this.setContent(items);
    }
}
