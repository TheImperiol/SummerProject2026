package ApplicationFX;

import ProtoMessages.CardWrapperProto.*;

public final class ProtoHandler {
    private ProtoHandler(){

    }

    public static void WrapperHandler(CardWrapper wrapper){
        switch(wrapper.getCardCase()){
            case DEVICECARD:
                System.out.println("device");
                break;
            case SCRIPTCARD:
                System.out.println("script");
                break;
            case COMPONENTCARD:
                System.out.println("component");
                break;
        }
    }
}
