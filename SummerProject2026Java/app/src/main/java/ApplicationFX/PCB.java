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

    private ArrayList<Line> tracks = new ArrayList<Line>();

    private Line currentTrack;

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
                    if(currentTrack == null){
                        currentTrack = new Line(
                            drawnTrackStart[0],
                            drawnTrackStart[1],
                            event.getX(),
                            event.getY()
                        );
                        getSelf().getChildren().add(currentTrack);

                    }
                    else{
                        currentTrack.setEndX(event.getX());
                        currentTrack.setEndY(event.getY());
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
