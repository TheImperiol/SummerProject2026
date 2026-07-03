package ApplicationFX;

import java.util.ArrayList;

public class EmulatorView {
    private ArrayList<DraggableCard> cards = new ArrayList<DraggableCard>();

    EmulatorView(ArrayList<DraggableCard> _cards){
        cards = _cards;
    }

    public void AddCard(DraggableCard _card){
        cards.add(_card);
    }

    public void RemoveCard(DraggableCard _card){
        cards.remove(_card);
    }
}
