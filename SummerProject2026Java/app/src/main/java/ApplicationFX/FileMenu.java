package ApplicationFX;

import java.io.File;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class FileMenu extends MenuButton {
    public FileMenu(){
        setText("File");
        MenuItem openProject = new MenuItem("Open Project");
        MenuItem newProject = new MenuItem("New Project");
        MenuItem saveProject = new MenuItem("Save Project");


        openProject.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent event){
                System.out.println("Opening");
                MenuItem source = (MenuItem) event.getSource();
                Stage currentStage = (Stage) source.getParentPopup().getOwnerWindow();
                FileSystem.OpenProject(currentStage);
            }
        });

        newProject.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent event){
                System.out.println("Creating new");
                MenuItem source = (MenuItem) event.getSource();
                Stage currentStage = (Stage) source.getParentPopup().getOwnerWindow();
                FileSystem.NewProject(currentStage);
            }
        });

        saveProject.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent event){
                System.out.println("Saving");
                FileSystem.SaveProject();
            }
        });

        getItems().addAll(
            openProject,
            newProject,
            saveProject
        );
    }
}
