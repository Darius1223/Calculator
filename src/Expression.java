import java.util.Scanner;

public class Expression {
    static Scanner scanner = new Scanner(System.in);
    Number number1, number2;
    char sign;

    Expression() {
        number1 = new Number();
        number2 = new Number();
    }

    public void inputExpression() {
        try {
            String[] tempExpr = scanner.nextLine().split(" ");

            if (tempExpr.length != 3) {
                throw new IndexOutOfBoundsException();
            }

            number1 = new Number(tempExpr[0]);

            if (!"+-*/".contains(String.valueOf(tempExpr[1].charAt(0))) || tempExpr[1].length() != 1) {
                throw new IllegalStateException();
            }

            sign = tempExpr[1].charAt(0);

            number2 = new Number(tempExpr[2]);

            if (!number1.type.equals(number2.type)) {
                throw new NumberFormatException();
            }

        } catch (NumberFormatException e) {
            System.out.println("ERROR: Types of numbers is not equals!");
            System.exit(-1);
        } catch (IllegalStateException e) {
            System.out.printf("ERROR: This sign '%s' is not valid!", sign);
            System.exit(-1);
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("ERROR: Format of expression = num1 sign num2!");
            System.exit(-1);
        }
    }


    @Override
    public String toString() {
        return number1 + " " + sign + " " + number2;
    }


    public Number calculate() {
        int res = 0;
        try {
            res = switch (sign) {
                case '+' -> number1.add(number2);
                case '-' -> number1.sub(number2);
                case '*' -> number1.mul(number2);
                case '/' -> number1.div(number2);
                default -> throw new IllegalStateException("Unexpected value: " + sign);
            };
        } catch (IllegalStateException error) {
            System.out.printf("This sign '%s' is not valid!", sign);
            System.exit(-1);
        }
        return new Number(res, number1.type);
    }

}
