package service;

import ehu.java.composite.TextComponent;
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
        String originalText = """
            Hello world again. First paragraph. Still. First. Paragraph.
                
            Second Paragraph.
                
            This is the third paragraph. It contains more words and has multiple sentences and hello.
        """;

        Parser parser = ParserBuilder.buildParserChain();
        TextComponent parsedText = parser.parse(originalText);
        List<TextComponent> sorted = textService.sortParagraphsBySentenceCount(parsedText);

        List<String> expectedParagraphs = List.of(
                "Second Paragraph.",
                "This is the third paragraph. It contains more words and has multiple sentences and hello.",
                "Hello world again. First paragraph. Still. First. Paragraph."
        );

        for (int i = 0; i < expectedParagraphs.size(); i++) {
            String actual = sorted.get(i).toString().strip();
            String expected = expectedParagraphs.get(i).strip();
            assertEquals(expected, actual, "Paragraph on position  " + i + " doesn't match");
        }
    }



    @Test
    void findSentencesWithLongestWord_shouldFindCorrectSentences() {
        String result = textService.findSentencesWithLongestWord(parsedText);
        assertFalse(result.isEmpty());
        assertTrue(result.toString().contains("universally"));
    }

    @Test
    void removeSentencesWithWordCountLessThan_shouldRemoveShortSentences() {
        String originalText = """
        Hello world again. First paragraph. Still. First. Paragraph.
            
        Second Paragraph.
            
        This is the third paragraph. It contains more words and has multiple sentences and hello.
    """;

        String expectedText = """
        Hello world again.
        This is the third paragraph. It contains more words and has multiple sentences and hello.
    """;

        Parser parser = ParserBuilder.buildParserChain();
        int threshold = 3;
        TextComponent parsedText = parser.parse(originalText);
        TextComponent filteredText = textService.removeSentencesWithWordCountLessThan(parsedText, threshold);

        assertEquals(
                expectedText.strip().replaceAll("\\s+", " "),
                filteredText.toString().strip().replaceAll("\\s+", " ")
        );
    }

        @Test
        void countIdenticalWordsIgnoreCase_shouldCountCorrectly() {
            Map<String, Integer> wordCount = textService.countIdenticalWordsIgnoreCase(parsedText);
            assertTrue(wordCount.containsKey("a"));
            assertTrue(wordCount.get("a") > 1);
            assertFalse(wordCount.containsKey("Bye"));
        }

    @Test
    void countVowelsAndConsonants_shouldCountCorrectly() {
        String text = """
            Hello world!
            Привет мир.
        """;

        TextComponent parsed = parser.parse(text);
        Map<String, Integer> result = textService.countVowelsAndConsonants(parsed);

        int expectedVowels = 6;
        int expectedConsonants = 13;

        assertEquals(expectedVowels, result.get("vowels"), "Incorrect vowel count");
        assertEquals(expectedConsonants, result.get("consonants"), "Incorrect consonant count");
    }
}
