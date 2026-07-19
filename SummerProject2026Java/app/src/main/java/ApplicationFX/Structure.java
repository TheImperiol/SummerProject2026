package ApplicationFX;

import java.io.*;
import java.util.ArrayList;
import java.util.Base64;

import ProtoMessages.CardWrapperProto.CardWrapper;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.scene.input.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.Window;

public class Structure extends GridPane {
    GridPane gpAppTopBar;
    GridPane gpMain;
    GridPane gpSidebar;
    GridPane gpSidebarContent;
    GridPane gpEmulator;
    EmulatorWindow emulatorWindow;

    public VBox devicesPane;
    public VBox scriptsPane;
    public VBox componentsPane;

    public void AddDeviceToStack(DeviceCard card){
        devicesPane.getChildren().add(card);
    }
    
    public Structure(Stage mainStage){

        this.setMaxHeight(Double.MAX_VALUE);
        this.setMaxWidth(Double.MAX_VALUE);

        ColumnConstraints structureCol = new ColumnConstraints();
        structureCol.setHgrow(Priority.ALWAYS);
        structureCol.setFillWidth(true);

        RowConstraints structureRow = new RowConstraints();
        structureRow.setVgrow(Priority.ALWAYS);
        structureRow.setFillHeight(true);

        this.getColumnConstraints().add(structureCol);
        this.getRowConstraints().add(structureRow);

        this.setPrefWidth(1200);
        this.setPrefHeight(800);
        
        gpMain = new GridPane();
        gpSidebar = new GridPane();
        gpSidebarContent = new GridPane();
        gpEmulator = new GridPane();
        gpAppTopBar = new GridPane();

        gpAppTopBar.setMaxWidth(Double.MAX_VALUE);
        gpAppTopBar.setMaxHeight(Double.MAX_VALUE);
        GridPane.setHgrow(gpAppTopBar, Priority.ALWAYS);
        GridPane.setVgrow(gpAppTopBar, Priority.ALWAYS);

        gpMain.setMaxWidth(Double.MAX_VALUE);
        gpMain.setMaxHeight(Double.MAX_VALUE);
        GridPane.setHgrow(gpMain, Priority.ALWAYS);
        GridPane.setVgrow(gpMain, Priority.ALWAYS);

        gpSidebar.setMaxWidth(Double.MAX_VALUE);
        gpSidebar.setMaxHeight(Double.MAX_VALUE);
        GridPane.setHgrow(gpSidebar, Priority.ALWAYS);
        GridPane.setVgrow(gpSidebar, Priority.ALWAYS);

        
        gpSidebarContent.setMaxWidth(Double.MAX_VALUE);
        gpSidebarContent.setMaxHeight(Double.MAX_VALUE);
        GridPane.setHgrow(gpSidebarContent, Priority.ALWAYS);
        GridPane.setVgrow(gpSidebarContent, Priority.ALWAYS);

        gpEmulator.setMaxWidth(Double.MAX_VALUE);
        gpEmulator.setMaxHeight(Double.MAX_VALUE);
        GridPane.setHgrow(gpEmulator, Priority.ALWAYS);
        GridPane.setVgrow(gpEmulator, Priority.ALWAYS);

        gpAppTopBar.setGridLinesVisible(true);
        gpMain.setGridLinesVisible(true);
        gpEmulator.setGridLinesVisible(true);
        gpSidebar.setGridLinesVisible(true);
        gpSidebarContent.setGridLinesVisible(true);

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
        ColumnConstraints sidebarColumnFilling = new ColumnConstraints();
        sidebarColumnFilling.setPercentWidth(100);
        
        RowConstraints mainRowSettings = new RowConstraints();
        mainRowSettings.setPercentHeight(5);
        RowConstraints mainRowContents = new RowConstraints();
        mainRowContents.setPercentHeight(80);
        RowConstraints mainRowTerminal = new RowConstraints();
        mainRowTerminal.setPercentHeight(20);
        RowConstraints sidebarRowTabs = new RowConstraints();
        sidebarRowTabs.setPercentHeight(5);
        RowConstraints sidebarRowContent = new RowConstraints();
        sidebarRowContent.setPercentHeight(95);
        RowConstraints sidebarRowFilling = new RowConstraints();
        sidebarRowFilling.setVgrow(Priority.ALWAYS);
        sidebarRowFilling.setPercentHeight(100);

        mainRowSettings.setFillHeight(true);
        mainRowContents.setFillHeight(true);
        mainRowTerminal.setFillHeight(true);
        sidebarRowContent.setFillHeight(true);

        sidebarColumnFilling.setFillWidth(true);
        mainColumnSidebar.setFillWidth(true);
        mainColumnEmulator.setFillWidth(true);

        gpAppTopBar.getRowConstraints().addAll(mainRowSettings, sidebarRowContent);
        gpAppTopBar.getColumnConstraints().add(sidebarColumnFilling);
        
        gpMain.getColumnConstraints().addAll(mainColumnSidebar, mainColumnEmulator);
        gpMain.getRowConstraints().addAll(sidebarRowFilling);

        gpSidebarContent.getColumnConstraints().addAll(sidebarColumnDevices, sidebarColumnComponents, sidebarColumnScripts);
        gpSidebarContent.getRowConstraints().addAll(sidebarRowFilling );

        gpEmulator.getColumnConstraints().add(sidebarColumnFilling);
        gpEmulator.getRowConstraints().addAll(mainRowContents,mainRowTerminal);

        gpSidebar.getColumnConstraints().add(sidebarColumnFilling);
        gpSidebar.getRowConstraints().addAll(sidebarRowTabs,sidebarRowContent);
        gpSidebar.add(gpSidebarContent,0,0);

        gpMain.add(gpSidebar,0,0);
        gpMain.add(gpEmulator, 1, 0);

        gpAppTopBar.add(gpMain,0,1);

        this.getChildren().add(gpAppTopBar);

        emulatorWindow = new EmulatorWindow(mainStage.getHeight()* ((gpAppTopBar.getRowConstraints().get(1).getPercentHeight() / 100) * (gpEmulator.getRowConstraints().get(0).getPercentHeight() / 100)),mainStage.getWidth() * 0.8);
        gpEmulator.add(emulatorWindow,0,0);
        gpAppTopBar.setMaxWidth(Double.MAX_VALUE);
        gpAppTopBar.setMaxHeight(Double.MAX_VALUE);

        /*Button closure = new Button();
        closure.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent event){
                System.out.println("close");
                emulatorWindow.EmptyDevice();
            }
        });
        
        gpAppTopBar.getChildren().add(closure);
        */

        Button devices = new Button("Devices");
        Button scripts = new Button("Scripts");
        Button components = new Button("Components");

        devicesPane = new VBox();
        scriptsPane = new VBox();
        componentsPane = new VBox(); 


        devices.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event){
                devicesPane.setVisible(true);
                componentsPane.setVisible(false);
                scriptsPane.setVisible(false);
            }
        });

        scripts.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event){
                devicesPane.setVisible(false);
                componentsPane.setVisible(false);
                scriptsPane.setVisible(true);
            }
        });

        components.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event){
                devicesPane.setVisible(false);
                componentsPane.setVisible(true);
                scriptsPane.setVisible(false);
            }
        });

        devicesPane.setVisible(true);
        scriptsPane.setVisible(false);
        componentsPane.setVisible(false);

        //devicesPane.getChildren().add(new Label("test labelD"));
        scriptsPane.getChildren().add(new Label("test labelS"));
        componentsPane.getChildren().add(new Label("test labelC"));


        gpSidebarContent.add(devices,0,0);
        gpSidebarContent.add(scripts,1,0);
        gpSidebarContent.add(components,2,0);
        gpSidebar.add(devicesPane,0,1);
        gpSidebar.add(scriptsPane,0,1);
        gpSidebar.add(componentsPane,0,1);
        

        AddDeviceToStack(new DeviceCard("testing", "test 2"));

        Button uploadDeviceButton = new Button("Upload Device");

        uploadDeviceButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent event){
                ArrayList<ExtensionFilter> fil = new ArrayList<ExtensionFilter>();
                File chosen = FileSystem.OpenExplorer("Select Device", mainStage, fil);
                AddDeviceToStack(new DeviceCard("dev card","dev desc"));
            }
        });

        HBox top = new HBox();
        gpAppTopBar.add(top,0,0);
        top.getChildren().add(new FileMenu());
        top.getChildren().add(uploadDeviceButton);
    }
}
