package ApplicationFX;

import java.io.File;
import java.util.ArrayList;

import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

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

    public static void SaveProject(){
        
    }
}
