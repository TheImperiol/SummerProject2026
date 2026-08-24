package ApplicationFX;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

public class PCB extends Pane{

    private double[] drawnTrackStart = new double[2];

    public void SetPCBSize(double _width, double _height){
        this.setPrefSize(_width, _height);
    }

    private PCB getSelf(){return this;}

    private ArrayList<Track> tracks = new ArrayList<Track>();

    private Track currentTrack;

    public PCB (){
        this.setStyle("-fx-background-color: #347d1c");
        this.setPrefSize(100.0,100.0);

        this.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent event){
                if(MainSummerApp.app.GetDrawingMode() == true){
                    System.out.println("start drawing");
                    drawnTrackStart[0] = event.getX();
                    drawnTrackStart[1] = event.getY();
                }
                event.consume();
            }
        });

        this.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent event){
                if(MainSummerApp.app.GetDrawingMode() == true){
                    double endX = Math.clamp(event.getX(), 0,getSelf().getPrefWidth());
                    double endY = Math.clamp(event.getY(), 0, getSelf().getPrefHeight());
                    if(currentTrack == null){
                        currentTrack = new Track(
                            drawnTrackStart[0],
                            drawnTrackStart[1],
                            endX,
                            endY
                        );
                        getSelf().getChildren().add(currentTrack);

                    }
                    else{
                        currentTrack.UpdateEndPoint(endX, endY);
                    }
                }
                event.consume();
            }
        });

        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent event){
                if(MainSummerApp.app.GetDrawingMode() == true){
                    if(currentTrack != null){
                        tracks.add(currentTrack);
                        currentTrack = null;
                    }
                }
            }
        });
        
    }
}
