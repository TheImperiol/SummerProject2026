package ApplicationFX;

import java.io.File;
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

    public static void OpenProject(File project){
        
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

    private static Project ConvertToProject(String json){
        Gson gson = new Gson();
        return gson.fromJson(json, Project.class);
    }
}
