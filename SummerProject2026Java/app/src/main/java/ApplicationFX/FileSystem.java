package ApplicationFX;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;

import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import com.google.gson.stream.JsonWriter;

public final class FileSystem {
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
        } catch(Exception e){
            System.out.println(e);
        }
    }

    public static void SaveProject(Project proj){
        //String convertedProj = ObjToJSON(proj);
        Gson gson = new Gson();
        try(FileWriter writer = new FileWriter(proj.GetPath())){
            gson.toJson(proj,writer);
        } catch(IOException e){
            System.out.print(e);
        }
    }

    public static void NewProject(Stage stage){
        Gson gson = new Gson();
        File destination = DirectoryExplorer("Select output Directory", stage);
        System.out.println(destination.getPath());
        ArrayList<String> _devices = new ArrayList<String>();
        ArrayList<String> _scripts = new ArrayList<String>();
        ArrayList<String> _components = new ArrayList<String>();
        Project proj = new Project(_devices, _scripts, _components, destination.getPath() + "\\Test.json");
        SaveProject(proj);
    }

    private static Project ConvertToProject(String json){
        Gson gson = new Gson();
        return gson.fromJson(json, Project.class);
    }
}
