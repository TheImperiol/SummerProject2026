package ApplicationFX;

import javafx.event.EventHandler;

import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class DeviceCard extends GeneralCard {
    private DeviceCard GetSelf(){
        return this;
    }
    public DeviceCard(String title, String description){
        super();
        cardTitle = title;
        cardDescription = description;
        Label titleLabel = new Label(cardTitle);
        Label descLabel = new Label(cardDescription);
        this.getChildren().addAll(titleLabel,descLabel);
        
        this.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override public void handle(MouseEvent event){
                System.out.println("dev clicked");
                if(event.getButton().equals(MouseButton.PRIMARY)){
                    if(event.getClickCount() == 2){
                        System.out.println("double clicked");
                        MainSummerApp.app.frontend.emulatorWindow.SetDevice(GetSelf());
                    }
                }
            }
        });

        }
    }

