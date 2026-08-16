package ApplicationFX;



import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class PCBSDimensions extends VBox {
    private TextField PCBX = new TextField();
    private TextField PCBY = new TextField();
    private Button confirm = new Button();
    public PCBSDimensions(EmulatorWindow window){
        PCBX.setPromptText("PCB X");
        PCBY.setPromptText("PCB Y");
        confirm.setText("Enter");

        //this.setPrefSize(USE_PREF_SIZE, USE_PREF_SIZE);
        this.getChildren().addAll(PCBX,PCBY,confirm);

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
        confirm.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event){
                if(PCBX.getText() != null || PCBY.getText() != null){
                    window.pcb.SetPCBSize(Double.parseDouble(PCBX.getText().toString()), Double.parseDouble(PCBY.getText().toString()));
                    System.out.println("confirmed new dimensions");
                }
            }
        });     
        
    }
}
