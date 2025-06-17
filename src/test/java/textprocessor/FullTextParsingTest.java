package textprocessor;

import ehu.java.composite.TextComponent;
import ehu.java.parser.Parser;
import ehu.java.parser.impl.ParserBuilder;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FullTextParsingTest {

    @Test
    void fullTextShouldBeParsedAndRestoredCorrectly() {
        String original = """
            It has survived - not only (five) centuries, but also the leap into electronic typesetting, 
       remaining essentially unchanged. It was popularised in the “Динамо” (Рига) with the 
       release of Letraset sheets.toString() containing Lorem Ipsum passages, and more recently 
       with desktop publishing software like Aldus PageMaker Faclon9 including versions of 
       Lorem Ipsum!
            It is a long a!=b established fact that a reader will be distracted by the readable 
      content of a page when looking at its layout. The point of using Ipsum is that it has a more-or-less normal distribution ob.toString(a?b:c), as opposed to using (Content here), content 
       here's, making it look like readable English?
            It is a established fact that a reader will be of a page when looking at its layout...
            Bye бандерлоги.
        """;


        String expected = """
        \tIt has survived - not only (five) centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the “Динамо” (Рига) with the release of Letraset sheets.toString() containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker Faclon9 including versions of Lorem Ipsum!
        \tIt is a long a!=b established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Ipsum is that it has a more-or-less normal distribution ob.toString(a?b:c), as opposed to using (Content here), content here's, making it look like readable English?
        \tIt is a established fact that a reader will be of a page when looking at its layout...
        \tBye бандерлоги.""";



        Parser parser = ParserBuilder.buildParserChain();
        TextComponent component = parser.parse(original);
        String restored = component.toString();

        assertEquals(expected, restored);
    }

    @Test
    void paragraphCountShouldBeCorrect() {
        String original = """
            It has survived - not only (five) centuries, but also the leap into electronic typesetting, 
       remaining essentially unchanged. It was popularised in the “Динамо” (Рига) with the 
       release of Letraset sheets.toString() containing Lorem Ipsum passages, and more recently 
       with desktop publishing software like Aldus PageMaker Faclon9 including versions of 
       Lorem Ipsum!
            It is a long a!=b established fact that a reader will be distracted by the readable 
       content of a page when looking at its layout. The point of using Ipsum is that it has a more-
       or-less normal distribution ob.toString(a?b:c), as opposed to using (Content here), content 
       here's, making it look like readable English?
            It is a established fact that a reader will be of a page when looking at its layout...
            Bye бандерлоги.
        """;

        Parser parser = ParserBuilder.buildParserChain();
        TextComponent component = parser.parse(original);

        assertEquals(4, component.getChild().size(), "Should be 4 paragraphs");
    }
}
