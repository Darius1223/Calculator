public class Calculator {
    public static void main(String[] args) {
        Expression expression = new Expression();

        expression.inputExpression();

        System.out.println(expression.calculate());

    }
}
