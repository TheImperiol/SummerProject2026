package ApplicationFX;


import javafx.scene.layout.*;
import ProtoMessages.CardWrapperProto.*;

public abstract class GeneralCard extends VBox{
    protected String cardTitle;
    protected String cardDescription;
    protected CardData cardData;
    String cssLayout = "-fx-border-insets: 5;\n" +
                   "-fx-border-width: 2;\n" +
                   "-fx-border-color: black";

    CardWrapper wrapper = CardWrapper.newBuilder().setScriptCard(ProtoMessages.ScriptCardOuterClass.ScriptCard.newBuilder().setName("test").build()).build();

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

    public CardData GetData(){
        return cardData;
    }

    public GeneralCard(){
        cardData = new CardData();
        this.setFillWidth(true);
        this.setPrefSize(50, 50);
        this.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        this.setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        this.setStyle(cssLayout);
    }

    
}
