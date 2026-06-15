package ApplicationFX;

import javafx.application.*;
import javafx.scene.control.*;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
public class MainSummerApp extends Application{
   

    @Override
    public void start(Stage stage) throws Exception {
        VBox vBox = new VBox();
        GridPane gpMain = new GridPane();
        GridPane gpSidebar = new GridPane();
        Separator sep = new Separator();

        gpMain.setGridLinesVisible(true);

        gpSidebar.setGridLinesVisible(true);

        ColumnConstraints mainColumnSidebar = new ColumnConstraints();
        mainColumnSidebar.setPercentWidth(20);
        ColumnConstraints mainColumnEmulator = new ColumnConstraints();
        mainColumnEmulator.setPercentWidth(80);

        ColumnConstraints sidebarColumnDevices = new ColumnConstraints();
        sidebarColumnDevices.setPercentWidth(33);
        ColumnConstraints sidebarColumnComponents = new ColumnConstraints();
        sidebarColumnComponents.setPercentWidth(33);
        ColumnConstraints sidebarColumnScripts = new ColumnConstraints();
        sidebarColumnScripts.setPercentWidth(34);

        RowConstraints mainRowSettings = new RowConstraints();
        mainRowSettings.setPercentHeight(10);
        RowConstraints mainRowContents = new RowConstraints();
        mainRowContents.setPercentHeight(70);
        RowConstraints mainRowTerminal = new RowConstraints();
        mainRowTerminal.setPercentHeight(20);

        RowConstraints sidebarRowTabs = new RowConstraints();
        sidebarRowTabs.setPercentHeight(5);
        RowConstraints sidebarRowContent = new RowConstraints();
        sidebarRowContent.setPercentHeight(95);

        gpMain.getColumnConstraints().addAll(mainColumnSidebar, mainColumnEmulator);
        gpMain.getRowConstraints().addAll(mainRowSettings, mainRowContents, mainRowTerminal);

        gpSidebar.getColumnConstraints().addAll(sidebarColumnDevices, sidebarColumnComponents, sidebarColumnScripts);
        gpSidebar.getRowConstraints().addAll(sidebarRowTabs, sidebarRowContent);

        VBox.setVgrow(gpMain, Priority.ALWAYS );

        Label lblTitle = new Label("Hello World");
        
        gpMain.add(lblTitle,1,1);

        gpMain.add(gpSidebar,0,1);

        vBox.getChildren().addAll(gpMain,sep);

        Scene mainScene = new Scene(vBox);
        stage.setScene(mainScene);
        stage.setHeight(800);
        stage.setWidth(1200);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
