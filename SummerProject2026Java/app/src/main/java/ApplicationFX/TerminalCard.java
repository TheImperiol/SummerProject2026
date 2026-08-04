package ApplicationFX;

import javafx.scene.control.Label;

public class TerminalCard extends GeneralCard{
    public TerminalCard(String content){
        super();
        this.SetCardTitle(content);
        this.getChildren().add(new Label(GetCardTitle()));
        this.setStyle(null);
    }
}
