package ApplicationFX;

import javafx.scene.layout.*;

public abstract class GeneralCard extends VBox{
    protected String cardTitle;
    protected String cardDescription;
    String cssLayout = "-fx-border-color: red;\n" +
                   "-fx-border-insets: 5;\n" +
                   "-fx-border-width: 3;\n" +
                   "-fx-border-style: dashed;\n";
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
        this.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        this.setStyle(cssLayout);
    }

    
}
