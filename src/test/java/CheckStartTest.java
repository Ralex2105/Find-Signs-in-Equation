
import lostSigns.controller.StartController;
import org.junit.Test;
import org.junit.jupiter.api.Tag;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CheckStartTest {
    
    @Test
    @Tag("General")
    public void checkTest() {
        int check = StartController.check("2 2 2 = 2");
        assertEquals(2, check);

        check = StartController.check("2 2222 2 = 2");
        assertEquals(2, check);

        check = StartController.check("2 (2 2) = 2");
        assertEquals(2, check);

        check = StartController.check("2 2 2 = 2");
        assertEquals(2, check);

        check = StartController.check("(((2 (2 2)))) = 2");
        assertEquals(2, check);

        check = StartController.check(")2 (2 2) = 2");
        assertEquals(1, check);

        check = StartController.check("2 2 = 2 = 2");
        assertEquals(1, check);

        check = StartController.check("2 2 2 = 2k");
        assertEquals(1, check);

        check = StartController.check("2ss 2 2 = 2");
        assertEquals(1, check);

        check = StartController.check("2 2 2() = 2");
        assertEquals(1, check);

        check = StartController.check("2 2 2()");
        assertEquals(1, check);

        check = StartController.check("2 2 2 =");
        assertEquals(1, check);

        check = StartController.check("");
        assertEquals(0, check);
    }

    @Test
    public void checkEmptyTest() {
        int check = StartController.check("");
        assertEquals(0, check);
    }

    @Test
    public void checkWithoutEquallyTest() {
        int check = StartController.check("2 2 2");
        assertEquals(1, check);
    }

    @Test
    public void checkParenthesesTest() {
        boolean check = StartController.checkParentheses("22 22 2");
        assertTrue(check);

        check = StartController.checkParentheses("((22 22) 2)");
        assertTrue(check);

        check = StartController.checkParentheses("((22 22) 2)");
        assertTrue(check);

        check = StartController.checkParentheses("(2(2 2)2) 2");
        assertTrue(check);

        check = StartController.checkParentheses("(2 (22 22)) 2");
        assertTrue(check);

        check = StartController.checkParentheses("2 (22 22)) 2");
        assertFalse(check);

        check = StartController.checkParentheses("(2) (22 22)( 2");
        assertFalse(check);

        check = StartController.checkParentheses("((2 (22 22)) 2");
        assertFalse(check);
    }

    @Test
    public void isIntegerTest() {
        boolean check = StartController.isInteger('2');
        assertTrue(check);

        check = StartController.isInteger('5');
        assertTrue(check);

        check = StartController.isInteger('0');
        assertTrue(check);

        check = StartController.isInteger('(');
        assertFalse(check);

        check = StartController.isInteger(')');
        assertFalse(check);

        check = StartController.isInteger('=');
        assertFalse(check);
    }

    @Test
    public void checkArgumentTest() {
        boolean check = StartController.checkArgument(" 2 2 2 2 2 2 2 2 2   ");
        assertTrue(check);

        check = StartController.checkArgument(" 2 2 2 2 + 2 2 2 2 2 ");
        assertFalse(check);

        check = StartController.checkArgument(" 2 2 2 2  { 2 2 2 2 2   ");
        assertFalse(check);

        check = StartController.checkArgument(" 2 2 2 2 2 1111111112 2 2 2 ");
        assertTrue(check);

        check = StartController.checkArgument(" 2w 2 2 2k 2 2e 2 2 2   ");
        assertFalse(check);
    }

    @Test
    public void checkAnswerTest() {
        boolean check = StartController.checkAnswer("  2  ");
        assertTrue(check);

        check = StartController.checkAnswer("1 1");
        assertFalse(check);

        check = StartController.checkAnswer("=");
        assertFalse(check);

        check = StartController.checkAnswer("(");
        assertFalse(check);
    }

    @Test
    public void deleteSpacesTest() {
        assertEquals("22 22 22",
                StartController.deleteSpaces("   22    22    22  "));
        assertEquals("(22 2) 2 22",
                StartController.deleteSpaces("  ( 22    2)2    22  "));
        assertEquals("(22 (22 22))",
                StartController.deleteSpaces(" (  22    (22    22)    )  "));
    }

    @Test
    public void addSymbolsTest() {
        List<String> list = new ArrayList<>();
        List<String> listTest = new ArrayList<>();

        String example = "222(";
        list.add("2");
        list.add("2");
        listTest.add("2");
        listTest.add("2");
        listTest.add("2");

        assertEquals(listTest, StartController.addSymbols(example, 2, 2, list));

        listTest.add("(");
        assertEquals(listTest, StartController.addSymbols(example, 3, 3, list));
    }

    @Test
    public void plusSymbols() {
        List<String> list = new ArrayList<>();
        List<String> listTest = new ArrayList<>();
        String example = "2222";

        StartController.addSymbols(example, 0, 0, list);
        listTest.add("2222");

        StartController.plusSymbols(example, 1, 0, list);
        StartController.plusSymbols(example, 2, 0, list);

        assertEquals(listTest, StartController.plusSymbols(example, 3, 0, list));
    }

    @Test
    public void toStringListTest() {
        List<String> list = new ArrayList<String>();

        list.add("2");
        list.add("(");
        list.add("2");
        list.add("2");
        list.add(")");
        list.add("2");

        assertEquals("2 (2 2) 2", StartController.toStringList(list));

        list.add("2");
        list.add("2");

        assertEquals("2 (2 2) 2 2 2", StartController.toStringList(list));
    }
}
