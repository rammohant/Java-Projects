/****************************************************************************************************
 * Flight Analysis
 ****************************************************************************************************
 * A program that will read textfiles of all Flights and Flights to be removed,
 * convert them to ArrayLists, and output a file containing the final ArrayList.
 * The program can also determine the best fare and best fare per mile from the final ArrayList.
 * ___________________________________________________________________________________________________
 * @author Tara Ram Mohan
 * @since 2019-11-25
 * CMSC 255 Section 3
 ****************************************************************************************************/

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class FlightAnalysis {
   /**********************************************************************************************
    * @throws FileNotFoundException if file exists but is not found in folder
    **********************************************************************************************/
    public static void main(String[] args) throws FileNotFoundException {

        /********************************************************************************
         * Construct scanner for user input
         * ****************************************************************************/
        Scanner input = new Scanner(System.in);

        /********************************************************************************
         * Construct string variable with inputFileName and File object for input file
         * ****************************************************************************/
        String inputFileName = "null";
        File inputFile = new File(inputFileName);

        /*********************************************************************************************
         * Construct string variable with outputFileName and File object for output file
         * *******************************************************************************************/
        String outputFileName = "null";
        File outputFile = new File(outputFileName);

        /*********************************************************************************
         * Declare and initalize boolean done variable for loop and scanner to read file
         * *******************************************************************************/
        boolean done = false;
        Scanner in = null;

        /******************************************************************************
         * Do while loop that continues until file that exists is entered
         * ****************************************************************************/
        do {
            /******************************************************************************
             * Try catch to check if file exists
             * ****************************************************************************/
            try {
                /******************************************************************************
                 * If then statement to check if files come in at command line
                 * ****************************************************************************/
                if (args.length > 0) {
                    System.out.println ("File exists");
                    inputFile = new File(args[0]);
                    outputFileName = args[1];
                    outputFile = new File(outputFileName);
                } else {
                    System.out.println("Enter input file:");
                    inputFileName = input.nextLine();
                    inputFile = new File(inputFileName);
                    System.out.println("Enter output file:");
                    outputFileName = input.nextLine();
                    outputFile = new File(outputFileName);
                }
                in = new Scanner(inputFile);
                done = true;

            /***************************************************************************************
             * Catch FileNotFoundExcpetion if file is inputted at command line but does not exist
             * *************************************************************************************/
            } catch (FileNotFoundException x) {
                System.out.println("File not valid, re-enter proper filename:");
                outputFileName = input.nextLine();
            }
        } while (!done);

        /**************************************************************************************
         * Create ArrayList of all Flights and add all in ArrayList using parseData() method
         * ************************************************************************************/
        ArrayList<Flight> allFlights = new ArrayList<Flight>();
        allFlights.addAll(parseData(inputFile));

        /************************************************************************************
         * Call parseData using try-catch if there is problem with opening the text file
         * *********************************************************************************/
        try {
            parseData(inputFile);
            System.out.println("Input file correct");
        }
        catch (FileNotFoundException ex){
            System.out.println("Incorrect input filename");
        }

        /*********************************************************************************************************************
         * Create File and ArrayList of Flights that must be removed. Call parseData method to convert File to ArrayList
         * ******************************************************************************************************************/
        File removeDataFile = new File("dataToRemove.txt");
        ArrayList<Flight> removeItems = new ArrayList <Flight>();
        removeItems.addAll(parseData(removeDataFile));

        /*********************************************************************************************************************
         * Call removeData method that will remove removeItems ArrayList from allFlights ArrayList
         * ******************************************************************************************************************/
        removeData(allFlights, removeItems);

        /*********************************************************************************************************************
         * Call calcBestFare and calcBestFarePerMile method to determine cheapest flight and cheapest dollar per mile flight
         * ******************************************************************************************************************/
        calcBestFare(allFlights);
        calcBestFarePerMile(allFlights);

        /*********************************************************************************************************************
         * Call writeOutData method to output allFlights ArrayList into outputFile, but use try-catch to catch exception
         * @throws Exception if there is an issue with the writing to the text file
         * ******************************************************************************************************************/
        try {
            writeOutData(allFlights, outputFile);
            System.out.print ("Output file correct");
        } catch (Exception x) {
            System.out.print("Incorrect output filename");
        }

        /*********************************************************************************************************************
         * Close Scanner and PrintWriter objects
         * ******************************************************************************************************************/
        input.close();
    }

    /**********************************************************************************************
     * Method that converts input files to ArrayList
     * @param inputFile from command line or user input
     * @throws FileNotFoundException if file exists but is not found in folder when using scanner
     * @throws NumberFormatException if issue converting String to double/int type
     * @return updated ArrayList
     **********************************************************************************************/
    public static ArrayList<Flight> parseData(File inputFile) throws FileNotFoundException, NumberFormatException {
        int z = 0;

        /********************************************************************************
         * Construct Scanner object, temp ArrayList object, and temp Flight object
         * ****************************************************************************/
        Scanner in = new Scanner(inputFile);
        ArrayList<Flight> arrayList = new ArrayList <Flight>();
        Flight flightTemp = new Flight();

        /********************************************************************************
         * While loop to read inputFile
         * ****************************************************************************/
        in.nextLine();
        while (in.hasNextLine()) {
            z++;
            String line = in.nextLine();
            String[] temp = line.split(";");

            /********************************************************************************************************
             * Declare variables and initialize correct index of temp Array to corresponding variables
             * ******************************************************************************************************/
            String year = temp[0];
            String startCity = temp[1];
            String endCity = temp[2];
            double price;
            int distance;

            /********************************************************************************************************
             * Try to convert String input from temp String Array into double value, and catch NumberFormat error
             * ******************************************************************************************************/
            try {
                price = Double.parseDouble(temp[3]);
            } catch (NumberFormatException x){
                temp[3] = "0.0";
                price = Double.parseDouble(temp[3]);
            }

            /********************************************************************************************************
             * Try to convert String input from temp String Array into int value, and catch NumberFormat error
             * ******************************************************************************************************/
            try {
                distance = Integer.parseInt(temp[4]);
            } catch (NumberFormatException x) {
                temp[4] = "0";
                distance = Integer.parseInt(temp[4]);
            }

            /********************************************************************************************************
             * Error Handling: if price or distance is negative, set to 0
             * ******************************************************************************************************/
            if ((price < 0)) {
                price = 0;
            }

            if ((distance < 0)) {
                distance = 0;
            }

            /********************************************************************************************************
             * Set Flight object attributes using variables and add to arrayList
             * ******************************************************************************************************/
            flightTemp = new Flight(year,startCity,endCity,price,distance);
            arrayList.add(flightTemp);
        }
        return arrayList;
    }


    /********************************************************************************************************
     * Method removes objects in removeData from allFlights
     * @param allFlights ArrayList containing all Flight objects
     * @param removeItems ArrayList containing all Flight objects from DataToRemove file
     * ******************************************************************************************************/
    public static void removeData(ArrayList<Flight> allFlights, ArrayList<Flight> removeItems) {

        /********************************************************************************************************
         * Using for loop, search if each object in removeItems equals any object in allFlights ArrayList
         * ******************************************************************************************************/
        for (int i = 0; i < removeItems.size(); i++) {
            for (int j = 0; j < allFlights.size(); j++) {
            Flight removeFlightsI = removeItems.get(i);
            Flight allFlightsJ = allFlights.get(j);

                /********************************************************************************************************
                 * Remove any repeated objects between both ArrayLists
                 * ******************************************************************************************************/
                if (removeFlightsI.equals(allFlightsJ)) {
                    allFlights.remove(j);
                }
            }
        }
    }

    /********************************************************************************************************
     * @param allFlights updated ArrayList to not include Flight objects in removeItems
     * @return Flight object with the cheapest price
     * ******************************************************************************************************/
    public static Flight calcBestFare(ArrayList<Flight> allFlights) {

        Flight bestFare = new Flight();
        bestFare = allFlights.get(0);

        /********************************************************************************************************
         * Using for loop, search if price of each object is less than all other objects in allFlights ArrayList
         * ******************************************************************************************************/
        for ( int i = 0; i < allFlights.size(); i++) {

                Flight allFlightsI = allFlights.get(i);

                double price = allFlightsI.getPrice();

                /********************************************************************************************************
                 * Assign cheapest Flight to bestFare Flight object
                 * ******************************************************************************************************/
                if (allFlightsI.getPrice() < bestFare.getPrice()) {
                    bestFare = allFlights.get(i);
                }
            }
        return bestFare;
    }

    /********************************************************************************************************
     * @param allFlights updated ArrayList to not include Flight objects in removeItems
     * @return Flight object with the lowest price per distance value
     * ******************************************************************************************************/
    public static Flight calcBestFarePerMile(ArrayList<Flight> allFlights) {
        Flight bestFarePerMile = new Flight();
        bestFarePerMile = allFlights.get(0);

        /****************************************************************************************************************************
         * Using for loop, search if dollars per mile of each Flight is less than all that of other Flights in allFlights ArrayList
         * **************************************************************************************************************************/
        for ( int i = 0; i < allFlights.size(); i++ ) {

                Flight allFlightsI = allFlights.get(i);

                /********************************************************************************************************
                 * Assign cheapest Flight to bestFare Flight object
                 * ******************************************************************************************************/
                double pricePerDistanceI = (allFlightsI.getPrice() / allFlightsI.getDistance());
                double pricePerDistanceBest = (bestFarePerMile.getPrice() / bestFarePerMile.getDistance());


            if (pricePerDistanceI < pricePerDistanceBest){
                    bestFarePerMile  = allFlights.get(i);
                }
        } return bestFarePerMile;
    }

    public static void deleteData (ArrayList<Flight>  )
    /********************************************************************************************************
     * @param allFlights ArrayList of all Flight objects from allFlights
     * @param outputFile PrintWriter outputs into this file
     * ******************************************************************************************************/
    public static void writeOutData (ArrayList<Flight> allFlights, File outputFile) throws FileNotFoundException {

        /********************************************************************************************************
         * Output each Flight object from allFlights, but catch FileNotFoundException
         * ******************************************************************************************************/
        try {
            PrintWriter out = new PrintWriter(outputFile);
            for (int i = 0; i < allFlights.size(); i++) {
                Flight allFlightsI = allFlights.get(i);
                String print = allFlightsI.toString();
                out.println(print);
            }
            out.close();
        } catch (FileNotFoundException x) {
            System.out.print("Incorrect output filename");
        }
        }
    }



