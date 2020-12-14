/* Tara Ram Mohan
CMSC 401
Assignment 2 (Updated 2)
4 October 2020
 */
import java.util.Scanner;

public class cmsc401 {

    static int a, b;

    // Swaps element at index i with element at index j
    static int[] swapElements(int[] tempArray, int element1, int element2) {

        int temp = tempArray[element1];
        tempArray[element1] = tempArray[element2];
        tempArray[element2] = temp;
        return tempArray;
    }

    // Returns the position of pivot element
    static int Partition(int tempArray[], int left, int right) {

        int i = left;
        int j = left;
        int last = tempArray[right];

        while (j < right) {
            if (tempArray[j] < last) {
                tempArray = swapElements(tempArray, i, j);
                i++;
            } j++;
        }
        tempArray = swapElements(tempArray, i, right);
        return i;
    }

    //Randomly pick pivot element and use the partition step from the quick sort to find median
    static int findMedian(int tempArray[], int left, int right, int middle) {
        if (left <= right) {

            //Randomly pick pivot element from tempArray[]
            int n = (right - left) + 1;
            int pivot = (int) (Math.random() % n);
            tempArray = swapElements(tempArray, left + pivot, right);

            //Partition tempArray around random element
            int partitionIndex = Partition(tempArray, left, right);

            if (partitionIndex == middle) { //if pivot is the middle of the array, then b is median
                b = tempArray[partitionIndex];
                if (a != -1) {
                    return -1;
                }
            } else if (partitionIndex == middle - 1) { // if a & b are median of tempArray[] (when even size of array)
                a = tempArray[partitionIndex];
                if (b != -1)
                    return -1;
            }

            //if position is before middle element, find index of first half of tempArray[]
            if (partitionIndex >= middle)  {
                return findMedian(tempArray, left, partitionIndex - 1, middle);
            //If partitionIndex <= k and position is after middle element, then find the index in second half of the tempArray[]
            } else
                return findMedian(tempArray, partitionIndex + 1, right, middle);
        }

        return -1;
    }

    static void outputMedian(int tempArray[], int n) {

        int output;
        a = -1;
        b = -1;
        int middle = (int)Math.ceil(n/2.0); //take the ceiling of the middle of the array

        if (n % 2 == 1) {  // if n is odd
            findMedian(tempArray, 0, n - 1, middle);
            output = b;
        } else {    // If n is even
            findMedian(tempArray, 0, n - 1, middle);
            output = (a + b) / 2;
        }

        // Print median of tempArray[]
        System.out.print(output);
    }

    //create array containing the y-coordinates of gardens of size 1
    //EX. 4 gardens of size 2 would yield 24 gardens of size 1 because (3*2)*4 = 24
    static int[] createGardenArray(int ycoordArray[], int sizeArray[], int numSubgardens) {

        int length = numSubgardens * 3;
        int gardens[] = new int[length];

        for (int i = 0, j = 0; i < length; i++, j++) {
            if (sizeArray[j] == 3) {
                gardens[i] = ycoordArray[j];
                gardens[i + 1] = ycoordArray[j];
                gardens[i + 2] = ycoordArray[j];
                gardens[i + 3] = ycoordArray[j];
                gardens[i + 4] = ycoordArray[j];
                gardens[i + 5] = ycoordArray[j];
                gardens[i + 6] = ycoordArray[j];
                gardens[i + 7] = ycoordArray[j];
                gardens[i + 8] = ycoordArray[j];
                i += 8;
            } else if (sizeArray[j] == 2) {
                gardens[i] = ycoordArray[j];
                gardens[i + 1] = ycoordArray[j];
                gardens[i + 2] = ycoordArray[j];
                gardens[i + 3] = ycoordArray[j];
                gardens[i + 4] = ycoordArray[j];
                gardens[i + 5] = ycoordArray[j];
                i += 5;
            } else if (sizeArray[j] == 1) {
                gardens[i] = ycoordArray[j];
                gardens[i + 1] = ycoordArray[j];
                gardens[i + 2] = ycoordArray[j];
                i += 2;
            }
        } return gardens;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // variable for total number of lines Scanner must read
        int numGardens = sc.nextInt();

        int ycoord; //y-coordinate of one garden’s center point
        int size; //size of the garden (1, 2 or 3)
        int numSubgardens = 0;

        int ycoordArray[] = new int[numGardens];
        int sizeArray[] = new int[numGardens];

        // loop through input values and store y coordinates into ycoordArray and size of gardens into sizeArray
        for (int i = 0; i < numGardens; i++) {
            //store value in gardens Array
            ycoord = sc.nextInt();
            ycoordArray[i] = ycoord;
            size = sc.nextInt();
            numSubgardens += size;
            sizeArray[i] = size;
        }
        //create and populate Array with gardens of size 1 using sizeArray and ycoordArray
        int gardens[] = createGardenArray(ycoordArray,sizeArray,numSubgardens);

        outputMedian(gardens, gardens.length);
    }
}