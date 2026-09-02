package ApplicationFX;

import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class TrackSnapPoint extends Circle{

    private Circle getSelf(){return this;}

    protected Track owningTrack;

    public TrackSnapPoint(Track owner){
        super();
        this.setFill(Color.RED);
        this.setOpacity(0);
        setRadius(5);
        owningTrack = owner;

        this.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent event){
                if(MainSummerApp.app.GetDrawingMode() == true){
                getSelf().setOpacity(0.5);
            }
        }
        });

        this.setOnMouseDragOver(new EventHandler<MouseDragEvent>() {
             @Override public void handle(MouseDragEvent event){
                System.out.println("dragged over snap");
                if(MainSummerApp.app.GetDrawingMode() == true){
                    System.out.println("drawing true");
                    getSelf().setFill(Color.BLUE);
                    getSelf().setOpacity(0.5);
            }
        }
    });

        this.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent event){
                if(MainSummerApp.app.GetDrawingMode() == true){
                    getSelf().setOpacity(0);
                }
            }
        });

        this.setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override public void handle(MouseEvent event){
                if(MainSummerApp.app.GetDrawingMode()){
                    owningTrack.owningPCB.setTrackStart(getSelf().getCenterX(), getSelf().getCenterY());
                }
                event.consume();
            }
        });
    }


}
