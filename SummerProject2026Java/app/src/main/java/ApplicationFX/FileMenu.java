package ApplicationFX;

import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

public class FileMenu extends MenuButton {
    public FileMenu(){
        setText("File");
        getItems().addAll(
            new MenuItem("Open Project"),
            new MenuItem("Save Project"),
            new MenuItem("New Project")
        );
    }
}
