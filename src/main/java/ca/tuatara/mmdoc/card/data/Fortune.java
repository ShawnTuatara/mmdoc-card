package ca.tuatara.mmdoc.card.data;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Fortune extends Card {
    private Faction faction;

    private int cost;

    private int mightLevel;

    private int magicLevel;

    private int destinyLevel;

    @Override
    public CardType getType() {
        return CardType.Fortune;
    }
}
