package ca.tuatara.mmdoc.card.data;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonAutoDetect
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Cards {
    @JacksonXmlProperty(localName = "card")
    @JacksonXmlElementWrapper(useWrapping = false)
    @Getter
    private List<Card> cards = new ArrayList<Card>();

    public void addCard(Card card) {
        cards.add(card);
    }
}
