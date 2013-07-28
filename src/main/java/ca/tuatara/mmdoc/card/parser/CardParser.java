package ca.tuatara.mmdoc.card.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ca.tuatara.jackson.CapitalizeBooleanDeserializer;
import ca.tuatara.jackson.CapitalizeNamingStrategy;
import ca.tuatara.mmdoc.card.data.Cards;

import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class CardParser {
    private static final Logger LOG = LoggerFactory.getLogger(CardParser.class);

    private XmlMapper xmlMapper;

    public CardParser() {
        JacksonXmlModule module = new JacksonXmlModule();
        module.setDefaultUseWrapper(false);
        module.addDeserializer(Boolean.TYPE, new CapitalizeBooleanDeserializer());
        xmlMapper = new XmlMapper(module);
        xmlMapper.setPropertyNamingStrategy(new CapitalizeNamingStrategy());
    }

    public static void main(String[] args) {
        File cardSetFile = new File(args[0]);

        CardParser cardSetParser = new CardParser();
        try {
            Cards cards = cardSetParser.parse(new FileInputStream(cardSetFile));
            LOG.trace("{}", cards);
        } catch (IOException e) {
            LOG.error("Unable to parse card set file", e);
        }
    }

    public Cards parse(InputStream inputStream) throws IOException {
        return xmlMapper.readValue(inputStream, Cards.class);
    }
}
