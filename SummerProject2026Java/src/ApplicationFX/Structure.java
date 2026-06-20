package ApplicationFX;

import java.io.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Base64;

import javafx.event.*;
import javafx.scene.input.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class Structure extends GridPane {
    GridPane gpAppTopBar;
    GridPane gpMain;
    GridPane gpSidebar;
    GridPane gpSidebarContent;
    GridPane gpEmulator;
    public Structure(){
        gpMain = new GridPane();
        gpSidebar = new GridPane();
        gpSidebarContent = new GridPane();
        gpEmulator = new GridPane();
        gpAppTopBar = new GridPane();

        gpEmulator.setOnDragOver(new EventHandler<DragEvent>() {
            @Override public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                if (db.hasString()) {
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }
            event.consume();
            }
        });

        gpEmulator.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                Clipboard clipboard = Clipboard.getSystemClipboard();
                boolean success = false;
                if( clipboard.hasString()){
                    System.out.println("Dropped: "+ clipboard.getString());
                    success = true;
                    final byte[] bytes = Base64.getDecoder().decode(clipboard.getString());
                    try(ByteArrayInputStream bytStream = new ByteArrayInputStream(bytes);
                    ObjectInputStream objStream = new ObjectInputStream(bytStream)){
                        DraggableCard deserializDraggableCard = (DraggableCard) objStream.readObject();
                        gpEmulator.add(deserializDraggableCard, 0,0);
                    } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                }
                event.setDropCompleted(success);
                event.consume();
            }
        });

        

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
        sidebarRowFilling.setPercentHeight(100);

        gpAppTopBar.getRowConstraints().addAll(mainRowSettings, sidebarRowContent);
        gpAppTopBar.getColumnConstraints().add(sidebarColumnFilling);
        gpMain.getColumnConstraints().addAll(mainColumnSidebar, mainColumnEmulator);
        gpMain.getRowConstraints().addAll(sidebarRowFilling);
        gpSidebarContent.getColumnConstraints().addAll(sidebarColumnDevices, sidebarColumnComponents, sidebarColumnScripts);
        gpSidebarContent.getRowConstraints().add(sidebarRowFilling);
        gpEmulator.getColumnConstraints().add(sidebarColumnFilling);
        gpEmulator.getRowConstraints().addAll(mainRowContents,mainRowTerminal);
        gpSidebar.getColumnConstraints().add(sidebarColumnFilling);
        gpSidebar.getRowConstraints().addAll(mainRowSettings);
        
        gpSidebar.add(gpSidebarContent,0,0);
        gpMain.add(gpSidebar,0,0);
        gpMain.add(gpEmulator, 1, 0);
        gpAppTopBar.add(gpMain,0,1);
    }
}
