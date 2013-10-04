package ca.tuatara.mmdoc.card.data;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "Type")
@JsonSubTypes({ @Type(value = Hero.class, name = "Hero"), @Type(value = Creature.class, name = "Unit"), @Type(value = Fortune.class, name = "Fortune"), @Type(value = Spell.class, name = "Spell"),
        @Type(value = Event.class, name = "Event") })
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_DEFAULT)
public abstract class Card {
    @JacksonXmlProperty(localName = "ID", isAttribute = true)
    protected int id;

    @JsonIgnore
    protected String set;

    @JacksonXmlProperty(isAttribute = true)
    protected String displayName;

    @JacksonXmlProperty(isAttribute = true)
    protected RarityType rarity;

    @JacksonXmlProperty(isAttribute = true)
    protected boolean unique = false;

    @JacksonXmlProperty(isAttribute = true)
    public abstract CardType getType();
}
