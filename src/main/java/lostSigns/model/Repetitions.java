package lostSigns.model;

import java.util.ArrayList;
import java.util.List;

public class Repetitions {
    private int answer = 0;
    private boolean doing = true;
    private List<List<String>> expression;

    public Repetitions() {
    }

    public Repetitions(int answer, boolean doing, List<List<String>> expression) {
        this.answer = answer;
        this.doing = doing;
        this.expression = expression;
    }

    public List<List<String>> repetition() {
        for(int index = 0; index < expression.size() && doing; index++) {
            toDeleteRepetition(expression.get(index), index);
        }
        return expression;
    }

    public List<String> toDeleteRepetition(List<String> list, int index) {
        PolishNotation polishNotation = new PolishNotation();
        List<String> signNeedToDelete = new ArrayList<>();
        int count = -2;
        for (String s : list) {
                signNeedToDelete.add(s);
            if (!polishNotation.isOperator(s)) count++;
            if (polishNotation.isOperator(s) && count == 0) count--;
        }

        int calculateToPut = Math.round(Calc.calc(signNeedToDelete));
        toDelete(expression, signNeedToDelete, calculateToPut, index);
        if (expression.get(index).size() != 1) toDeleteRepetition(expression.get(index), index);
        if (expression.get(index).size() == 1 && calculateToPut == answer) {
            doing = false;
            return expression.get(index);
        }
        return expression.get(index);
    }


    public List<List<String>> toDelete(List<List<String>> expr,List<String> signNeedToDelete,
                                              int calculateToPut, int index) {

        String n = String.valueOf(calculateToPut);
        int c = 0;
        for (; index < expr.size(); index++) {
            for (int i = 0; i < signNeedToDelete.size() && i < expr.get(index).size(); i++) {
                if (expr.get(index).get(i).equals(signNeedToDelete.get(i))) c++;
            }
            if (c == signNeedToDelete.size()) {
                expr.get(index).subList(0, signNeedToDelete.size()).clear();
                expr.get(index).add(0, n);
            }
            expression = expr;
            c = 0;
        }
        return expr;
    }
}
