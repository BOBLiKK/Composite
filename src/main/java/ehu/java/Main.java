package ehu.java;

import ehu.java.composite.TextComponent;
import ehu.java.parser.Parser;
import ehu.java.parser.impl.ParserBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        String originalText2 = """
                It has survived - not only (five) centuries, but also the leap into electronic typesetting,
                remaining -3-5 essentially 6*9/(3+4) unchanged.
                It was popularised in the 5*(1+2*(3/(4-(1-56-47)*73)+(-89+4/(42/7)))+1) with the release of Letraset.
                The point of using (-71+(2+3/(3*(2+1/2+2)-2)/10+2))/7 Ipsum.
                Bye.
                """;

        Parser parser = ParserBuilder.buildParserChain();
        TextComponent component = parser.parse(originalText2);
        logger.info(component.toString());
    }
}