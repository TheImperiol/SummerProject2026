package ApplicationFX;

import ProtoMessages.*;
import ProtoMessages.CardWrapperProto.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.robot.Robot;

public final class ProtoHandler {
    private ProtoHandler(){

    }

    public static void WrapperHandler(CardWrapper wrapper){
        switch(wrapper.getCardCase()){
            case DEVICECARD:
                DeviceHandler(wrapper.getDeviceCard());
                break;
            case SCRIPTCARD:
                ScriptHandler(wrapper.getScriptCard());
                break;
            case COMPONENTCARD:
                ComponentHandler(wrapper.getComponentCard());
                break;
        }
    }

    private static void DeviceHandler(DeviceCardOuterClass.DeviceCard card){
        System.out.println("Device from handler");
    }

    private static void ScriptHandler(ScriptCardOuterClass.ScriptCard card){
        System.out.println("Script from handler");
        ScriptCard script = new ScriptCard(0, 0,"test path");
        Robot robot = new Robot();
        Point2D mousePos = robot.getMousePosition();
        Point2D localPos = MainSummerApp.app.frontend.emulatorWindow.screenToLocal(mousePos);
        script.relocate(localPos.getX(),localPos.getY());
        MainSummerApp.app.frontend.emulatorWindow.getChildren().add(script);
    }

    private static void ComponentHandler(ComponentCardOuterClass.ComponentCard card){
        System.out.println("Component from handler");
        ComponentEmulatorCard comp = new ComponentEmulatorCard(0, 0);
        Robot robot = new Robot();
        Point2D mousePos = robot.getMousePosition();
        Point2D localPos = MainSummerApp.app.frontend.emulatorWindow.screenToLocal(mousePos);
        comp.relocate(localPos.getX(),localPos.getY());
        MainSummerApp.app.frontend.emulatorWindow.getChildren().add(comp);
    }
}
