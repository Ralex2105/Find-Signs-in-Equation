import lostSigns.model.PutterSigns;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;

public class PutterSignsTest {

    @Test
    public void putSigns() {

        List<String> signs = new ArrayList<>();
        signs.add("**");
        signs.add("*+");
        signs.add("*-");
        signs.add("+*");
        String example = "2 2 2";

        List<String> expected = new ArrayList<>();
        expected.add("2*2*2");
        expected.add("2*2+2");
        expected.add("2*2-2");
        expected.add("2+2*2");

        assertEquals(expected, PutterSigns.putSigns(signs, example));
    }
}
