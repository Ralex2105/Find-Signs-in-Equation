import lostSigns.model.PolishNotation;
import org.junit.Test;
import org.junit.jupiter.api.Tag;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class PolishNotationTest {

    @Test
    public void isDelimetersTest() {
        PolishNotation polishNotation = new PolishNotation();

        String token = "(";
        boolean i  = polishNotation.isDelimiter(token);
        assertTrue(i);

        token = ")";
        i  = polishNotation.isDelimiter(token);
        assertTrue(i);

        token = "()";
        i  = polishNotation.isDelimiter(token);
        assertFalse(i);

        token = "&";
        i  = polishNotation.isDelimiter(token);
        assertFalse(i);
    }

    @Test
    public void isOperatorTest() {
        PolishNotation polishNotation = new PolishNotation();

        String token = "+";
        boolean i  = polishNotation.isOperator(token);
        assertTrue(i);

        token = "-";
        i  = polishNotation.isDelimiter(token);
        assertTrue(i);

        token = "*";
        i  = polishNotation.isDelimiter(token);
        assertTrue(i);

        token = "()))";
        i  = polishNotation.isDelimiter(token);
        assertFalse(i);
    }

    @Test
    public void priorityTest() {
        PolishNotation polishNotation = new PolishNotation();

        int test = polishNotation.priority("(");
        assertEquals(1, test);

        test = polishNotation.priority("+");
        assertEquals(2, test);

        test = polishNotation.priority("-");
        assertEquals(2, test);

        test = polishNotation.priority("*");
        assertEquals(3, test);

        test = polishNotation.priority(")");
        assertEquals(4, test);
    }

    @Test
    @Tag("General")
    public void parseTest() {
        PolishNotation polishNotation = new PolishNotation();
        List<String> result = polishNotation.parse("2+2+2");
        List<String> needInf = new ArrayList<>();
        needInf.add("2");
        needInf.add("2");
        needInf.add("+");
        needInf.add("2");
        needInf.add("+");
        assertEquals(needInf, result);

        result = polishNotation.parse("(2+2)*2");
        needInf = new ArrayList<>();
        needInf.add("2");
        needInf.add("2");
        needInf.add("+");
        needInf.add("2");
        needInf.add("*");
        assertEquals(needInf, result);

        result = polishNotation.parse("(2+2)*2+1");
        needInf = new ArrayList<>();
        needInf.add("2");
        needInf.add("2");
        needInf.add("+");
        needInf.add("2");
        needInf.add("*");
        needInf.add("1");
        needInf.add("+");
        assertEquals(needInf, result);
    }
}
