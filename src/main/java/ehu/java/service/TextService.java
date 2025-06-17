package ehu.java.service;

import ehu.java.composite.TextComponent;
import java.util.List;
import java.util.Map;

public interface TextService {
    List<TextComponent> sortParagraphsBySentenceCount(TextComponent text);
    List<TextComponent> findSentencesWithLongestWord(TextComponent text);
    TextComponent removeSentencesWithWordCountLessThan(TextComponent text, int threshold);
    Map<String, Integer> countIdenticalWordsIgnoreCase(TextComponent text);
    Map<String, Integer> countVowelsAndConsonants(TextComponent sentence);
}