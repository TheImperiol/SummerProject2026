package ApplicationFX;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;

import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public final class FileSystem {
    private static Project currentProject;
    private FileSystem(){
        
    }

    public static File OpenExplorer(String title, Stage stage, ArrayList<ExtensionFilter> filters){
        File returnFile = null;
        FileChooser handler = new FileChooser();
        try{
            handler.setTitle(title);
            handler.getExtensionFilters().addAll(filters);
            returnFile = handler.showOpenDialog(stage);
        } catch(Exception e){
            System.out.println("File system failed");
            return null;
        }

        handler.getExtensionFilters().clear();
        return returnFile;
    }

    public static File DirectoryExplorer(String title, Stage stage){
        DirectoryChooser directoryChooser =  new DirectoryChooser();

        directoryChooser.setTitle(title);

        File selectedDirectory = directoryChooser.showDialog(stage);

        if(selectedDirectory != null){
            return selectedDirectory;
        }
        else{
            return null;
        }
    }

    public static void OpenProject(Stage stage){
        ArrayList<ExtensionFilter> filter = new ArrayList<ExtensionFilter>();
        filter.add(new ExtensionFilter("Project", "*.json"));
        File project = OpenExplorer("Select Project",stage , filter);

        Gson gson = new Gson();

        try(FileReader reader = new FileReader(project)){
            Project proj = gson.fromJson(reader, Project.class);
            if(proj == null){
                System.out.println(proj.GetDevices());
                return;
            }
            System.out.println(proj.GetPath());
            currentProject = proj;
        } catch(Exception e){
            System.out.println(e);
        }
    }

    public static void SaveProject(){
        //String convertedProj = ObjToJSON(proj);
        if(currentProject == null){
            System.out.println("No open Project");
            return;
        }
        Gson gson = new Gson();
        MainSummerApp.app.UpdateProject(currentProject);
        try(FileWriter writer = new FileWriter(currentProject.GetPath())){
            gson.toJson(currentProject,writer);
        } catch(IOException e){
            System.out.print(e);
        }
    }

    public static void NewProject(Stage stage){
        File destination = DirectoryExplorer("Select output Directory", stage);
        System.out.println(destination.getPath());
        ArrayList<String> _devices = new ArrayList<String>();
        ArrayList<String> _scripts = new ArrayList<String>();
        ArrayList<String> _components = new ArrayList<String>();
        Project proj = new Project(_devices, _scripts, _components, destination.getPath() + "\\Test.json");
        currentProject = proj;

        SaveProject();
    }

    public static DeviceCard OpenDevice(Stage stage){
        ArrayList<ExtensionFilter> filter = new ArrayList<ExtensionFilter>();
        filter.add(new ExtensionFilter("Device", "*.json"));
        File device = OpenExplorer("Select Device",stage , filter);
        DeviceCard result = null;
        Gson gson = new Gson();

        try(FileReader reader = new FileReader(device)){
            DeviceData deviceData = gson.fromJson(reader, DeviceData.class);
            if(deviceData == null){
                System.out.println("Failed");
                return null;
            }
            result = new DeviceCard(deviceData);
        } catch(Exception e){
            System.out.println(e);
        }
        return result;
    }

    public static ScriptCard OpenScript(Stage stage){
        ArrayList<ExtensionFilter> filter = new ArrayList<ExtensionFilter>();
        filter.add(new ExtensionFilter("Script", "*.json"));
        File script = OpenExplorer("Select Script",stage , filter);
        ScriptCard result = null;
        Gson gson = new Gson();

        try(FileReader reader = new FileReader(script)){
            ScriptData scriptData = gson.fromJson(reader, ScriptData.class);
            if(scriptData == null){
                System.out.println("Failed");
                return null;
            }
            result = new ScriptCard(scriptData);
        } catch(Exception e){
            System.out.println(e);
        }
        return result;
    }
}
