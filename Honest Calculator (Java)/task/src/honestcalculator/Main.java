package honestcalculator;

import java.util.Scanner;


public class Main {

    private static final String MSG_0 = "Enter an equation";
    private static final String MSG_1 = "Do you even know what numbers are? Stay focused!";
    private static final String MSG_2 = "Yes ... an interesting math operation. You've slept through all classes, haven't you?";
    private static final String MSG_3 = "Yeah... division by zero. Smart move...";
    private static final String MSG_4 = "Do you want to store the result? (y / n):";
    private static final String MSG_5 = "Do you want to continue calculations? (y / n):";
    private static final String MSG_6 = " ... lazy";
    private static final String MSG_7 = " ... very lazy";
    private static final String MSG_8 = " ... very, very lazy";
    private static final String MSG_9 = "You are";
    public static final String MSG_10 = "Are you sure? It is only one digit! (y / n)";
    public static final String MSG_11 = "Don't be silly! It's just one number! Add to the memory? (y / n)";
    public static final String MSG_12 = "Last chance! Do you really want to embarrass yourself? (y / n)";

    /**
     * Checks if the given string represents an integer value, i.e. has no fractional part.
     *
     * @param v the string to check
     * @return true if the string represents an integer, false otherwise
     */
    private static boolean isInteger(String v) {
        try {
            double value = Double.parseDouble(v);
            return value == (int) value;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isOneDigit(String v) {
        double value = Double.parseDouble(v);
        return isInteger(v) && value > -10 && value < 10;
    }

    private static void checkLazy(String v1, String v2, String v3) {
        StringBuilder sb = new StringBuilder();
        if (isOneDigit(v1) && isOneDigit(v2)) {
            sb.append(MSG_6);
        }
        if ((v1.equals("1") || v2.equals("1")) && v3.equals("*")) {
            sb.append(MSG_7);
        }
        if ((v1.equals("0.0") || v2.equals("0.0") || v1.equals("0") || v2.equals("0")) && (v3.equals("+") || v3.equals("-") || v3.equals("*"))) {
            sb.append(MSG_8);
        }
        if (!sb.isEmpty()) {
            System.out.println(MSG_9 + sb);
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        double memory = 0.0;
        while (true) {
            System.out.println(MSG_0);
            String calc = sc.nextLine();
            String[] split = calc.split(" ");
            String x, y, oper;
            x = split[0];
            oper = split[1];
            y = split[2];

            if (x.equals("M")) {
                x = Double.toString(memory);
            }
            if (y.equals("M")) {
                y = Double.toString(memory);
            }

            try {
                Double.parseDouble(x);
                Double.parseDouble(y);
            } catch (NumberFormatException e) {
                System.out.println(MSG_1);
                continue;
            }
            if (!oper.matches("[+\\-*/]")) {
                System.out.println(MSG_2);
                continue;
            }

            checkLazy(x, y, oper);

            double xd = Double.parseDouble(x);
            double yd = Double.parseDouble(y);

            if (yd == 0 && oper.equals("/")) {
                System.out.println(MSG_3);
                continue;
            }
            double result = switch (oper) {
                case "+" -> xd + yd;
                case "-" -> xd - yd;
                case "*" -> xd * yd;
                case "/" -> xd / yd;
                default -> 0; // This should never happen due to the regex check above
            };


            System.out.println(result);
            System.out.println(MSG_4);
            String store = sc.nextLine();
            if (store.equals("y")) {
                if (isOneDigit(Double.toString(result))) {
                    for (int i = 10; i <= 12; i++) {
                        System.out.println(switch (i) {
                            case 10 -> MSG_10;
                            case 11 -> MSG_11;
                            case 12 -> MSG_12;
                            default -> "";
                        });
                        String ans = sc.nextLine();
                        if (ans.equals("n")) {
                            break;
                        }
                    }
                } else {
                    memory = result;
                }
            }
            System.out.println(MSG_5);
            String cont = sc.nextLine();
            if (cont.equals("n")) {
                break;
            }

        }


    }
}
