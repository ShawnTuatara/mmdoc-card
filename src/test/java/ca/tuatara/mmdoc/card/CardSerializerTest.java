package ca.tuatara.mmdoc.card;

import org.junit.Test;

import ca.tuatara.jackson.CapitalizeNamingStrategy;
import ca.tuatara.mmdoc.card.data.Cards;
import ca.tuatara.mmdoc.card.data.Creature;
import ca.tuatara.mmdoc.card.data.Hero;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class CardSerializerTest {
    @Test
    public void serializeCardSet() throws Exception {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.setPropertyNamingStrategy(new CapitalizeNamingStrategy());

        Cards cards = new Cards();
        cards.addCard(new Hero());
        cards.addCard(new Creature());
        String string = xmlMapper.writeValueAsString(cards);
        System.out.println(string);

        Cards returnedCards = xmlMapper.readValue(string, Cards.class);
        System.out.println(returnedCards.toString());
    }
}
