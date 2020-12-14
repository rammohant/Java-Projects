/* Tara Ram Mohan
CMSC 401
Project 4
6 December 2020
 */
import java.util.*;

public class cmsc401 {

    public static int cutRod(int[] cuts, int n) {

        int[][] matrix = new int[cuts.length][cuts.length];

        //iterate through loops and populate matrix[i][j] using matrix multiplication
        for (int i = cuts.length - 1; i >= 0; --i) {
            for (int j = i + 1; j < cuts.length; ++j) {
                for (int k = i + 1; k < j; ++k) {
                    int temp = cuts[j] - cuts[i] + matrix[i][k] + matrix[k][j];
                    matrix[i][j] = Math.min(matrix[i][j] == 0 ? Integer.MAX_VALUE : matrix[i][j], temp);
                }
            }
        }
        return matrix[0][cuts.length - 1];
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        int size = sc.nextInt(); //size of the rod
        int numCP = sc.nextInt(); //number of cutting points

        int locationCP[] = new int[numCP+2]; //location of each of M distinct cutting points
        locationCP[0] = 0;
        locationCP[numCP+1] = size;
        for (int i = 1; i < numCP+1; i++) {
            locationCP[i] = sc.nextInt();
        }

        System.out.println(cutRod(locationCP, size));
    }
}
