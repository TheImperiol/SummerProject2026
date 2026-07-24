package ApplicationFX;

import javafx.scene.control.*;

public class ScriptCard extends DraggableCard {
    public ScriptData data;
    public ScriptCard(float x, float y, String _path){
        super(x, y);
        data = new ScriptData(x, y, _path);
        Label test = new Label(Float.toString(GetX()));
        Label test2 = new Label(Float.toString(GetY()));

        this.getChildren().addAll(test,test2);
    }
    public ScriptCard(ScriptData _data){
        super(_data.x,_data.y);
        data = _data;
        Label test = new Label(Float.toString(GetX()));
        Label test2 = new Label(Float.toString(GetY()));

        this.getChildren().addAll(test,test2);
    }
}
