package ApplicationFX;

import javafx.scene.control.Label;

public class ComponentCard extends DraggableCard{
    public ComponentData data;
    public ComponentCard(float x, float y, String path){
        this(new ComponentData(x, y, path));
    }
    public ComponentCard(ComponentData _data){
        super(_data.x, _data.y);
        data = _data;
        this.getChildren().add(new Label("Component"));
    }
}
