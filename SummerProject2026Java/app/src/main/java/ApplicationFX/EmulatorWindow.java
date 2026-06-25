package ApplicationFX;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Base64;

import ProtoMessages.CardWrapperProto.CardWrapper;
import javafx.event.EventHandler;
import javafx.scene.input.Clipboard;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;

public class EmulatorWindow extends Pane {
    public EmulatorWindow(){
        this.setOnDragOver(new EventHandler<DragEvent>() {
            @Override public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                if (db.hasString()) {
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }
            event.consume();
            }
        });

        

        this.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                Clipboard clipboard = Clipboard.getSystemClipboard();
                boolean success = false;
                if( clipboard.hasString()){
                    System.out.println("Dropped: "+ clipboard.getString());
                    success = true;
                    final byte[] bytes = Base64.getDecoder().decode(clipboard.getString());
                    try(ByteArrayInputStream bytStream = new ByteArrayInputStream(bytes);
                    ObjectInputStream objStream = new ObjectInputStream(bytStream)){
                        CardWrapper deserializDraggableCard = (CardWrapper) objStream.readObject();
                        ProtoHandler.WrapperHandler(deserializDraggableCard);
                    } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                }
                event.setDropCompleted(success);
                event.consume();
            }
        });

        this.setOnScroll(new EventHandler<ScrollEvent>(){
            @Override public void handle(ScrollEvent event){
                getSelf().setScaleX(getSelf().getScaleY() +  (event.getDeltaY() / (Math.abs(event.getDeltaY()) * 10)));
                getSelf().setScaleY(getSelf().getScaleY() +  (event.getDeltaY() / (Math.abs(event.getDeltaY()) * 10)));
                System.out.println(getSelf().getScaleX() + (event.getDeltaY() / (Math.abs(event.getDeltaY()) * 10)));
                System.out.println(getSelf().getScaleY() +  (event.getDeltaY() / (Math.abs(event.getDeltaY()) * 10)));
            }
        });

        this.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent event){
                System.out.println("mouse dragged");
                
            }
        });
    }

    private EmulatorWindow getSelf(){return this;}

}
