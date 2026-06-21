package ApplicationFX;

import javafx.scene.control.*;

public class DeviceCard extends GeneralCard {
    public DeviceCard(String title, String description){
        super();
        cardTitle = title;
        cardDescription = description;
        Label titleLabel = new Label(cardTitle);
        Label descLabel = new Label(cardDescription);
        this.getChildren().addAll(titleLabel,descLabel);
        
    }
}
