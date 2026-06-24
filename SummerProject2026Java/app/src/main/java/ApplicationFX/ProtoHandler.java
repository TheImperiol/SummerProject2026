package ApplicationFX;

import ProtoMessages.*;
import ProtoMessages.CardWrapperProto.*;

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
    }

    private static void ComponentHandler(ComponentCardOuterClass.ComponentCard card){
        System.out.println("Component from handler");
    }
}
