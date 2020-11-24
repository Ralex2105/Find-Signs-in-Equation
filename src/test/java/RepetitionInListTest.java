import lostSigns.model.Repetitions;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class RepetitionInListTest {

    @Test
    public void repetitionTest() {
        List<String> partOneInTestList = new ArrayList<>();
        partOneInTestList.add("2");
        partOneInTestList.add("2");
        partOneInTestList.add("*");
        partOneInTestList.add("2");
        partOneInTestList.add("*");
        partOneInTestList.add("2");
        partOneInTestList.add("*");
        List<String> partTwoInTestList = new ArrayList<>();
        partTwoInTestList.add("2");
        partTwoInTestList.add("2");
        partTwoInTestList.add("*");
        partTwoInTestList.add("2");
        partTwoInTestList.add("*");
        partTwoInTestList.add("2");
        partTwoInTestList.add("+");
        List<String> partThreeInTestList = new ArrayList<>();
        partThreeInTestList.add("2");
        partThreeInTestList.add("2");
        partThreeInTestList.add("*");
        partThreeInTestList.add("2");
        partThreeInTestList.add("+");
        partThreeInTestList.add("2");
        partThreeInTestList.add("*");

        List<List<String>> testList = new ArrayList<>();
        testList.add(partOneInTestList);
        testList.add(partTwoInTestList);
        testList.add(partThreeInTestList);

        List<List<String>> expectedList = new ArrayList<>();
        List<String> partOneInExpected = new ArrayList<>();
        partOneInExpected.add("16");
        List<String> partTwoInExpected = new ArrayList<>();
        partTwoInExpected.add("2");
        partTwoInExpected.add("2");
        partTwoInExpected.add("*");
        partTwoInExpected.add("2");
        partTwoInExpected.add("*");
        partTwoInExpected.add("2");
        partTwoInExpected.add("+");
        List<String> partThreeInExpected = new ArrayList<>();
        partThreeInExpected.add("2");
        partThreeInExpected.add("2");
        partThreeInExpected.add("*");
        partThreeInExpected.add("2");
        partThreeInExpected.add("+");
        partThreeInExpected.add("2");
        partThreeInExpected.add("*");
        expectedList.add(partOneInExpected);
        expectedList.add(partTwoInExpected);
        expectedList.add(partThreeInExpected);

        Repetitions repetitionsInList = new Repetitions(16, true, testList);
        assertEquals(expectedList, repetitionsInList.repetition());


        partOneInTestList = new ArrayList<>();
        partOneInTestList.add("2");
        partOneInTestList.add("2");
        partOneInTestList.add("*");
        partOneInTestList.add("2");
        partOneInTestList.add("*");
        partOneInTestList.add("2");
        partOneInTestList.add("*");
        partTwoInTestList = new ArrayList<>();
        partTwoInTestList.add("2");
        partTwoInTestList.add("2");
        partTwoInTestList.add("*");
        partTwoInTestList.add("2");
        partTwoInTestList.add("*");
        partTwoInTestList.add("2");
        partTwoInTestList.add("+");
        partThreeInTestList = new ArrayList<>();
        partThreeInTestList.add("2");
        partThreeInTestList.add("2");
        partThreeInTestList.add("*");
        partThreeInTestList.add("2");
        partThreeInTestList.add("+");
        partThreeInTestList.add("2");
        partThreeInTestList.add("*");

        testList = new ArrayList<>();
        testList.add(partOneInTestList);
        testList.add(partTwoInTestList);
        testList.add(partThreeInTestList);

        expectedList = new ArrayList<>();
        partOneInExpected = new ArrayList<>();
        partOneInExpected.add("16");
        partTwoInExpected = new ArrayList<>();
        partTwoInExpected.add("10");
        partThreeInExpected = new ArrayList<>();
        partThreeInExpected.add("12");
        expectedList.add(partOneInExpected);
        expectedList.add(partTwoInExpected);
        expectedList.add(partThreeInExpected);

        repetitionsInList = new Repetitions(2, true, testList);
        assertEquals(expectedList, repetitionsInList.repetition());
    }

    @Test
    public void toDeleteRepetitionTest() {

        List<String> partOneInTestList = new ArrayList<>();
        partOneInTestList.add("2");
        partOneInTestList.add("2");
        partOneInTestList.add("*");
        partOneInTestList.add("2");
        partOneInTestList.add("*");
        partOneInTestList.add("2");
        partOneInTestList.add("*");
        List<String> partTwoInTestList = new ArrayList<>();
        partTwoInTestList.add("2");
        partTwoInTestList.add("2");
        partTwoInTestList.add("*");
        partTwoInTestList.add("2");
        partTwoInTestList.add("*");
        partTwoInTestList.add("2");
        partTwoInTestList.add("+");
        List<String> partThreeInTestList = new ArrayList<>();
        partThreeInTestList.add("2");
        partThreeInTestList.add("2");
        partThreeInTestList.add("*");
        partThreeInTestList.add("2");
        partThreeInTestList.add("+");
        partThreeInTestList.add("2");
        partThreeInTestList.add("*");

        List<List<String>> testList = new ArrayList<>();
        testList.add(partOneInTestList);
        testList.add(partTwoInTestList);
        testList.add(partThreeInTestList);

        List<String> partTwoExpected = new ArrayList<>();
        partTwoExpected.add("16");
        List<String> partOneExpected = new ArrayList<>();
        partOneExpected.add("10");
        List<String> partThreeExpected = new ArrayList<>();
        partThreeExpected.add("12");

        Repetitions repetitionInList = new Repetitions(16, true, testList);
        assertEquals(partTwoExpected, repetitionInList.toDeleteRepetition(partOneInTestList, 0));
        repetitionInList = new Repetitions(10, true, testList);
        assertEquals(partOneExpected, repetitionInList.toDeleteRepetition(partTwoInTestList, 1));
        repetitionInList = new Repetitions(12, true, testList);
        assertEquals(partThreeExpected, repetitionInList.toDeleteRepetition(partThreeInTestList, 2));
    }


    @Test
    public void toDeleteTest() {

        List<String> signNeedToDelete = new ArrayList<>();
        signNeedToDelete.add("2");
        signNeedToDelete.add("2");
        signNeedToDelete.add("*");

        List<String> partOneInTestList = new ArrayList<>();
        partOneInTestList.add("2");
        partOneInTestList.add("2");
        partOneInTestList.add("*");
        partOneInTestList.add("2");
        partOneInTestList.add("*");
        partOneInTestList.add("2");
        partOneInTestList.add("*");
        List<String> partTwoInTestList = new ArrayList<>();
        partTwoInTestList.add("2");
        partTwoInTestList.add("2");
        partTwoInTestList.add("*");
        partTwoInTestList.add("2");
        partTwoInTestList.add("*");
        partTwoInTestList.add("2");
        partTwoInTestList.add("+");
        List<String> partThreeInTestList = new ArrayList<>();
        partThreeInTestList.add("2");
        partThreeInTestList.add("2");
        partThreeInTestList.add("*");
        partThreeInTestList.add("2");
        partThreeInTestList.add("+");
        partThreeInTestList.add("2");
        partThreeInTestList.add("*");

        List<List<String>> testList = new ArrayList<>();
        testList.add(partOneInTestList);
        testList.add(partTwoInTestList);
        testList.add(partThreeInTestList);

        List<String> partOneInExpected = new ArrayList<>();
        partOneInExpected.add("4");
        partOneInExpected.add("2");
        partOneInExpected.add("*");
        partOneInExpected.add("2");
        partOneInExpected.add("*");
        List<String> partTwoInExpected = new ArrayList<>();
        partTwoInExpected.add("4");
        partTwoInExpected.add("2");
        partTwoInExpected.add("*");
        partTwoInExpected.add("2");
        partTwoInExpected.add("+");
        List<String> partThreeInExpected = new ArrayList<>();
        partThreeInExpected.add("4");
        partThreeInExpected.add("2");
        partThreeInExpected.add("+");
        partThreeInExpected.add("2");
        partThreeInExpected.add("*");

        List<List<String>> expectedList = new ArrayList<>();
        expectedList.add(partOneInExpected);
        expectedList.add(partTwoInExpected);
        expectedList.add(partThreeInExpected);


        Repetitions repetitionInList = new Repetitions();
        int numberToPut = 4;
        assertEquals(expectedList, repetitionInList.toDelete(testList, signNeedToDelete, numberToPut, 0));


        signNeedToDelete = new ArrayList<>();
        signNeedToDelete.add("8");
        signNeedToDelete.add("2");
        signNeedToDelete.add("*");

        numberToPut = 16;

        testList = new ArrayList<>();
        partOneInTestList = new ArrayList<>();
        partOneInTestList.add("8");
        partOneInTestList.add("2");
        partOneInTestList.add("*");
        partTwoInTestList = new ArrayList<>();
        partTwoInTestList.add("8");
        partTwoInTestList.add("2");
        partTwoInTestList.add("+");
        partThreeInTestList = new ArrayList<>();
        partThreeInTestList.add("4");
        partThreeInTestList.add("2");
        partThreeInTestList.add("+");
        partThreeInTestList.add("2");
        partThreeInTestList.add("*");

        testList.add(partOneInTestList);
        testList.add(partTwoInTestList);
        testList.add(partThreeInTestList);

        expectedList = new ArrayList<>();
        partOneInExpected = new ArrayList<>();
        partOneInExpected.add("16");
        partTwoInExpected = new ArrayList<>();
        partTwoInExpected.add("8");
        partTwoInExpected.add("2");
        partTwoInExpected.add("+");
        partThreeInExpected = new ArrayList<>();
        partThreeInExpected.add("4");
        partThreeInExpected.add("2");
        partThreeInExpected.add("+");
        partThreeInExpected.add("2");
        partThreeInExpected.add("*");
        expectedList.add(partOneInExpected);
        expectedList.add(partTwoInExpected);
        expectedList.add(partThreeInExpected);

        assertEquals(expectedList, repetitionInList.toDelete(testList, signNeedToDelete, numberToPut, 0));

        signNeedToDelete = new ArrayList<>();
        signNeedToDelete.add("2");
        signNeedToDelete.add("2");
        signNeedToDelete.add("2");
        signNeedToDelete.add("*");
        signNeedToDelete.add("+");

        numberToPut = 8;

        testList = new ArrayList<>();
        partOneInTestList = new ArrayList<>();
        partOneInTestList.add("2");
        partOneInTestList.add("2");
        partOneInTestList.add("2");
        partOneInTestList.add("*");
        partOneInTestList.add("+");
        partTwoInTestList = new ArrayList<>();
        partTwoInTestList.add("2");
        partTwoInTestList.add("2");
        partTwoInTestList.add("2");
        partTwoInTestList.add("*");
        partTwoInTestList.add("-");
        partThreeInTestList = new ArrayList<>();
        partThreeInTestList.add("2");
        partThreeInTestList.add("2");
        partThreeInTestList.add("+");
        partThreeInTestList.add("2");
        partThreeInTestList.add("*");

        testList.add(partOneInTestList);
        testList.add(partTwoInTestList);
        testList.add(partThreeInTestList);

        expectedList = new ArrayList<>();
        partOneInExpected = new ArrayList<>();
        partOneInExpected.add("8");
        partTwoInExpected = new ArrayList<>();
        partTwoInExpected.add("2");
        partTwoInExpected.add("2");
        partTwoInExpected.add("2");
        partTwoInExpected.add("*");
        partTwoInExpected.add("-");
        partThreeInExpected = new ArrayList<>();
        partThreeInExpected.add("2");
        partThreeInExpected.add("2");
        partThreeInExpected.add("+");
        partThreeInExpected.add("2");
        partThreeInExpected.add("*");
        expectedList.add(partOneInExpected);
        expectedList.add(partTwoInExpected);
        expectedList.add(partThreeInExpected);

        assertEquals(expectedList, repetitionInList.toDelete(testList, signNeedToDelete, numberToPut, 0));


    }
}
