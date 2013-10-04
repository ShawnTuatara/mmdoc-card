package ca.tuatara.mmdoc.card.data;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Hero extends Card {
    @JacksonXmlProperty(isAttribute = true)
    private Faction faction;

    @JacksonXmlProperty(isAttribute = true)
    private int mightLevel = -1;

    @JacksonXmlProperty(isAttribute = true)
    private int magicLevel = -1;

    @JacksonXmlProperty(isAttribute = true)
    private int destinyLevel = -1;

    @JacksonXmlProperty(localName = "HP", isAttribute = true)
    private int hp;

    @JacksonXmlProperty(localName = "school")
    @JacksonXmlElementWrapper(useWrapping = false)
    private Set<HeroSchool> schools = new HashSet<HeroSchool>();

    @Override
    public CardType getType() {
        return CardType.Hero;
    }
}
