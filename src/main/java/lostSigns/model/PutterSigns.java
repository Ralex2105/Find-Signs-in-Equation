package lostSigns.model;

import java.util.ArrayList;
import java.util.List;

public class PutterSigns {

    public static List<String> putSigns(List<String> signs, String example) {
        List<String> exampleWithSigns = new ArrayList<>();
        for (String sign : signs) {
            String ex = example;
            String str = "";
            for (int k = 0, j = 0; k < ex.length() && j < sign.length(); k++) {
                if (ex.charAt(k) == ' ') {
                    str = ex.substring(0, k) + sign.charAt(j) + ex.substring(k + 1);
                    ex = str;
                    k--;
                    j++;
                }
            }
            exampleWithSigns.add(str);
        }
        return exampleWithSigns;
    }
}
