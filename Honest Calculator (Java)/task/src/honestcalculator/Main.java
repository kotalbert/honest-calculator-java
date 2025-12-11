package honestcalculator;

import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        String MSG_0 = "Enter an equation";
        String MSG_1 = "Do you even know what numbers are? Stay focused!";
        String MSG_2 = "Yes ... an interesting math operation. You've slept through all classes, haven't you?";
        String MSG_3 = "Yeah... division by zero. Smart move...";

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println(MSG_0);
            String calc = sc.nextLine();
            String[] split = calc.split(" ");
            String x, y, oper;
            x = split[0];
            oper = split[1];
            y = split[2];
            try {
                Float.parseFloat(x);
                Float.parseFloat(y);
            } catch (NumberFormatException e) {
                System.out.println(MSG_1);
                continue;
            }
            if (!oper.matches("[+\\-*/]")) {
                System.out.println(MSG_2);
                continue;
            }
            float xf = Float.parseFloat(x);
            float yf = Float.parseFloat(y);

            if (yf == 0 && oper.equals("/")) {
                System.out.println(MSG_3);
                continue;
            }
            float result = switch (oper) {
                case "+" -> xf + yf;
                case "-" -> xf - yf;
                case "*" -> xf * yf;
                case "/" -> xf / yf;
                default -> 0; // This should never happen due to the regex check above
            };
            System.out.println(result);
            return;
        }


    }
}
