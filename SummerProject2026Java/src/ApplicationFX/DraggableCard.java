package ApplicationFX;

import javafx.event.EventHandler;
import javafx.scene.input.*;


public abstract class DraggableCard extends GeneralCard{
    protected float[] position = new float[2];

    public float GetX(){
        return position[0];
    }

    public void SetX(float x){
        position[0] = x;
    }

    public float GetY(){
        return position[1];
    }

    public void SetY(float y){
        position[1] = y;
    }
    public DraggableCard(float x, float y){
        super();
        SetX(x);
        SetY(y);

        

        this.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent event) {
                Dragboard db = startDragAndDrop(TransferMode.ANY);
                ClipboardContent content = new ClipboardContent();
                
                content.putString("Hello!");
                db.setContent(content);
                event.consume();
            }
        });

        
    }
}
