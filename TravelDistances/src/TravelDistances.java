import java.util.Scanner;
import java.util.Arrays;

/************************************************************************************************************
 * Travel Distances
 * **********************************************************************************************************
 * Search and print cities within array that are either below or above a specified distance from selected city
 * Tara Ram Mohan
 * 10/24/19
 * CMSC 255 Section 3
 *************************************************************************************************************/

public class TravelDistances {

/**************************************************
 * Introduce main method
 ***************************************************/
    public static void main(String[] args) {

        /**************************************************
         * Print out user input questions and store user input into declared variables
         ***************************************************/
        Scanner in = new Scanner(System.in);
        System.out.print("Type true if you would like to search cities above a certain distance or false if you would like to search cities below a certain distance: ");
        boolean isOverOrUnder = in.nextBoolean(); //true

        System.out.print("Enter the distance you would like to search: ");
        int distanceInput = in.nextInt(); //500

        System.out.print("Enter the city you would like to start from: ");
        String temp = in.next();
        String cityInput = temp.toUpperCase().trim();

        /*************************************************************************************************************
         * Convert args[0] String into an array using getCity() method
         **************************************************************************************************************/
        String[] citiesArray = getCity(args[0]);

        /*************************************************************************************************************
         * Convert args[1] String into an array using getDistance() method
         **************************************************************************************************************/
        int[][] distanceArrayInt = getDistance(args[1]); //{ {0, 900, 1500} ,

        /*****************************************************************************************************************
         * Call searchDistance() method, which will output final array with all viable cities in specified distance range
         *****************************************************************************************************************/
        String[] finalCity = searchDistance(distanceArrayInt, citiesArray, isOverOrUnder, distanceInput, cityInput);

        /*************************************************************************************************************
         * Print alphabetized array of cities in proper format
         **************************************************************************************************************/
        System.out.print("[" + finalCity[0]);
            for (int i = 1; i < finalCity.length; i++) {
                System.out.print(", " + finalCity[i]);
                }
            System.out.print("]");
            }

    /**************************************************
     * Introduce getCity() method
     ***************************************************/
    public static String[] getCity(String inputCityString) {

        /**************************************************
         * Convert string into 1D array
         ***************************************************/
        String[] cities = inputCityString.split(",");

        /**************************************************
         * Return 1D array to main method
         ***************************************************/
        return cities;
    }

    public static int [][] getDistance(String inputDistanceString) {

        /**************************************************
         * Convert string into 1D array
         ***************************************************/
        String[] distances = inputDistanceString.split("<>");

        int size = distances.length;

        String[][] distanceArrayString = new String[size][];

        /**************************************************
         * Convert 1D array into multidimensional array
         ***************************************************/
        int count = 0;
        for (String line : distances) {
            distanceArrayString[count] = line.split(",");
            ++count;
        }

        /**************************************************
         * Convert String type array into integer type array
         ***************************************************/
        int[][] distanceInt = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++)
                distanceInt[i][j] = Integer.parseInt(distanceArrayString[i][j]);
        }
        /**************************************************
         * Return 2D array to main method
         ***************************************************/
        return distanceInt;
    }

        public static String[] searchDistance(int [][] distanceInt, String [] cities, boolean isOver, int distanceInput, String cityInput) {

            /***************************************************************
             * Find which city array column contains specified input city
             *************************************************************/
            String[] finalCityTemp = new String[cities.length];
            int size = 0;

            /***************************************************************
             * Find which city array column contains specified input city
             *************************************************************/
            String key = cityInput;
            int rowCity = 0;

            for (int i = 0; i < cities.length; i++) {
                if (key.equals(cities[i])) {
                    rowCity = i;
                }
            }
            /**************************************************
             * If above specified distance:
             ***************************************************/
            if (isOver) {

                /**************************************************
                 * For loop to determine where index is above specified distance and not input city
                 ***************************************************/
                for (int column = 0; column < distanceInt.length; column++) {
                    if ((distanceInt[rowCity][column] >= distanceInput) && !(cities[column].equals(cityInput))) {
                        /**************************************************
                         * Store into finalCityNull array
                         ***************************************************/
                        finalCityTemp[size] = cities[column];
                        size ++;
                    }
                }
            }

            /**************************************************
             * If below specified distance
             ***************************************************/
            else {
                /**************************************************
                 * For loop to determine where index is below specified distance and not input city
                 ***************************************************/
                for (int column = 0; column < distanceInt.length; column++) {
//or not equal to zero
                    if ((distanceInt[rowCity][column] < distanceInput) && (distanceInt[rowCity][column] != 0) && !(cities[column].equals(cityInput))) {
                        /**************************************************
                         * Store into finalCityNull array
                         ***************************************************/
                        finalCityTemp[size] = cities[column];
                        size++;
                    }
                }
            }

            /*************************************************************************************************
             * Declare and initialize finalCity array as copy of finalCityNull array
             **************************************************************************************************/
            String[] finalCity;
            finalCity = Arrays.copyOf(finalCityTemp, size);

            /**************************************************
             * Alphabetize finalCity array
             ***************************************************/
            Arrays.sort(finalCity);

            /********************************************************
             * Return alphabetized array
             ********************************************************/
            return finalCity;
        }
    }
