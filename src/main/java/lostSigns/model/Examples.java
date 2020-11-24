package lostSigns.model;


import java.util.*;

public class Examples {
    private final int enteredAnswer;
    private final String example;
    private ArrayList<String> examplesWithSigns = new ArrayList<>();
    private final Map<Integer, String> examplesWithAnswers = new HashMap<>();


    public Examples(int enteredAnswer, String example) {
        this.enteredAnswer = enteredAnswer;
        this.example = example;
        examplesWithSigns.removeAll(examplesWithSigns);
        examplesWithAnswers.clear();
    }


    public String toSolve() {
        List<List<String>> calculatedExamples = new ArrayList<>();
        examplesWithSigns = toPutSigns(example);
        List<String> polishNotationForm;
        for (String in : examplesWithSigns) {
            PolishNotation polishNotation = new PolishNotation();
            polishNotationForm = polishNotation.parse(in);
            boolean flag = PolishNotation.flag;
            if (flag) {
                calculatedExamples.add(polishNotationForm);
            }
        }
        Repetitions repetitionsInList = new Repetitions(enteredAnswer, true, calculatedExamples);
        List<List<String>> answersForExamples = repetitionsInList.repetition();
        answersForExamples.removeIf(i -> i.size() > 1);
        for(int i = 0; i < answersForExamples.size(); i++) {
            toPutResponse(Integer.parseInt(answersForExamples.get(i).get(0)), examplesWithSigns.get(i));
        }
        return isEquals(examplesWithAnswers, enteredAnswer);
    }

    public ArrayList<String> toPutSigns(String example) {
        int count = toCountSpaces(example);
        List<String> signs = CombinationOfSigns.toCombinationSigns(count);
        examplesWithSigns = (ArrayList<String>) PutterSigns.putSigns(signs, example);
        return examplesWithSigns;
    }

    public int toCountSpaces(String example) {
        int count = 0;
        for(int i = 0; i < example.length(); i++) {
            if (example.charAt(i) == ' ') count++;
        }
        return count;
    }

    public Map<Integer, String> toPutResponse(Integer calc, String in) {
        examplesWithAnswers.put(calc, in);
        return examplesWithAnswers;
    }

    public String isEquals(Map<Integer, String> examplesWithSings, int enteredAnswer) {
        for (Map.Entry<Integer, String> index : examplesWithSings.entrySet()) {
            if (index.getKey() == enteredAnswer) return index.getValue() + "=" + enteredAnswer;
        }
        return "Решения нет";
    }
}
