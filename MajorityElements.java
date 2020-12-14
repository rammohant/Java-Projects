/* Tara Ram Mohan
CMSC 401 Assignment 1
6 September 2020
 */

import java.util.Scanner;

public class cmsc401 {

    public static void main(String[] args) {
        //candidate variables
        int first = 0, second = 0;

        //counter variables
        int countFirst = 0, countSecond = 0;

        Scanner sc = new Scanner(System.in);

        // variable for total number of lines Scanner must read
        int totalLines = sc.nextInt();

        //create temp array to store input values
        int temp[] = new int[totalLines];

        //variable for min count of majority element
        //majority element = elements occurring more than (NOT EQUAL TO) N/3 times
        double majority = totalLines/3;

        // loop through input values
        for (int i = 0; i < totalLines; i++) {

            //store value in temp array
            temp[i] = sc.nextInt();

        }

        countFirst = 0;
        countSecond = 0;

        // loop through array again and make sure current candidates are majority elements
        for (int i = 0; i < totalLines; i++) {
            if (temp[i] == first) {
                countFirst++;
            } else if (temp[i] == second) {
                countSecond++;
            }
        }

        // sort and output majority elements
        if (countFirst > majority && countSecond > majority) {
            if (first < second) {
                System.out.print(first + " ");
                System.out.print(second);
            } else {
                System.out.print(second + " ");
                System.out.print(first);
            }
        } else if (countFirst > majority) {
                System.out.print(first);
        } else if (countSecond > majority) {
                System.out.print(second);
        } else {
            System.out.print("-1");
        }
    }
}
