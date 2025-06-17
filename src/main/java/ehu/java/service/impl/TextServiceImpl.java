package ehu.java.service.impl;

import ehu.java.composite.TextComponent;
import ehu.java.composite.impl.ComponentType;
import ehu.java.service.TextService;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TextServiceImpl implements TextService {

    //todo names

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
    public List<TextComponent> findSentencesWithLongestWord(TextComponent text) {
        int maxLength = text.getChild().stream()
                .flatMap(p -> p.getChild().stream()) // sentences
                .flatMap(s -> s.getChild().stream()) // lexemes
                .flatMap(l -> l.getChild().stream()) // words/symbols
                .filter(c -> c.getType() == ComponentType.WORD)
                .mapToInt(w -> w.toString().length())
                .max().orElse(0);

        return text.getChild().stream()
                .flatMap(p -> p.getChild().stream()) // sentences
                .filter(s -> s.toString().split("\\s+").length > 0 &&
                        Arrays.stream(s.toString().split("\\s+"))
                                .anyMatch(w -> w.length() == maxLength))
                .collect(Collectors.toList());
    }

    @Override
    public TextComponent removeSentencesWithWordCountLessThan(TextComponent text, int threshold) {
        for (TextComponent paragraph : text.getChild()) {
            List<TextComponent> updatedSentences = paragraph.getChild().stream()
                    .filter(sentence -> {
                        long wordCount = Arrays.stream(sentence.toString().split("\\s+"))
                                .filter(w -> w.matches("[\\p{L}\\p{Digit}]+"))
                                .count();
                        return wordCount >= threshold;
                    }).collect(Collectors.toList());
            paragraph.getChild().clear();
            paragraph.getChild().addAll(updatedSentences);
        }
        return text;
    }

    @Override
    public Map<String, Integer> countIdenticalWordsIgnoreCase(TextComponent text) {
        Map<String, Integer> wordCount = new HashMap<>();
        Pattern wordPattern = Pattern.compile("[\\p{L}\\p{Digit}]+");

        text.getChild().stream()
                .flatMap(p -> p.getChild().stream()) // sentences
                .flatMap(s -> Arrays.stream(s.toString().split("\\s+")))
                .map(word -> word.replaceAll("[^\\p{L}\\p{Digit}]", "").toLowerCase())
                .filter(word -> !word.isBlank())
                .forEach(word -> wordCount.put(word, wordCount.getOrDefault(word, 0) + 1));

        return wordCount.entrySet().stream()
                .filter(e -> e.getValue() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public Map<String, Integer> countVowelsAndConsonants(TextComponent sentence) {
        int vowels = 0;
        int consonants = 0;

        for (char c : sentence.toString().toUpperCase().toCharArray()) {
            if (Character.isLetter(c)) {
                if (VOWELS.contains(c)) {
                    vowels++;
                } else {
                    consonants++;
                }
            }
        }

        Map<String, Integer> result = new HashMap<>();
        result.put("vowels", vowels);
        result.put("consonants", consonants);
        return result;
    }
}
