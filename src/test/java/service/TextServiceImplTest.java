package service;

import ehu.java.composite.TextComponent;
import ehu.java.composite.impl.TextComposite;
import ehu.java.parser.Parser;
import ehu.java.parser.impl.ParserBuilder;
import ehu.java.service.TextService;
import ehu.java.service.impl.TextServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class TextServiceImplTest {
    private TextService textService;
    private Parser parser;
    private String sampleText;
    private TextComponent parsedText;



    //todo names

    @BeforeEach
    void setUp() {
        textService = new TextServiceImpl();
        parser = new ParserBuilder().buildParserChain();
        sampleText = """
            It is a truth universally acknowledged, that a single man in possession of a good fortune, must be in want of a wife.
            A quick brown fox jumps over the lazy dog.
            Bye.
            """;
        parsedText = parser.parse(sampleText);
    }

    @Test
    void sortParagraphsBySentenceCount_shouldSortCorrectly() {
        List<TextComponent> sortedParagraphs = textService.sortParagraphsBySentenceCount(parsedText);
        assertEquals(3, sortedParagraphs.size());
        assertTrue(sortedParagraphs.get(0).toString().contains("Bye."));
        assertTrue(sortedParagraphs.get(2).toString().contains("It is a truth"));
    }

    @Test
    void findSentencesWithLongestWord_shouldFindCorrectSentences() {
        List<TextComponent> result = textService.findSentencesWithLongestWord(parsedText);
        assertFalse(result.isEmpty());
        assertTrue(result.get(0).toString().contains("universally")); // longest word
    }

    @Test
    void removeSentencesWithWordCountLessThan_shouldRemoveShortSentences() {
        int minWords = 4;
        TextComponent result = textService.removeSentencesWithWordCountLessThan(parsedText, minWords);
        String restored = result.toString();
        assertFalse(restored.contains("Bye."));
        assertTrue(restored.contains("A quick brown fox"));
    }

        @Test
        void countIdenticalWordsIgnoreCase_shouldCountCorrectly() {
            Map<String, Integer> wordCount = textService.countIdenticalWordsIgnoreCase(parsedText);
            assertTrue(wordCount.containsKey("a"));
            assertTrue(wordCount.get("a") > 1);
            assertFalse(wordCount.containsKey("Bye"));
        }

    @Test
    void countVowelsAndConsonantsInSentence_shouldReturnCorrectCounts() {
        TextComponent sentence = ((TextComposite) parsedText.getChild().get(0))
                .getChild().get(0); // First sentence of first paragraph
        Map<String, Integer> counts = textService.countVowelsAndConsonants(sentence);
        assertEquals(22, counts.get("vowels")); // exact numbers may differ
        assertEquals(43, counts.get("consonants"));
    }
}
