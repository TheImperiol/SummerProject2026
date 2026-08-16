package ApplicationFX;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Base64;

import ProtoMessages.CardWrapperProto.CardWrapper;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class EmulatorWindow extends Pane {
    private Point2D previousDragPos;
    private DeviceCard device;
    private VBox overlay = new VBox();
    public Button closeDevice;
    public PCB pcb = new PCB();
    public PCBSDimensions dimensions = new PCBSDimensions(this);

    public void SetDevice(DeviceCard openedDevice){
        closeDevice.setVisible(true);
        device = openedDevice;
        this.getChildren().clear();
        UpdateWindow();
    }

    public boolean DevicePresent(){
        return (device != null);
    }

    public void EmptyDevice(){
        closeDevice.setVisible(false);
        device = null;
        this.getChildren().clear();
        UpdateWindow();
    }

    private void UpdateWindow(){
        if(device != null){
            this.getChildren().add(overlay);
            this.getChildren().add(pcb);
            pcb.relocate(this.getWidth() / 2,this.getHeight() / 2);
        }
        else{
            Label placeholder = new Label("No Device currently opened");
            placeholder.setFont(new Font(30.0));
            this.getChildren().add(placeholder);
        }
    }

    public EmulatorWindow(double _width, double _height){
        this.setHeight(_height);
        this.setWidth(_width);
       
        //pcb.relocate(_width / 2, _height / 2);

        System.out.println(_height / 2 + " " + _width/2);

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
                //getSelf().setScaleX(getSelf().getScaleY() +  (event.getDeltaY() / (Math.abs(event.getDeltaY()) * 10)));
                //getSelf().setScaleY(getSelf().getScaleY() +  (event.getDeltaY() / (Math.abs(event.getDeltaY()) * 10)));
                getSelf().getChildren().remove(overlay);

                for (Node child : getSelf().getChildren()){
                    child.setScaleX(Math.clamp(child.getScaleY() +  (event.getDeltaY() / (Math.abs(event.getDeltaY()) * 10)),0,999));
                    child.setScaleY(Math.clamp(child.getScaleY() +  (event.getDeltaY() / (Math.abs(event.getDeltaY()) * 10)),0,999));
                }
                getSelf().getChildren().add(overlay);

                event.consume();
            }
        });

        this.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent event){
                getSelf().getChildren().remove(overlay);
                getSelf().getChildren().forEach(node -> {
                    node.relocate(
                        node.getLayoutX() + (event.getSceneX() - previousDragPos.getX()),
                         node.getLayoutY() + (event.getSceneY() - previousDragPos.getY())
                        );
                    });
                previousDragPos = new Point2D(event.getSceneX(),event.getSceneY());
                getSelf().getChildren().add(overlay);
                event.consume();
            }
        });

        this.setOnMousePressed(new EventHandler<MouseEvent>() {
            
            @Override public void handle(MouseEvent event){
                System.out.println("dragging: ");
                previousDragPos = new Point2D(event.getSceneX(),event.getSceneY());
                event.consume();
            }
        });

        closeDevice = new Button();

        overlay.layoutXProperty().bind(
            widthProperty().subtract(closeDevice.widthProperty()).subtract(100)
        );

        overlay.setLayoutY(10);


        closeDevice.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event){
                EmptyDevice();
            }
        });      

        overlay.setSpacing(10);

        overlay.getChildren().addAll(closeDevice,dimensions);

        UpdateWindow();

    }



    private EmulatorWindow getSelf(){return this;}

}
