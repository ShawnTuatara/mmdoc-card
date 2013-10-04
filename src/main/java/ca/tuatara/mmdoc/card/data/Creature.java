package ca.tuatara.mmdoc.card.data;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Creature extends Card {
    private Faction faction;
    
    private int cost;

    private int mightLevel;

    private int magicLevel;

    private int destinyLevel;

    private String subType;

    @Override
    public CardType getType() {
        return CardType.Creature;
    }
}
