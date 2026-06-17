package ApplicationFX;

//import javafx.scene.control.*;
import javafx.scene.layout.*;

public abstract class GeneralCard extends VBox{
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

    public GeneralCard(){
     
    }

    
}
