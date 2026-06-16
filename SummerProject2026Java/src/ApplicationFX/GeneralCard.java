package ApplicationFX;

import javafx.scene.layout.HBox;

public abstract class GeneralCard extends HBox{
    protected String cardTitle;
    protected String cardDescription;

    public String GetCardTitle(){
        return cardTitle;
    }

    public void SetCardTitle(String title){
        cardTitle = title;
    }

     public String GetCardDescription(){
        return cardDescription;
    }

    public void SetCardDescription(String description){
        cardDescription = description;
    }
}
