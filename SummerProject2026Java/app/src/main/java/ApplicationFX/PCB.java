package ApplicationFX;

import javafx.scene.layout.Pane;

public class PCB extends Pane{

    public void SetPCBSize(double _width, double _height){
        this.setPrefSize(_width, _height);
    }

    public PCB (){
        this.setStyle("-fx-background-color: #347d1c");
        this.setPrefSize(100.0,100.0);
    }
}
