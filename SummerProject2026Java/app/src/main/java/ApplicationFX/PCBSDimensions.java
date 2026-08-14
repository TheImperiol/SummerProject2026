package ApplicationFX;

import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class PCBSDimensions extends VBox {
    private TextField PCBX = new TextField();
    private TextField PCBY = new TextField();
    public PCBSDimensions(){
        PCBX.setPromptText("PCB X");
        PCBY.setPromptText("PCB Y");
        this.setPrefSize(USE_PREF_SIZE, USE_PREF_SIZE);
        this.getChildren().addAll(PCBX,PCBY);
    }
}
