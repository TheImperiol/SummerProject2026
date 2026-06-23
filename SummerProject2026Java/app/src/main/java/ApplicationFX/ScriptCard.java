package ApplicationFX;

import javafx.scene.control.*;

public class ScriptCard extends DraggableCard {
    public ScriptCard(float x, float y){
        super(x, y);
        Label test = new Label(Float.toString(GetX()));
        Label test2 = new Label(Float.toString(GetY()));

        this.getChildren().addAll(test,test2);
    }
}
