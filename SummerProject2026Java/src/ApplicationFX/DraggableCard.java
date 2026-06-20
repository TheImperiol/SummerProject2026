package ApplicationFX;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javafx.event.EventHandler;
import javafx.scene.input.*;


public abstract class DraggableCard extends GeneralCard implements Serializable{
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
                DataFormat fmt = new DataFormat("DraggableCard/card");
                Dragboard db = startDragAndDrop(TransferMode.ANY);
                Clipboard clipboard = Clipboard.getSystemClipboard();
                ClipboardContent content = new ClipboardContent();
                
                //content.put(fmt, this);
                ByteArrayOutputStream bytStream = new ByteArrayOutputStream();
                

                //db.setContent(content);
                event.consume();
            }
        });

        
    }
}
