package ApplicationFX;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import com.google.gson.Gson;

import javafx.application.*;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
public class MainSummerApp extends Application{

    public static MainSummerApp app;
    public Structure frontend;
    @Override
    public void start(Stage stage) throws Exception {
        VBox vBox = new VBox();

        Separator sep = new Separator();
        frontend = new Structure(stage);


        //ScriptCard card = new ScriptCard(0.87888888888888888f, 8.78f);
        DeviceCard devCard = new DeviceCard("Device", "desc");
        
        
        //frontend.gpSidebarContent.add(card, 0, 0);
        vBox.getChildren().addAll(frontend,sep);
        VBox.setVgrow(frontend, Priority.ALWAYS );

        //frontend.gpSidebarContent.add(devCard, 0,0);
        
        frontend.AddDeviceToStack(devCard);

        System.out.println("frontend height and width: " + frontend.getHeight() + " " + frontend.getWidth());

        Scene mainScene = new Scene(vBox);
        stage.setScene(mainScene);
        stage.setTitle("Electronics Emulator");
        //stage.setHeight(800);
        //stage.setWidth(1200);
        stage.sizeToScene();
        stage.show();
        /*ArrayList<ExtensionFilter> fil = new ArrayList<ExtensionFilter>();
        fil.add(new ExtensionFilter("PNG", "*.png"));
        File chosenFile = FileSystem.OpenExplorer(
            "Testing singleton",
             stage,
             fil);
        if(chosenFile != null){
            System.out.println(chosenFile.getPath());
        }
        else{
            System.out.println("error in file system occoured");
        }*/
            //Project testJson = new Project("C:/development/SummerProject2026/SummerProject2026Java/app/src/main/Files/TestProjLoc.json");
            //FileSystem.SaveProject(testJson);
            //Gson gson = new Gson();
            //String result = gson.toJson(testJson);
            //System.out.println(result);

        //FileSystem.OpenProject(stage);
        FileSystem.NewProject(stage);
    }

    

    public static void main(String[] args) {
        launch(args);
    }

    public MainSummerApp(){
        app = this;
    }

}
