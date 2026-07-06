package ApplicationFX;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

public class FileMenu extends MenuButton {
    public FileMenu(){
        setText("File");
        MenuItem openProject = new MenuItem("Open Project");
        MenuItem newProject = new MenuItem("New Project");
        MenuItem saveProject = new MenuItem("Save Project");


        openProject.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent event){
                System.out.println("Opening");
            }
        });

        newProject.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent event){
                System.out.println("Creating new");
            }
        });

        saveProject.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent event){
                System.out.println("Saving");
            }
        });

        getItems().addAll(
            openProject,
            newProject,
            saveProject
        );
    }
}
