package ApplicationFX;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Base64;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.robot.Robot;

public class ComponentEmulatorCard extends DraggableCard{

    private ComponentEmulatorCard GetSelf(){
        return this;
    }

    public ComponentEmulatorCard(float x, float y) {
        super(x, y);

        
        this.setOnDragDetected((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent event) {
                event.consume();
            }
        });

        this.setOnMouseDragged(new EventHandler<MouseEvent> (){
            @Override public void handle(MouseEvent event){
                Robot robot = new Robot();
                Point2D mousePos = robot.getMousePosition();
                Point2D localPos = MainSummerApp.app.frontend.emulatorWindow.screenToLocal(mousePos);
                GetSelf().relocate(localPos.getX() - (GetSelf().getWidth() / 2),localPos.getY() - (GetSelf().getHeight() / 2));
                event.consume();
            }
        });
    }
    
}
