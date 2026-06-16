package ApplicationFX;

import javafx.scene.layout.HBox;
import javafx.scene.control.*;

public class GeneralCard extends HBox{
    String title;
    Label cardLabel;
    public GeneralCard() {
        title = "General card";
        cardLabel = new Label(title);
        this.getChildren().add(cardLabel);
    }
}
