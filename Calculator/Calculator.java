
/*
 * Derrick Lin
 */
import java.util.ArrayList;
import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {

        double num1;
        double num2;
        double result = 0;
        char operator;
        boolean cont = true;
        String history = "";
        ArrayList<Double> nums = new ArrayList<Double>();

        Scanner input = new Scanner(System.in);

        while (cont) {
            System.out.println("Do you want to perform a basic operation (B) or calculate statistics (S)?");
            char option = input.next().charAt(0);

            if (option == 'B' || option == 'b') {
                System.out.println("Please enter num1");
                num1 = input.nextDouble();
                System.out.println("Please enter num2");
                num2 = input.nextDouble();

                System.out.println("Choose operator +, -, *, /, or ^");
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

                history += String.format("%.2f %c %.2f = %.2f\n", num1, operator, num2, result);

                System.out.printf("%.2f %c %.2f = %.2f\n", num1, operator, num2, result);
                System.out.println("Calculation history:\n" + history);

            } else if (option == 'S' || option == 's') {
                nums.clear();

                System.out.println("Enter the number of data points: ");
                int n = input.nextInt();

                for (int i = 1; i <= n; i++) {
                    System.out.println("Enter data point " + i + ": ");
                    nums.add(input.nextDouble());
                }

                double sum = 0;
                for (double num : nums) {
                    sum += num;
                }
                double mean = sum / nums.size();

                double variance = 0;
                for (double num : nums) {
                    variance += Math.pow(num - mean, 2);
                }
                variance /= nums.size();

                double stdDev = Math.sqrt(variance);

                System.out.printf("Mean: %.2f\n", mean);
                System.out.printf("Variance: %.2f\n", variance);
                System.out.printf("Standard Deviation: %.2f\n", stdDev);
            } else {
                System.out.println("Invalid choice. Please select B or S.");
            }

            System.out.println("Do you want to continue? (Y/N)");
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