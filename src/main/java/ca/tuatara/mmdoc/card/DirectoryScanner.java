package ca.tuatara.mmdoc.card;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ca.tuatara.mmdoc.card.data.Card;
import ca.tuatara.mmdoc.card.data.Cards;
import ca.tuatara.mmdoc.card.parser.CardParser;

public class DirectoryScanner {
    private static final Logger LOG = LoggerFactory.getLogger(DirectoryScanner.class);

    private static final String CARDS_FILENAME_FORMAT = "cards_{0}.xml";
    private static final String CARDS_SET_FILE = "cards_set.txt";

    private File gameDataDirectory;

    public DirectoryScanner(String gameDataDirectoryLocation) {
        gameDataDirectory = new File(gameDataDirectoryLocation);
    }

    public static void main(String[] args) {
        DirectoryScanner directoryScanner = new DirectoryScanner(args[0]);
        List<Card> loadCards = directoryScanner.loadCards();
        for (Card card : loadCards) {
            LOG.debug("{}", card);
        }
    }

    public List<Card> loadCards() {
        List<Card> cards = new ArrayList<Card>();

        File cardSets = new File(gameDataDirectory, CARDS_SET_FILE);
        if (!cardSets.exists()) {
            LOG.error("Unable to find cards_set.txt. Please make sure you specific the right directory for GameData.");
        }

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(cardSets));
            while (true) {
                String cardSetFiles = bufferedReader.readLine();
                if (cardSetFiles == null) {
                    break;
                }
                List<Card> cardSet = readCardSet(gameDataDirectory, cardSetFiles);
                for (Card card : cardSet) {
                    card.setSet(cardSetFiles.split(",")[0]);
                }
                cards.addAll(cardSet);
            }
            bufferedReader.close();
        } catch (IOException e) {
            LOG.error("Unable to read cards_set.txt file", e);
        }

        return cards;
    }

    private List<Card> readCardSet(File gameDataDirectory, String cardSetFiles) {
        LOG.debug("Loading card set [{}]", cardSetFiles);
        List<Card> cardSet = new ArrayList<Card>();
        CardParser cardParser = new CardParser();
        for (String cardSetFile : cardSetFiles.split(",")) {
            try {
                Cards cardGroup = cardParser.parse(new FileInputStream(new File(gameDataDirectory, MessageFormat.format(CARDS_FILENAME_FORMAT, cardSetFile))));
                cardSet.addAll(cardGroup.getCards());
            } catch (IOException e) {
                LOG.error("Unable to parse card set for {}", cardSetFile, e);
            }
        }

        return cardSet;
    }
}
