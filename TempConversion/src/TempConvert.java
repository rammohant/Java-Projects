/****************************************************************************************************
 * Temperature Conversions
 * **************************************************************************************************
 * Converts temperature from one scale from Fahrenheit, Celsius, or Kelvin to another
 * Tara Ram Mohan
 * 10/10/19
 * CMSC 255 Section 3
 ****************************************************************************************************/
import java.util.Scanner;

public class TempConvert {

    public static Object[] searchDistance(int [][] distance, String[] cities, boolean isOver, int distanceInt, String inputCity) {
        String key = inputCity;
        int rowCity;
        for (int i = 0; i < cities.length; i++ )
            if (key == cities[i])
                rowCity = i;
            else rowCity = 0;

        String[] finalCity;
        if (isOver) {
            for (int row = 0; row < distance.length; row++) {
                for (int column = 0; column < distance[row].length; column++) {
                    if ((!(distance[row][column] == distance[1][column]))
                            && (!(distance[row][column] == distance[2][column]))
                            && (!(distance[row][column] == distance[3][column]))
                            && (distance[row][column] >= distanceInt)) {
                        finalCity[column] = cities[column];
                    }
                }
            }
        } else if (isOver = false) {
            for (int row = 0; row < distance.length; row++) {
                for (int column = 0; column < distance[row].length; column++) {
                    if ((!(distance[row][column] == distance[1][column]))
                            && (!(distance[row][column] == distance[2][column]))
                            && (!(distance[row][column] == distance[3][column]))
                            && (distance[row][column] < distanceInt)) {
                        finalCity[column] = cities[column];
                    }
                }
            }
        }
        System.out.print(finalCity);
    }
/***************************************************************
* Introduce main method
***************************************************************/
    public static void main(String[] args) {
        processData();
    }

/**************************************************
 * Introduce processData() method
 ***************************************************/
    public static void processData() {

    /*************************************************************
     * Introduce Scanner and declare quit var for do while loop
     *************************************************************/
        Scanner input = new Scanner(System.in);
        String quit;

    /****************************************************************
     * Start do while loop that will control if program continues
     ******************************************************************/
        do {

        /**************************************************
         * User input and store into variables
         ***************************************************/
            System.out.println("Enter the temperature to convert:");
            double tempInitial = input.nextDouble();

            System.out.println("Enter the current scale of the temperature:");
            String scaleTemp = input.next();
            String scale = scaleTemp.toLowerCase();

            System.out.println("Enter the scale you want to convert the temperature to:");
            String scaleFinalTemp = input.next();
            String scaleFinal = scaleFinalTemp.toLowerCase();

        /*******************************************************************************************************
         * If inputs are valid, call appropriate method and print out final statement of converted temperature
         *******************************************************************************************************/
            if (checkValidity(tempInitial, scale)) {

                if (scale.contains("fahr")) {

                    if (scaleFinal.contains("cel")) {
                        double result = convertFahtoCel(tempInitial);
                        System.out.printf("%.2f degrees %s is ", tempInitial, scaleTemp);
                        System.out.printf("%.2f degrees %s%n", result, scaleFinalTemp);
                    }
                    if (scaleFinal.contains("kel")) {
                        double result = convertFahtoKel(tempInitial);
                        System.out.printf("%.2f degrees %s is ", tempInitial, scaleTemp);
                        System.out.printf("%.2f degrees %s%n", result, scaleFinalTemp);
                    }
                    if (scaleFinal.contains("fahr")) {
                        System.out.printf("%.2f degrees %s is ", tempInitial, scaleTemp);
                        System.out.printf("%.2f degrees %s%n", tempInitial, scaleFinalTemp);
                    }
                }

                if (scale.contains("cel")) {

                    if (scaleFinal.contains("fahr")) {
                        double result = convertCeltoFah(tempInitial);
                        System.out.printf("%.2f degrees %s is ", tempInitial, scaleTemp);
                        System.out.printf("%.2f degrees %s%n", result, scaleFinalTemp);
                    }
                    if (scaleFinal.contains("kel")) {
                        double result = convertCeltoKel(tempInitial);
                        System.out.printf("%.2f degrees %s is ", tempInitial, scaleTemp);
                        System.out.printf("%.2f degrees %s%n", result, scaleFinalTemp);
                    }
                    if (scaleFinal.contains("cel")) {
                        System.out.printf("%.2f degrees %s is ", tempInitial, scaleTemp);
                        System.out.printf("%.2f degrees %s%n", tempInitial, scaleFinalTemp);
                    }
                }

                if (scale.contains("kel")) {

                    if (scaleFinal.contains("fahr")) {
                        double result = convertKeltoFah(tempInitial);
                        System.out.printf("%.2f degrees %s is ", tempInitial, scaleTemp);
                        System.out.printf("%.2f degrees %s%n", result, scaleFinalTemp);
                    }
                    if (scaleFinal.contains("cel")) {
                        double result = convertKeltoCel(tempInitial);
                        System.out.printf("%.2f degrees %s is ", tempInitial, scaleTemp);
                        System.out.printf("%.2f degrees %s%n", result, scaleFinalTemp);
                    }
                    if (scaleFinal.contains("kel")) {
                        System.out.printf("%.2f degrees %s is ", tempInitial, scaleTemp);
                        System.out.printf("%.2f degrees %s%n", tempInitial, scaleFinalTemp);
                    }
                }

            } else {
                System.out.println(tempInitial + " degrees " + scaleTemp + " is not a valid temperature");
            }

        /***********************************************************************************
         * User input of whether user wants to continue
         ***********************************************************************************/
            System.out.println("Would you like to convert another temperature? Enter quit to exit.");
            String fake = input.nextLine();
            quit = input.nextLine().trim();

        } while (!quit.toLowerCase().equals("quit"));
    }

/****************************************************************************
 * Determine if temperature input is valid based on initial temperature scale
 ****************************************************************************/
    public static boolean checkValidity(double tempInitial, String scale) {

    /****************************************************************************
     * Create constants for temperature bounds
     ****************************************************************************/
        final double fahrInBound = -459.40;
        final double fahrOutBound = 1000.0;

        final double celInBound = -273;
        final double celOutBound = 1000;

        final double kelInBound = 0;
        final double kelOutBound = 1000;

    /****************************************************************************
     * Switch statement to check validity of user temperature input
     ****************************************************************************/
        boolean valid;

        switch (scale) {

            case "Fahrenheit":
            case "fahrenheit":

                if ((tempInitial <= fahrOutBound) && (tempInitial >= fahrInBound)) {
                return true; }


            case "Celsius":
            case "celsius":

                if ((tempInitial >= celInBound) && (tempInitial <= celOutBound)) {
                    return true; }

            case "Kelvin":
            case "kelvin":

                if ((tempInitial >= kelInBound) && (tempInitial <= kelOutBound)) {
                    return true; }

            default: return false;

        }
    }

/****************************************************************************
 * Temperature calculations from Fahrenheit to Celsius
 ****************************************************************************/
    public static double convertFahtoCel(double tempInitial) {
        double result = (tempInitial - 32) /(9.0 / 5);
        return result;
        }

/****************************************************************************
 * Temperature calculations from Fahrenheit to Kelvin
 ****************************************************************************/
    public static double convertFahtoKel(double tempInitial) {
        double temp = (tempInitial - 32) / (9.0/5);
        double result = (temp + 273);
        return result;
        }

/****************************************************************************
 * Temperature calculations from Celsius to Fahrenheit
 ****************************************************************************/
    public static double convertCeltoFah(double tempInitial) {
        double result = (tempInitial * 9.0 / 5) + 32;
        return result;
        }

/****************************************************************************
 * Temperature calculations from Celsius to Kelvin
 ****************************************************************************/
    public static double convertCeltoKel (double tempInitial) {
        double result = (tempInitial + 273);
        return result;
        }

/****************************************************************************
 * Temperature calculations from Kelvin to Celsius
 ****************************************************************************/
    public static double convertKeltoCel(double tempInitial) {
        double result = (tempInitial - 273);
        return result;
        }

/****************************************************************************
 * Temperature calculations from Kelvin to Fahrenheit
 ****************************************************************************/
    public static double convertKeltoFah(double tempInitial) {
        double temp = (tempInitial - 273);
        double result = (temp * (9.0/5)) + 32;
        return result;
        }
}
