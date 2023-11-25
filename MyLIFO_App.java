package lab10;

import java.util.*;

public class MyLIFO_App {

    public static <E> void reserve(E[] array) {
        Stack<E> stack = new Stack<>();
        for (E element : array) {
            stack.push(element);
        }
        for (int i = 0; i < array.length; i++) {
            array[i] = stack.pop();
        }
    }

    public static boolean isCorrect(String input) {
        char[] inputArr = toArrayChar(input);
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < inputArr.length; i++) {
            if (isOpen(inputArr[i])) {
                stack.push(inputArr[i]);
            } else {
                if (stack.isEmpty()) return false;
                char tem = stack.peek();
                if (isDouble(tem, inputArr[i])) {
                    stack.pop();
                } else return false;
            }
        }
        if (!stack.isEmpty()) return false;

        return true;
    }

    public static boolean isOpen(char input) {
        return input == '{' || input == '[' || input == '(';
    }

    public static boolean isDouble(char open, char close) {
        return (open == '(' && close == ')') || (open == '{' && close == '}') || (open == '[' && close == ']');
    }

    public static char[] toArrayChar(String input) {
        char[] res = new char[input.length()];
        for (int i = 0; i < res.length; i++) {
            res[i] = input.charAt(i);
        }
        return res;
    }

    public static int evaluateExpression(String expression) {
        String operand = "";
        Stack<Integer> operandStack = new Stack<>();
        Stack<Character> operatorStack = new Stack<>();
        for (int i = 0; i < expression.length(); i++) {
            char curr = expression.charAt(i);
            if (!isOperator(curr)) {
                operand += curr;
                if (i == expression.length() - 1) operandStack.push(Integer.parseInt(operand));
            } else {
                if (!operand.equals("")) {
                    operandStack.push(Integer.parseInt(operand));
                }
                switch (curr) {
                    case '+', '-':
                        if (!operatorStack.isEmpty() && operandStack.size() > 1 && operatorStack.peek() != '(') {
                            int tempRes = calc(operandStack.pop(), operandStack.pop(), operatorStack.pop());
                            operandStack.push(tempRes);
                        }
                        operatorStack.push(curr);
                        break;
                    case '*', '/':
                        if (!operatorStack.isEmpty()) {
                            if ((operatorStack.peek() == '*' || operatorStack.peek() == '/') && operatorStack.peek() != '(') {
                                operandStack.push(calc(operandStack.pop(), operandStack.pop(), operatorStack.pop()));
                            }
                        }
                        operatorStack.push(curr);
                        break;
                    case ')':
                        while (operatorStack.peek() != '(') {
                            operandStack.push(calc(operandStack.pop(), operandStack.pop(), operatorStack.pop()));
                        }
                        operatorStack.pop();
                        break;
                    default:
                        operatorStack.push(curr);
                }
                operand = "";
            }
        }
        while (!operatorStack.isEmpty()) {
            operandStack.push(calc(operandStack.pop(), operandStack.pop(), operatorStack.pop()));
        }
        return operandStack.pop();
    }

    public static boolean isOperator(char input) {
        return input == '+' || input == '-' || input == '*' || input == '/' || input == '(' || input == ')';
    }

    public static int calc(int operand1, int operand2, char operator) {
        int res = 0;
        switch (operator) {
            case '+':
                res = operand1 + operand2;
                break;
            case '-':
                res = operand2 - operand1;
                break;
            case '*':
                res = operand2 * operand1;
                break;
            case '/':
                res = operand2 / operand1;
                break;
            default:
        }
        return res;
    }

    public static void main(String[] args) {
        String[] a = {"D", "A", "I"};
        reserve(a);
//        for (String b : a) {
//            System.out.print(b);
//        }

        String input = "40/40";
        int res = evaluateExpression(input);
        System.out.println(res);
    }

}
