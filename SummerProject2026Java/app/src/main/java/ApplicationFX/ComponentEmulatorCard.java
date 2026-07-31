package ApplicationFX;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Base64;

import javafx.event.EventHandler;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

public class ComponentEmulatorCard extends DraggableCard{

    public ComponentEmulatorCard(float x, float y) {
        super(x, y);
        
        this.setOnDragDetected((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent event) {
                Dragboard db = startDragAndDrop(TransferMode.ANY);
                Clipboard clipboard = Clipboard.getSystemClipboard();
                ClipboardContent content = new ClipboardContent();
                System.out.println("emulator variant");
                /*
                try(ByteArrayOutputStream bytStream = new ByteArrayOutputStream();
                    ObjectOutputStream objStream = new ObjectOutputStream(bytStream)){
                    objStream.writeObject(wrapper);
                    content.putString(Base64.getEncoder().encodeToString(bytStream.toByteArray()));
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                db.setContent(content);
                */
                event.consume();
            }
        });
    }
    
}
