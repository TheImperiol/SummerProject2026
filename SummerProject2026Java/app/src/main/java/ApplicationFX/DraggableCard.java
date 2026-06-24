package ApplicationFX;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javafx.event.EventHandler;
import javafx.scene.control.Control;
import javafx.scene.input.*;


public abstract class DraggableCard extends GeneralCard implements Serializable{
    protected float[] position = new float[2];

    public ArrayList<Control> children = new ArrayList<Control>();
    public float GetX(){
        return position[0];
    }

    public DraggableCard GetCard(){
        return this;
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
                Clipboard clipboard = Clipboard.getSystemClipboard();
                ClipboardContent content = new ClipboardContent();

                try(ByteArrayOutputStream bytStream = new ByteArrayOutputStream();
                    ObjectOutputStream objStream = new ObjectOutputStream(bytStream)){
                    objStream.writeObject(wrapper);
                    content.putString(Base64.getEncoder().encodeToString(bytStream.toByteArray()));
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                db.setContent(content);
                event.consume();
            }
        });

        
    }
}
