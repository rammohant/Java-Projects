import java.lang.management.MemoryType;
import java.util.Arrays;

public class Array {

    public static void main(String[] args) {

        String[] tokens = args[0].split(",");
        System.out.println(Arrays.toString(tokens));
        System.out.println();

        String[] customerName = new String[7];

        for (int i = 0; i < tokens.length; i++)
            customerName[i] = tokens[i];

        customerName[5] = customerName[3];
        customerName[6] = customerName[4];

        String temp = "null";
        customerName[3] = temp;
        customerName[4] = temp;

        customerName[3] = "Rick";
        customerName[4] = "Jessica";

        for (String element : customerName) {
            System.out.println(element);
        }
        System.out.println();

        //Replace Rick and shift;

        String key = "Rick";

        for (int i = 0; i < customerName.length; i++) {
            if (customerName[i].equals(key)) {
                for (int n = 1; i < customerName.length - 2; i++) {
                    customerName[i] = customerName[i + 2];
                }
            }
        }

        for (int i = 0; i < customerName.length; i++) {
            if (customerName[i].equals(key)) {
                customerName[i] = customerName[i + 1]; }
            int l = customerName.length;
            customerName[l-1] = "null";
            customerName[l-2] = "null";
        }

        for (String element : customerName) {
            System.out.println(element);
        }
        System.out.println();

        //Reverse
        int p, k;
        for (p = 0; p < customerName.length / 2; p++) {
            String t = customerName[p];
            customerName[p] = customerName[customerName.length - p - 1];
            customerName[customerName.length - p - 1] = t;
        }

        for (String element : customerName) {
            System.out.println(element); }
            System.out.println();
        }
    }




