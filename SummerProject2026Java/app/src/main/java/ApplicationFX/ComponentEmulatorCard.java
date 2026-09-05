package ApplicationFX;



import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import java.lang.Math;
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
                PCB _pcb = MainSummerApp.app.frontend.emulatorWindow.pcb;

                double clampedx = Math.clamp(
                    localPos.getX() - (GetSelf().getWidth() / 2),
                    _pcb.getLayoutX(),
                    _pcb.getLayoutX() + (_pcb.getPrefWidth() - GetSelf().getPrefWidth()) 
                );

                double clampedy = Math.clamp(
                    localPos.getY() - (GetSelf().getHeight() / 2),
                    _pcb.getLayoutY(),
                    _pcb.getLayoutY() + (_pcb.getPrefHeight() - GetSelf().getPrefHeight())
                );

                GetSelf().relocate(clampedx,clampedy);
                event.consume();
            }
        });
    }
    
}
