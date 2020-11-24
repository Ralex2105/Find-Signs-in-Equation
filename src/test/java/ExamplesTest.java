import lostSigns.model.Examples;
import org.junit.Test;
import org.junit.jupiter.api.Tag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ExamplesTest {

    @Test
    @Tag("General")
    public void toSolve() {
        Examples examples = new Examples(2, "2 2 2");
        assertEquals("2-2+2=2", examples.toSolve());
        examples = new Examples(8, "(2 2) 2");
        assertEquals("(2*2)*2=8", examples.toSolve());
        examples = new Examples(10, "(3 2) 2");
        assertEquals("(3+2)*2=10", examples.toSolve());
        examples = new Examples(14, "((2 6) 2) 2");
        assertEquals("((2+6)*2)-2=14", examples.toSolve());
        examples = new Examples(2, "(((2 2)) 2)");
        assertEquals("(((2-2))+2)=2", examples.toSolve());
    }

    @Test
    public void toPutSigns() {
        String exampleFirst = "2 2 2";
        String exampleSecond = "((2 2))";

        List<String> expectedForFirst = new ArrayList<>();
        expectedForFirst.add("2*2*2");
        expectedForFirst.add("2+2*2");
        expectedForFirst.add("2-2*2");
        expectedForFirst.add("2*2+2");
        expectedForFirst.add("2+2+2");
        expectedForFirst.add("2-2+2");
        expectedForFirst.add("2*2-2");
        expectedForFirst.add("2+2-2");
        expectedForFirst.add("2-2-2");

        Examples examples = new Examples(2, "");

        assertEquals(expectedForFirst, examples.toPutSigns(exampleFirst));

        List<String> expectedForSecond = new ArrayList<>();
        expectedForSecond.add("((2*2))");
        expectedForSecond.add("((2+2))");
        expectedForSecond.add("((2-2))");

        assertEquals(expectedForSecond, examples.toPutSigns(exampleSecond));
    }

    @Test
    public void toCountSpacesTest() {
        Examples examples = new Examples(2, "2 2 2");
        assertEquals(3, examples.toCountSpaces("2 2 2 2"));
        assertEquals(1, examples.toCountSpaces("22 22"));
        assertEquals(3, examples.toCountSpaces("(2 2) 2 2"));
        assertEquals(2, examples.toCountSpaces("22 (2 2)"));
    }

    @Test
    public void toPutResponseTest() {
        Map<Integer, String> examplesWithAnswers = new HashMap<>();
        examplesWithAnswers.put(0,"2-2");
        examplesWithAnswers.put(2, "2+2-2");
        examplesWithAnswers.put(8, "(2+2)*2");

        Examples examples = new Examples(2, "");

        Map<Integer, String> examplesWithSigns;
        examples.toPutResponse(0, "2-2");
        examples.toPutResponse(2, "2+2-2");
        examplesWithSigns = examples.toPutResponse(8, "(2+2)*2");
        assertEquals(examplesWithAnswers, examplesWithSigns);
    }

    @Test
    public void isEqualsTest() {
        Map<Integer, String> examplesWithSigns = new HashMap<>();
        examplesWithSigns.put(0, "2-2");
        examplesWithSigns.put(1, "2-2+2-2+1");
        examplesWithSigns.put(2, "2+2-2");
        examplesWithSigns.put(3, "2+2+1");
        examplesWithSigns.put(4, "2+2");
        examplesWithSigns.put(5, "2+2+1");
        examplesWithSigns.put(6, "(2+2)+2");
        examplesWithSigns.put(7, "(2+2)*2-1");
        examplesWithSigns.put(8, "(2+2)*2");


        Examples examples = new Examples(2, "");


        String check = examples.isEquals(examplesWithSigns, 2);
        assertEquals("2+2-2=2", check);

        check = examples.isEquals(examplesWithSigns, 3);
        assertEquals("2+2+1=3", check);

        check = examples.isEquals(examplesWithSigns, 4);
        assertEquals("2+2=4", check);

        check = examples.isEquals(examplesWithSigns, 5);
        assertEquals("2+2+1=5", check);

        check = examples.isEquals(examplesWithSigns, 6);
        assertEquals("(2+2)+2=6", check);

        check = examples.isEquals(examplesWithSigns, 7);
        assertEquals("(2+2)*2-1=7", check);

        check = examples.isEquals(examplesWithSigns, 8);
        assertEquals("(2+2)*2=8", check);

        check = examples.isEquals(examplesWithSigns, 10);
        assertEquals("Решения нет", check);
    }
}
