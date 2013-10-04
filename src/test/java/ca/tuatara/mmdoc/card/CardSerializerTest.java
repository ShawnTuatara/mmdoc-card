package ca.tuatara.mmdoc.card;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ca.tuatara.jackson.CapitalizeBooleanDeserializer;
import ca.tuatara.jackson.CapitalizeNamingStrategy;
import ca.tuatara.mmdoc.card.data.Cards;
import ca.tuatara.mmdoc.card.data.Faction;
import ca.tuatara.mmdoc.card.data.Hero;
import ca.tuatara.mmdoc.card.data.HeroSchool;
import ca.tuatara.mmdoc.card.data.MagicSchool;
import ca.tuatara.mmdoc.card.data.RarityType;

import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class CardSerializerTest {
    private static final Logger LOG = LoggerFactory.getLogger(CardSerializerTest.class);

    @Test
    public void serializeCardSet() throws Exception {
        JacksonXmlModule module = new JacksonXmlModule();
        module.setDefaultUseWrapper(false);
        module.addDeserializer(Boolean.TYPE, new CapitalizeBooleanDeserializer());
        XmlMapper xmlMapper = new XmlMapper(module);
        xmlMapper.setPropertyNamingStrategy(new CapitalizeNamingStrategy());

        // <Card Rarity="Heroic" Type="Hero" Name="Her_Hav_004" DisplayName="Siegfried, Champion of Faith" Faction="Haven" MightLevel="2" MagicLevel="0" DestinyLevel="1" HP="20" ID="1">
        // <School Name="Light" />

        Cards cards = new Cards();

        Hero hero = new Hero();
        hero.setRarity(RarityType.Heroic);
        hero.setDisplayName("Siegfried, Champion of Faith");
        hero.setFaction(Faction.Haven);
        hero.setMightLevel(2);
        hero.setMagicLevel(0);
        hero.setDestinyLevel(1);
        hero.setHp(20);
        Set<HeroSchool> magicSchools = new HashSet<HeroSchool>();
        HeroSchool heroSchool = new HeroSchool();
        heroSchool.setName(MagicSchool.Air);
        magicSchools.add(heroSchool);
        heroSchool = new HeroSchool();
        heroSchool.setName(MagicSchool.Fire);
        magicSchools.add(heroSchool);
        hero.setSchools(magicSchools);
        cards.addCard(hero);

        String string = xmlMapper.writeValueAsString(cards);
        LOG.debug("{}", string);

        Cards returnedCards = xmlMapper.readValue(string, Cards.class);
        LOG.debug("{}", returnedCards);
    }
}
