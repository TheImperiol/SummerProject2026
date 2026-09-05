package ApplicationFX;


import com.google.gson.Gson;

import javafx.application.*;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class MainSummerApp extends Application{

    public static MainSummerApp app;
    public Structure frontend;

    private boolean drawingMode = false;

    public boolean GetDrawingMode(){return drawingMode;}

    public void ToggleDrawingMode(){ drawingMode = (!drawingMode);}

    

    public void InitialiseProject(Project project){
        frontend.devicesPane.getChildren().clear();
        frontend.scriptsPane.getChildren().clear();
        frontend.componentsPane.getChildren().clear();
        Gson gson = new Gson();
        for(String device : project.GetDevices()){
            DeviceData cardData = gson.fromJson(device,DeviceData.class);
            if(cardData != null){
                frontend.devicesPane.getChildren().add(new DeviceCard(cardData));
            }
        }

        for(String script : project.GetScript()){
            ScriptData cardData = gson.fromJson(script,ScriptData.class);
            if(cardData != null){
                frontend.scriptsPane.getChildren().add(new ScriptCard(0, 0, "path"));
            }
        }

        for(String component : project.GetComponents()){
            ComponentData cardData = gson.fromJson(component,ComponentData.class);
            if(cardData != null){
                frontend.componentsPane.getChildren().add(new ComponentCard(cardData));
            }
        }
    }

    public void UpdateProject(Project project){
        Gson gson = new Gson();

        for(Object device : frontend.devicesPane.getChildren().toArray()){
            try{
            DeviceCard convertedDevice = (DeviceCard)device;
            project.AddDevice(gson.toJson(convertedDevice.data));
            } catch (Exception e){
                System.err.println();
            }
        }

        for(Object script : frontend.scriptsPane.getChildren().toArray()){
            try{
            ScriptCard convertedScript = (ScriptCard)script;
            project.AddScript(gson.toJson(convertedScript.data));
            } catch (Exception e){
                System.err.println();
            }

        }

        for(Object component : frontend.componentsPane.getChildren().toArray()){
            try{
            ComponentCard convertedComponent = (ComponentCard)component;
            project.AddComponent(gson.toJson(convertedComponent.data));
            } catch (Exception e){
                System.err.println();
            }
        }

    }

    @Override
    public void start(Stage stage) throws Exception {
        VBox vBox = new VBox();

        Separator sep = new Separator();
        frontend = new Structure(stage);

        //ScriptCard card = new ScriptCard(0.87888888888888888f, 8.78f);
        //DeviceCard devCard = new DeviceCard("Device", "desc");
        ComponentCard compCard = new ComponentCard(0.1f,0.2f,"Path");
        frontend.AddComponentToStack(compCard);
        //frontend.gpSidebarContent.add(card, 0, 0);
        vBox.getChildren().addAll(frontend,sep);
        VBox.setVgrow(frontend, Priority.ALWAYS );

        //frontend.gpSidebarContent.add(devCard, 0,0);
        
        //frontend.AddDeviceToStack(devCard);

        //UpdateProject(null);

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
        //FileSystem.NewProject(stage);

      
    }

    public void CloseApp(){
        Platform.exit();
    }
    

    public static void main(String[] args) {
        launch(args);
    }

    public MainSummerApp(){
        app = this;
    }

}
