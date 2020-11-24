package lostSigns.model;

import java.util.ArrayList;
import java.util.List;

public class CombinationOfSigns {
    public interface PermuteCallback{
        public void handle(Object[] snapshot);
    };

    static void permute(Object[] a, int k, PermuteCallback callback) {
            int n = a.length;
            int[] indexes = new int[k];
            int total = (int) Math.pow(n, k);
            Object[] snapshot = new Object[k];

            while (total-- > 0) {
                for (int i = 0; i < k; i++){
                    snapshot[i] = a[indexes[i]];
                }
                callback.handle(snapshot);
                for (int i = 0; i < k; i++) {
                    if (indexes[i] >= n - 1) {
                        indexes[i] = 0;
                    } else {
                        indexes[i]++;
                        break;
                    }
                }
            }
        }

        public static List<String> toCombinationSigns(int count) {
            List<String> list = new ArrayList<>();
            Object[] chars = {'*', '+', '-'};
            PermuteCallback callback = snapshot -> {
                StringBuilder str = new StringBuilder();
                for (Object o : snapshot) {
                    str.append(o);
                }
                list.add(str.toString());
            };
            permute(chars, count, callback);
            return list;
        }
    }
