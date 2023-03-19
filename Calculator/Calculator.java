
/*
 * Derrick Lin
 */
import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {

        // don't have to declare here, can declare with input
        //double num1;
        double num2;
        double result = 0;
        char operator;

        Scanner input = new Scanner(System.in);

        System.out.println("Please enter num1");
        double num1 = input.nextDouble();
        System.out.println("Please enter num2");
        num2 = input.nextDouble();

        System.out.println("Choose operator +, -, *, or /");
        operator = input.next().charAt(0);
        if (operator == '+') {
            result = num1 + num2;
        } else if (operator == '-') {
            result = num1 - num2;
        } else if (operator == '*') {
            result = num1 * num2;
        } else if (operator == '/') {
            result = num1 / num2;
        } else {
            System.out.println("Please re-enter operator");
        }

        //System.out.println(num1 + operator + num2 + "=" + result);
        System.out.printf("%.2f%c%.2f=%.2f",num1,operator,num2,result);

        input.close();
    }
}