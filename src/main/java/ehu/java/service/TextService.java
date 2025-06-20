package ehu.java.service;

import ehu.java.composite.TextComponent;
import java.util.List;
import java.util.Map;

public interface TextService {
    List<TextComponent> sortParagraphsBySentenceCount(TextComponent text);
    Map<String, Integer> countIdenticalWordsIgnoreCase(TextComponent text);
    Map<String, Integer> countVowelsAndConsonants(TextComponent sentence);
    String findSentencesWithLongestWord(TextComponent text);
    TextComponent removeSentencesWithWordCountLessThan(TextComponent text, int threshold);
}