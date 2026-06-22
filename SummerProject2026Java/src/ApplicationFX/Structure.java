package ApplicationFX;

import java.io.*;
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
                        System.out.println(deserializDraggableCard.children.size());
                        GridPane.setFillHeight(deserializDraggableCard,false);
                        GridPane.setFillWidth(deserializDraggableCard,false);
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
        //mainColumnSidebar.setHgrow(Priority.ALWAYS);
        mainColumnSidebar.setPercentWidth(20);
        ColumnConstraints mainColumnEmulator = new ColumnConstraints();
        //mainColumnEmulator.setHgrow(Priority.ALWAYS);
        mainColumnEmulator.setPercentWidth(80);
        ColumnConstraints sidebarColumnDevices = new ColumnConstraints();
        //sidebarColumnDevices.setHgrow(Priority.ALWAYS);
        sidebarColumnDevices.setPercentWidth(33);
        ColumnConstraints sidebarColumnComponents = new ColumnConstraints();
        //sidebarColumnComponents.setHgrow(Priority.ALWAYS);
        sidebarColumnComponents.setPercentWidth(33);
        ColumnConstraints sidebarColumnScripts = new ColumnConstraints();
        //sidebarColumnScripts.setHgrow(Priority.ALWAYS);
        sidebarColumnScripts.setPercentWidth(34);
        ColumnConstraints sidebarColumnFilling = new ColumnConstraints();
        //sidebarColumnFilling.setHgrow(Priority.ALWAYS);
        sidebarColumnFilling.setPercentWidth(100);
        
        RowConstraints mainRowSettings = new RowConstraints();
        //mainRowSettings.setVgrow(Priority.ALWAYS);
        mainRowSettings.setPercentHeight(5);
        RowConstraints mainRowContents = new RowConstraints();
        //mainRowContents.setVgrow(Priority.ALWAYS);
        mainRowContents.setPercentHeight(80);
        RowConstraints mainRowTerminal = new RowConstraints();
        //mainRowTerminal.setVgrow(Priority.ALWAYS);
        mainRowTerminal.setPercentHeight(20);
        RowConstraints sidebarRowTabs = new RowConstraints();
        //sidebarRowTabs.setVgrow(Priority.ALWAYS);
        sidebarRowTabs.setPercentHeight(5);
        RowConstraints sidebarRowContent = new RowConstraints();
        //sidebarRowContent.setVgrow(Priority.ALWAYS);
        sidebarRowContent.setPercentHeight(95);
        RowConstraints sidebarRowFilling = new RowConstraints();
        //sidebarRowFilling.setVgrow(Priority.ALWAYS);
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
