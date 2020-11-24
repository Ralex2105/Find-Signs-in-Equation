import lostSigns.model.CombinationOfSigns;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CombinationOfSignsTest {

    @Test
    public void toCombinationSignsTest() {
        List<String> combinationOfSignsFirst = new ArrayList<>();
        combinationOfSignsFirst.add("**");
        combinationOfSignsFirst.add("+*");
        combinationOfSignsFirst.add("-*");
        combinationOfSignsFirst.add("*+");
        combinationOfSignsFirst.add("++");
        combinationOfSignsFirst.add("-+");
        combinationOfSignsFirst.add("*-");
        combinationOfSignsFirst.add("+-");
        combinationOfSignsFirst.add("--");

        assertEquals(combinationOfSignsFirst, CombinationOfSigns.toCombinationSigns(2));

        List<String> combinationOfSignsSecond = new ArrayList<>();
        combinationOfSignsSecond.add("*");
        combinationOfSignsSecond.add("+");
        combinationOfSignsSecond.add("-");

        assertEquals(combinationOfSignsSecond, CombinationOfSigns.toCombinationSigns(1));
    }
}
