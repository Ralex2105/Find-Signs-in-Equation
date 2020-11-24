package lostSigns.controller;


import lostSigns.model.Examples;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class StartController {

    public void toHelp(JButton help) {
        help.addActionListener(e -> {
            try {
                ResourceBundle res = new PropertyResourceBundle(getClass().getResourceAsStream("help.txt"));
                JOptionPane.showMessageDialog(null, res.getString("help"));
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        });
    }

    public void toStart(JButton dicision, final JTextField example) {
        dicision.addActionListener(e -> {
            switch (check((example.getText()))) {
                case 0:
                    JOptionPane.showMessageDialog(null,
                            "Введите пример");
                    break;
                case 1:
                    JOptionPane.showMessageDialog(null,
                            "Введите корректный пример (см. Помощь)");
                    break;
                case 2:
                    String enteredExample = example.getText();
                    String[] parse = parser(enteredExample);
                    int answer = Integer.parseInt(parse[1].trim());
                    String exampleWithoutSigns = deleteSpaces(parse[0].trim());

                    Examples examples = new Examples(answer, exampleWithoutSigns);
                    String exampleWithSigns = examples.toSolve();

                    JOptionPane.showMessageDialog(null, exampleWithSigns);
                    break;
            }
        });
    }


    private String[] parser(String example) {
        return example.split("=");
    }

    public static int check(String example) {
        if (example.equals("")) return 0;
        String [] partsOfExample = new String[0];

        if (example.contains("=")) partsOfExample = example.split("=");
        if (partsOfExample.length > 2 || partsOfExample.length == 1) return 1;
        else if (!example.contains("=")) return 1;
        else if (checkArgument(partsOfExample[0]) & checkAnswer(partsOfExample[1])
                & checkParentheses(partsOfExample[0])) {
            return 2;
        } else {
            return 1;
        }
    }

    public static boolean checkParentheses(String exampleWithoutAnswer) {
        if (!exampleWithoutAnswer.contains("(") && ! exampleWithoutAnswer.contains(")")) return true;

        boolean isOpen = false;
        int countOpen = 0;
        int countNumber = 0;

        for (int i = 0; i < exampleWithoutAnswer.length(); i++) {
            if (exampleWithoutAnswer.charAt(i) == '(') {
                isOpen = true;
                countOpen++;
            }
            if (isInteger(exampleWithoutAnswer.charAt(i))) {
                if (isOpen && (!isInteger(exampleWithoutAnswer.charAt(i - 1)))) countNumber++;
        }
            if (exampleWithoutAnswer.charAt(i) == ')') {
                if (countNumber < 2 && isOpen) return false;
                countNumber = 0;
                countOpen--;
                if (countOpen == -1) return false;
                isOpen = false;
            }
        }
        return (countOpen == 0);
    }

    public static boolean isInteger(char s) {
        boolean isValidInteger = false;
        try
        {
            Integer.parseInt(String.valueOf(s));
            isValidInteger = true;
        }
        catch (NumberFormatException ignored) {}
        return isValidInteger;
    }

    public static boolean checkArgument(String example) {
        String parse = example.replace(")", "").replace("(", "");
        return parse.matches("^(\\s*\\d+\\s+\\d+)+\\s*\\d*\\s*");
    }

    public static boolean checkAnswer(String example) {
        return example.matches("^\\s*\\d+\\s*");
    }

    public static String deleteSpaces(String example) {
        String exampleWithoutSpaces;
        String parseExample = example.trim();
        List<String> exampleInList = new ArrayList<>();
        for (int i = 0, j = 0; i < parseExample.length(); i++, j++) {
           if (parseExample.charAt(i) == '(' || parseExample.charAt(i) == ')' || parseExample.charAt(i) == ' ') {
               addSymbols(parseExample, i, j, exampleInList);
           }
           if (i != 0 && isInteger(parseExample.charAt(i - 1)) && isInteger(parseExample.charAt(i))) {
               j--;
               plusSymbols(parseExample, i, j, exampleInList);
           } else if(isInteger(parseExample.charAt(i))) {
               addSymbols(parseExample, i, j, exampleInList);
           }
        }
        exampleInList.removeIf(i -> i.equals(" "));
        exampleWithoutSpaces = toStringList(exampleInList);
        return exampleWithoutSpaces;
    }

    public static List<String> plusSymbols(String example, int symbolsForPut,
                                           int placeForPut, List<String> exampleInList) {
        String iStr = exampleInList.get(placeForPut);
        exampleInList.remove(placeForPut);
        String what = String.valueOf(example.charAt(symbolsForPut));
        String need = iStr + what;
        exampleInList.add(placeForPut, need);
        return exampleInList;
    }

    public static List<String> addSymbols(String example, int symbolsForPut,
                                          int placeForPut, List<String> exampleInList) {
        exampleInList.add(placeForPut, String.valueOf(example.charAt(symbolsForPut)));
        return exampleInList;
    }

    public static String toStringList(List<String> list) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (list.get(i).matches("^\\d+") && ((i + 1) < list.size() && !list.get(i + 1).equals(")")))
                sb.append(" ");
            if (list.get(i).equals(")") && ((i + 1) < list.size() && !list.get(i + 1).equals(")")))
                sb.append(" ");
        }
        return sb.toString();
    }
}