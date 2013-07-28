package ca.tuatara.mmdoc.card;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import ca.tuatara.mmdoc.card.data.Card;
import ca.tuatara.mmdoc.card.data.CardType;
import ca.tuatara.mmdoc.card.data.RarityType;

public class CardManager {
    @Getter
    private List<Card> cards = new ArrayList<Card>();

    private Map<Integer, Card> cardsById = new HashMap<Integer, Card>();

    private Map<CardType, List<Card>> cardsByType = new HashMap<CardType, List<Card>>();

    private Map<RarityType, List<Card>> cardsByRarity = new HashMap<RarityType, List<Card>>();

    private Map<String, List<Card>> cardsBySet = new HashMap<String, List<Card>>();

    public CardManager() {
    }

    public void addCards(Collection<Card> cards) {
        for (Card card : cards) {
            this.cards.add(card);
            cardsById.put(card.getId(), card);

            List<Card> cardTypeList = cardsByType.get(card.getType());
            if (cardTypeList == null) {
                cardTypeList = new ArrayList<Card>();
                cardsByType.put(card.getType(), cardTypeList);
            }
            cardTypeList.add(card);

            List<Card> rarityTypeList = cardsByRarity.get(card.getRarity());
            if (rarityTypeList == null) {
                rarityTypeList = new ArrayList<Card>();
                cardsByRarity.put(card.getRarity(), rarityTypeList);
            }
            rarityTypeList.add(card);

            List<Card> setList = cardsBySet.get(card.getSet());
            if (setList == null) {
                setList = new ArrayList<Card>();
                cardsBySet.put(card.getSet(), setList);
            }
            setList.add(card);
        }
    }

    public Card getCardById(int id) {
        return cardsById.get(id);
    }
}
