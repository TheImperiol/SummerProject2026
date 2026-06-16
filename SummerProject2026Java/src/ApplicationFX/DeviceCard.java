package ApplicationFX;

import javafx.scene.control.*;

public class DeviceCard extends GeneralCard {
    public DeviceCard(String title, String description){
        cardTitle = title;
        cardDescription = description;
        Label titleLabel = new Label(cardTitle);
        this.getChildren().add(titleLabel);
    }
}
