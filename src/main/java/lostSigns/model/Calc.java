package lostSigns.model;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class Calc {
    public static Integer calc(List<String> postfix) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (String x : postfix) {
            switch (x) {
                case "+":
                    stack.push(stack.pop() + stack.pop());
                    break;
                case "-":
                    Integer b = stack.pop(), a = stack.pop();
                    stack.push(a - b);
                    break;
                case "*":
                    stack.push(stack.pop() * stack.pop());
                    break;
                default:
                    stack.push(Integer.valueOf(x));
                    break;
            }
        }
        return stack.pop();
    }
}