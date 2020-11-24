import lostSigns.model.Calc;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CalcTest {

    @Test
    public void calc() {
        List<String> postfix = Arrays.asList("2", "2", "+", "2", "*");
        Integer result = 8;
        assertEquals(result, Calc.calc(postfix));

        postfix = Arrays.asList("3", "2", "*", "2", "-");
        result = 4;
        assertEquals(result, Calc.calc(postfix));

        postfix = Arrays.asList("2", "2", "+", "2", "+");
        result = 6;
        assertEquals(result, Calc.calc(postfix));

        postfix = Arrays.asList("1", "1", "*", "1", "*");
        result = 1;
        assertEquals(result, Calc.calc(postfix));
    }
}
