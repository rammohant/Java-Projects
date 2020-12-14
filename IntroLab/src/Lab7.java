/*************************************
 * Varun Venkatesh and Tara Ram Mohan
 * CMSC 255 Section 3
 *************************************/
import java.util.Scanner;
import java.lang.Math;

public class Lab7 {
    public static void main(String[] args) {
        // variable declarations for part 1
        String title;
        String firstName;
        String lastName;
        Scanner in = new Scanner(System.in);

        // prompt for input for part 1
        System.out.print("Enter a title: ");
        title = in.next();
        System.out.print("Enter your first name: ");
        firstName = in.next();
        System.out.print("Enter a your last name: ");
        lastName = in.next();

        // call the method for part 1
        greeting(title, firstName, lastName);

        // variable declarations for part 2
        int num1;
        int num2;

        // user prompts for part 2
        System.out.print("Enter first number: ");
        num1 = in.nextInt();
        System.out.print("Enter second number: ");
        num2 = in.nextInt();

        // call the methods max and sumTo inside the println statement
        System.out.println("The largest number is " + max(num1, num2));
        System.out.println("The sum of the numbers is " + sumTo(num1, num2));
    }// end of main method

    /******************** greeting method goes here*********************/
    public static String greeting (String title, String firstName, String lastName) {
        String greet = "0";
        System.out.println();
        System.out.println("Dear" + " " + title + " " + firstName + " " + lastName);
        System.out.println();
        return greet;
    }

    /***********************end of method*************************/

    /******************** max method goes here*********************/
    public static int max(int num1, int num2) {
        int result;
        if (num1 > num2) {
            result = num1;
        } else {
            result = num2;
        }
        return result;
    }
    /***********************end of method*************************/

    /******************** sumTo method goes here*********************/

    public static int sumTo(int num1, int num2) {
        int sum = 0;
        for (int i = Math.min(num1,num2); i <= Math.max(num1,num2); i++)
            sum += i;
        return sum;
        }
    /***********************end of method*************************/
}
