
/*
 * Derrick Lin
 */
import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {

        double num1;
        double num2;
        double result = 0;
        char operator;
        boolean cont = true; // Added feature: flag to continue calculations

        Scanner input = new Scanner(System.in);

        while (cont) { // Added feature: allow for multiple calculations

            System.out.println("Please enter num1");
            num1 = input.nextDouble();
            System.out.println("Please enter num2");
            num2 = input.nextDouble();
    
            System.out.println("Choose operator +, -, *, /, or ^"); // Added feature: exponentiation
            operator = input.next().charAt(0);
    
            if (operator == '+') {
                result = num1 + num2;
            } else if (operator == '-') {
                result = num1 - num2;
            } else if (operator == '*') {
                result = num1 * num2;
            } else if (operator == '/') {
                if (num2 == 0) {
                    System.out.println("Cannot divide by zero");
                } else {
                    result = num1 / num2;
                }
            } else if (operator == '^') {
                result = Math.pow(num1, num2);
            } else {
                System.out.println("Please re-enter operator");
            }
    
            System.out.printf("%.2f %c %.2f = %.2f\n", num1, operator, num2, result); // Added feature: print the calculation
    
            System.out.println("Do you want to continue? (Y/N)"); // Added feature: ask user if they want to continue
            char choice = input.next().charAt(0);
            while (choice != 'Y' && choice != 'y' && choice != 'N' && choice != 'n') {
                System.out.println("Please select Y or N");
                choice = input.next().charAt(0);
            }
            if (choice == 'N' || choice == 'n') {
                cont = false;
            } else if (choice == 'Y' || choice == 'y') {
                cont = true;
            }
        }
    
        input.close();
    }
}