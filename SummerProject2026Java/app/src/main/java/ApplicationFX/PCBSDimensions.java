package ApplicationFX;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class PCBSDimensions extends VBox {
    private TextField PCBX = new TextField();
    private TextField PCBY = new TextField();
    public PCBSDimensions(){
        PCBX.setPromptText("PCB X");
        PCBY.setPromptText("PCB Y");
        //this.setPrefSize(USE_PREF_SIZE, USE_PREF_SIZE);
        this.getChildren().addAll(PCBX,PCBY);

        PCBX.textProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    PCBX.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        PCBY.textProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    PCBY.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        
    }
}
