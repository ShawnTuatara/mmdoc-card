package ca.tuatara.mmdoc.card.data;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Event extends Card {
    private int cost;
    
    @Override
    public CardType getType() {
        return CardType.Event;
    }
}
