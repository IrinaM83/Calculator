import com.sun.jdi.connect.Connector;

import java.io.IOException;
import java.util.Scanner;


public class Main {
    static int last = 100;
    static int numbers[] = {1, 4, 5, 9, 10, 50, 100, 500};
    static String letters[]  = { "I", "IV", "V", "IX", "X", "L", "C", "D"};
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        if(controlOperator(str)){
            try{
                throw new IOException();
            } catch (IOException e){
                System.out.println("throws Exception //т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            }
        }else Main.calc(str);

    }

    public static void calc(String input) {

        char op = searchOperator(input);

        String[] words = input.split("\\+|-|\\*|/");
        int a = 0, b = 0;
        if (words.length<2){
            try{
                throw new IOException();
            } catch (IOException e){
                System.out.println("throws Exception //т.к. строка не является математической операцией");
            }
        }else if (isNumeric(words[0]) && isNumeric(words[1])) {
            a = Integer.parseInt(words[0].trim());
            b = Integer.parseInt(words[1].trim());
            if (a < 1 || a > 10 || b < 1 || b > 10) {
                try {
                    throw new IOException();
                } catch (IOException e) {
                    System.out.println("throws Exception //т.к. числа должны быть от 1 до 10 включительно");
                }
            } else {
                System.out.println(operations(a, b, op));
            }
        } else {
            try {
                a = convertRomanToInt(words[0].trim());
                b = convertRomanToInt(words[1].trim());
                if (a < 1 || a > 10 || b < 1 || b > 10) {
                    try {
                        throw new IOException();
                    } catch (IOException e) {
                        System.out.println("throws Exception //т.к. числа должны быть от 1 до 10 включительно");
                    }
                }
                else if (operations(a, b, op) < 0) {
                    try {
                        throw new IOException();
                    } catch (IOException e) {
                        System.out.println("throws Exception //т.к. в римской системе нет отрицательных чисел");
                    }
                } else {
                    System.out.println();
                    System.out.println(convertIntegerToRoman(operations(a, b, op)));
                }


            } catch (Exception e) {
                System.out.println("throws Exception //т.к. используются одновременно разные системы счисления");
            }
    }
        }


        static String convertIntegerToRoman ( int number){
            String romanValue = "";
            int N = number;
            while (N > 0) {
                for (int i = 0; i < numbers.length; i++) {
                    if (N < numbers[i]) {
                        N -= numbers[i - 1];
                        romanValue += letters[i - 1];
                        break;
                    }
                }
            }
            return romanValue;
        }

        static int convertRomanToInt (String romanNumeral){
            int integerValue = 0;
            for (int i = 0; i < romanNumeral.length(); i++) {
                char ch = romanNumeral.charAt(i);
                int number = letterToNumber(ch);
                if (number == -1) {
                    throw new NumberFormatException("Invalid format");
                }
                if (last < number) number -= last - 1;
                integerValue += number;
                last = number;
            }
            return integerValue;
        }

        static int letterToNumber ( char letter){
            switch (letter) {
                case 'I':
                    return 1;
                case 'V':
                    return 5;
                case 'X':
                    return 10;
                case 'L':
                    return 50;
                case 'C':
                    return 100;
                default:
                    return -1;
            }
        }

        static boolean isNumeric (String str){
            int number;

            try {
                number = Integer.parseInt(str);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }

        static int operations ( int a, int b, char op){
            int c = 0;
            switch (op) {
                case '+':
                    c = a + b;
                    break;
                case '-':
                    c = a - b;
                    break;
                case '*':
                    c = a * b;
                    break;
                case '/':
                    c = a / b;
                    break;
            }
            return c;
        }

        static char searchOperator (String str){
            char op = 0;
            if (str.indexOf('+', 1) > 0) op = str.charAt(str.indexOf('+', 1));
            else if (str.indexOf('-', 1) > 0) op = str.charAt(str.indexOf('-', 1));
            else if (str.indexOf('*', 1) > 0) op = str.charAt(str.indexOf('*', 1));
            else if (str.indexOf('/', 1) > 0) op = str.charAt(str.indexOf('/', 1));
            return op;
        }

        static boolean controlOperator (String str){
        int count=0;
        char[] op = {'+', '-', '*', '/'};
        char[] strCh = str.toCharArray();
        for (int i =0; i < op.length; i++){
            for (int j = 0; j < strCh.length; j++){
                if (op[i] == strCh[j]){
                    count++;
                }
            }
        }
        return count>1;
        }
    }
