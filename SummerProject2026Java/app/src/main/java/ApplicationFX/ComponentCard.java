package ApplicationFX;

import ProtoMessages.CardWrapperProto.CardWrapper;
import javafx.scene.control.Label;

public class ComponentCard extends DraggableCard{
    public ComponentData data;
    public ComponentCard(float x, float y, String path){
        this(new ComponentData(x, y, path));
    }
    public ComponentCard(ComponentData _data){
        super(_data.x, _data.y);
        wrapper = CardWrapper.newBuilder().setComponentCard(ProtoMessages.ComponentCardOuterClass.ComponentCard.newBuilder().setName("Component").build()).build();
        data = _data;
        this.getChildren().add(new Label("Component"));
    }
}
