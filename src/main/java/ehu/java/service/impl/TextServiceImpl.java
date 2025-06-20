package ehu.java.service.impl;

import ehu.java.composite.TextComponent;
import ehu.java.composite.impl.ComponentType;
import ehu.java.composite.impl.TextComposite;
import ehu.java.service.TextService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TextServiceImpl implements TextService {

    private static final Logger logger = LogManager.getLogger(TextServiceImpl.class);

    private static final Set<Character> VOWELS = Set.of(
            'A', 'E', 'I', 'O', 'U', 'Y',
            'А', 'Е', 'Ё', 'И', 'О', 'У', 'Ы', 'Э', 'Ю', 'Я'
    );

    @Override
    public List<TextComponent> sortParagraphsBySentenceCount(TextComponent text) {
        return text.getChild().stream()
                .sorted(Comparator.comparingInt(paragraph ->
                        (int) paragraph.getChild().stream()
                                .filter(c -> c.getType() == ComponentType.SENTENCE)
                                .count()))
                .collect(Collectors.toList());
    }

    @Override
    public String findSentencesWithLongestWord(TextComponent text) {
        if (text.getType() != ComponentType.TEXT) {
            throw new IllegalArgumentException("Component is not a TEXT node");
        }

        List<TextComponent> result = new ArrayList<>();
        int maxLength = 0;

        for (TextComponent paragraph : text.getChild()) {
            for (TextComponent sentence : paragraph.getChild()) {
                int sentenceMax = sentence.getChild().stream()
                        .mapToInt(lexeme -> lexeme.toString().length())
                        .max()
                        .orElse(0);
                if (sentenceMax > maxLength) {
                    maxLength = sentenceMax;
                    result.clear();
                    result.add(sentence);
                } else if (sentenceMax == maxLength) {
                    result.add(sentence);
                }
            }
        }
        logger.info("Sentences with longest word (length = " + maxLength + "):");

        return result.toString();
    }

    @Override
    public TextComponent removeSentencesWithWordCountLessThan(TextComponent text, int threshold) {
        if (text.getType() != ComponentType.TEXT) {
            throw new IllegalArgumentException("Component is not a TEXT node");
        }

        TextComposite resultText = new TextComposite(ComponentType.TEXT);

        for (TextComponent paragraph : text.getChild()) {
            TextComposite resultParagraph = new TextComposite(ComponentType.PARAGRAPH);

            for (TextComponent sentence : paragraph.getChild()) {
                int wordCount = sentence.getChild().size();
                if (wordCount >= threshold) {
                    resultParagraph.add(sentence);
                }
            }
            if (!resultParagraph.getChild().isEmpty()) {
                resultText.add(resultParagraph);
            }
        }

        return resultText;
    }

    @Override
    public Map<String, Integer> countIdenticalWordsIgnoreCase(TextComponent text) {
        Map<String, Integer> wordCount = new HashMap<>();
        Pattern wordPattern = Pattern.compile("[\\p{L}\\p{Digit}]+");

        text.getChild().stream()
                .flatMap(p -> p.getChild().stream())
                .flatMap(s -> Arrays.stream(s.toString().split("\\s+")))
                .map(word -> word.replaceAll("[^\\p{L}\\p{Digit}]", "").toLowerCase())
                .filter(word -> !word.isBlank())
                .forEach(word -> wordCount.put(word, wordCount.getOrDefault(word, 0) + 1));

        return wordCount.entrySet().stream()
                .filter(e -> e.getValue() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public Map<String, Integer> countVowelsAndConsonants(TextComponent text) {
        if (text.getType() != ComponentType.TEXT) {
            throw new IllegalArgumentException("Component is not a TEXT node");
        }

        int vowels = 0;
        int consonants = 0;

        for (TextComponent paragraph : text.getChild()) {
            for (TextComponent sentence : paragraph.getChild()) {
                for (TextComponent lexeme : sentence.getChild()) {
                    for (TextComponent symbol : lexeme.getChild()) {
                        char ch = symbol.toString().charAt(0);
                        if (Character.isLetter(ch)) {
                            char upperCh = Character.toUpperCase(ch);
                            if (VOWELS.contains(upperCh)) {
                                vowels++;
                            } else {
                                consonants++;
                            }
                        }
                    }
                }
            }
        }

        Map<String, Integer> result = new HashMap<>();
        result.put("vowels", vowels);
        result.put("consonants", consonants);
        return result;
    }
}
