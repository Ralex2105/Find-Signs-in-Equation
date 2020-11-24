package lostSigns.model;

import java.util.*;

public class PolishNotation {
    private static final String operators = "+-*";
    private static final String delimiters = "() " + operators;
    public static boolean flag = true;


    public boolean isDelimiter(String token) {
        if (token.length() != 1) return false;
        for (int i = 0; i < delimiters.length(); i++) {
            if (token.charAt(0) == delimiters.charAt(i)) return true;
        }
        return false;
    }

    public boolean isOperator(String token) {
        for (int i = 0; i < operators.length(); i++) {
            if (token.charAt(0) == operators.charAt(i)) return true;
        }
        return false;
    }

    public int priority(String token) {
        if (token.equals("(")) return 1;
        if (token.equals("+") || token.equals("-")) return 2;
        if (token.equals("*")) return 3;
        return 4;
    }

    public List<String> parse(String infix) {
        List<String> postfix = new ArrayList<>();
        Deque<String> stack = new ArrayDeque<>();
        StringTokenizer tokenizer = new StringTokenizer(infix, delimiters, true);
        String curr;
        while (tokenizer.hasMoreTokens()) {
            curr = tokenizer.nextToken();
            if (isDelimiter(curr)) {
                if (curr.equals("(")) stack.push(curr);
                else if (curr.equals(")")) {
                    while (!stack.peek().equals("(")) {
                        postfix.add(stack.pop()); }
                    stack.pop();
                } else {
                    while (!stack.isEmpty() && (priority(curr) <= priority(stack.peek()))) {
                        postfix.add(stack.pop());
                    }
                    stack.push(curr);
                }

            } else {
                postfix.add(curr);
            }
        }

        while (!stack.isEmpty()) {
            if (isOperator(stack.peek())) postfix.add(stack.pop());
        }
        return postfix;
    }
}