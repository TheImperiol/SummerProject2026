package ApplicationFX;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class PCB extends Pane{

  

    public void SetPCBSize(double _width, double _height){
        this.setPrefSize(_width, _height);
    }

    private PCB getSelf(){return this;}

    private ArrayList<Track> tracks = new ArrayList<Track>();

    public void AddTrack(Track track){
        tracks.add(track);
    }

    public PCB (){
        this.setStyle("-fx-background-color: #347d1c");
        this.setPrefSize(100.0,100.0);
 
    }
}
