package com.example.homework1;

import java.util.regex.Pattern;

public class CalculatorLogic {

    float result = 0;

    public String calculate(String expression) {

        if (Pattern.matches("^\\d+([+-/x]\\d+)+$", expression)) { // Более надежная проверка
            String[] numbers = expression.split("[+-/x]");
            String[] operands = expression.split("\\d+"); // Изменено regex


            try {
                float result = Float.parseFloat(numbers[0]);
                for (int i = 1; i < operands.length; i++) { // Исправлено условие цикла
                    switch (operands[i]) {
                        case "+":
                            result += Float.parseFloat(numbers[i]);
                            break;
                        case "-":
                            result -= Float.parseFloat(numbers[i]);
                            break;
                        case "/":
                            float divisor = Float.parseFloat(numbers[i]);
                            if (divisor == 0) {
                                return "Error: Division by zero";
                            }
                            result /= divisor;
                            break;
                        case "x":
                            result *= Float.parseFloat(numbers[i]);
                            break;
                    }
                }
                return String.valueOf(result);

            } catch (NumberFormatException e) {
                return "Error: Invalid input";
            }
        } else {
            return "Error: Invalid expression";
        }
    }
}