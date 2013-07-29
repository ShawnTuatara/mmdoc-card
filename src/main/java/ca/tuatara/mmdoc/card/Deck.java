package ca.tuatara.mmdoc.card;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import ca.tuatara.mmdoc.card.data.Card;

@Data
public class Deck {
    private Card hero;

    private List<Card> cards = new ArrayList<Card>();

    public void addCard(Card card) {
        cards.add(card);
    }
}
