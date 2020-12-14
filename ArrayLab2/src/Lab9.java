/****************************************************************************************************
 * Lab 9
 * **************************************************************************************************
 * Tara Ram Mohan and Joshua Del Orbe
 * 10/16/19
 * CMSC 255 Section 3
 ****************************************************************************************************/
public class Lab9 {
    public static void addTo10(int [][] array) {
        int total = 0;
        int key = 0;
        for (int row = 0; row < array.length; row++) {
            for (int column = 0; column < array[row].length; column++) {
                total += array[row][column];
            }
            int zero = 0;
            if ((total != 10)) {
                if (total > 10) {
                    zero = -(total - 10);
                }
                if (total < 10) {
                    zero = (10-total);
                }
            }
                for (int column = 0; column < array[row].length; column++) {
                    if (key == array[row][column]) {
                        array[row][column] = zero;
                    }

            }
                total = 0;
        }
    }


    public static void setHints(int [][] array) {
        for (int row = 0; row < array.length; row++) {
            for (int column = 0; column < array[row].length; column++) {
                System.out.print(array[row][column] + " ");
            }
            System.out.println();
        }
        for (int row = 0; row < array.length; row++) {
            for (int column = 0; column < array[row].length; column++) {
                int temp = array[row][column];
                if (isBomb(temp)) {

                    if (isInBounds(row-1,column-1, array) && !isBomb(array[row-1][column-1])) {
                        array[row-1][column-1] += 1;
                    }
                    if (isInBounds(row-1,column, array) && !isBomb(array[row-1][column])) {
                        array[row-1][column] += 1;
                    }
                    if (isInBounds(row-1,column+1, array) && !isBomb(array[row-1][column+1])) {
                        array[row-1][column+1] += 1;
                    }
                    if (isInBounds(row,column-1, array) && !isBomb(array[row][column-1])) {
                        array[row][column-1] += 1;
                    }
                    if (isInBounds(row,column+1, array) && !isBomb(array[row][column+1])) {
                        array[row][column+1] += 1;
                    }
                    if (isInBounds(row+1,column-1, array) && !isBomb(array[row+1][column-1])) {
                        array[row+1][column-1] += 1;
                    }
                    if (isInBounds(row+1,column, array) && !isBomb(array[row+1][column])) {
                        array[row+1][column] += 1;
                    }
                    if (isInBounds(row+1,column+1, array) && !isBomb(array[row+1][column+1])) {
                        array[row+1][column+1] += 1;
                }
            }
        }
    }
        System.out.println();
        for (int row = 0; row < array.length; row++) {
            for (int column = 0; column < array[row].length; column++) {
                System.out.print(array[row][column] + " ");
            }
            System.out.println();
        }
    }

    public static boolean isInBounds(int row, int columns, int[][] board){
        return (row >= 0 && row < board.length) && (columns >= 0 && columns < board[row].length);
    }

    public static boolean isBomb(int value){
        return value == -1;
    }


    public static void main(String[] args)
    {

        int [][] matrix = {{-1, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, -1, 0, -1},
                {0, 0, 0, -1, 0, 0}, {0, 0, 0, 0, 0, 0}};
        int [][] matrix2 = {{6, 3, 2, 0, 4}, {34, 53, 0, 23, 1}, {0, 23, 54, 11, 7}};
        int [][] matrix3 = { { 0, -1, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { -1, 0, 0, 0, 0, 0 }, { -1, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 } };
        addTo10(matrix2);
        setHints(matrix);
        setHints(matrix3);

    }


}



