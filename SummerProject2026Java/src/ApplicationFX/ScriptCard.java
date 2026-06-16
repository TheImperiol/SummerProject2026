package ApplicationFX;

import javafx.scene.control.*;

public class ScriptCard extends DraggableCard {
    public ScriptCard(float x, float y){
        SetX(x);
        SetY(y);
        Label test = new Label(Float.toString(GetX()));
        this.getChildren().add(test);
    }
}
