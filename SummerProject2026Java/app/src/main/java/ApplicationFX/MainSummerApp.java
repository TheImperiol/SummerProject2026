package ApplicationFX;

import javafx.application.*;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class MainSummerApp extends Application{

    public static MainSummerApp app;
    public Structure frontend = new Structure();
    @Override
    public void start(Stage stage) throws Exception {
        VBox vBox = new VBox();
        
        Separator sep = new Separator();
        //VBox.setVgrow(frontend.gpAppTopBar, Priority.ALWAYS );

        ScriptCard card = new ScriptCard(0.87888888888888888f, 8.78f);
        //DeviceCard devCard = new DeviceCard("Device", "desc");
        frontend.gpSidebarContent.add(card, 0, 0);

        vBox.getChildren().addAll(frontend.gpAppTopBar,sep);

        Scene mainScene = new Scene(vBox);
        stage.setScene(mainScene);
        stage.setHeight(800);
        stage.setWidth(1200);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public MainSummerApp(){
        app = this;
    }

}
