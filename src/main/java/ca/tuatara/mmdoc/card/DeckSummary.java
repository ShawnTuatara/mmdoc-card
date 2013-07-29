package ca.tuatara.mmdoc.card;

import lombok.Data;
import ca.tuatara.mmdoc.card.data.Card;

@Data
public class DeckSummary {
    private Card hero;

    private int eventCount;

    private int creatureCount;

    private int spellCount;

    private int fortuneCount;
}
