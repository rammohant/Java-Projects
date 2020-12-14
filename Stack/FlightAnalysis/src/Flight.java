/****************************************************************************************************
 * Flight Analysis
 ****************************************************************************************************
 * Flight object for Flight Analysis program
 * ___________________________________________________________________________________________________
 * @author Tara Ram Mohan
 * @since 2019-11-25
 * CMSC 255 Section 3
 ****************************************************************************************************/
public class Flight {

    /********************************************************************************************************
     * Declare all private variables for Flight attributes
     * ******************************************************************************************************/
    private String year;
    private String startCity;
    private String endCity;
    private double price;
    private int distance;

    /********************************************************************************************************
     * Non-arg constructor
     * ******************************************************************************************************/
    public Flight() {
        year = "1970";
        startCity = "Boston";
        endCity = "Richmond";
        price = 40.0;
        distance = 900;
    }

    /********************************************************************************************************
     * Parameterized constructor
     * ******************************************************************************************************/
    public Flight(String year, String startCity, String endCity, double price, int distance) {
        this.year = year;
        this.startCity = startCity;
        this.endCity = endCity;
        this.price = price;
        this.distance = distance;
    }

    /********************************************************************************************************
     * Mutator methods for Flight attributes
     * ******************************************************************************************************/
    public void setYear(String year) {
        this.year = year;
    }

    public void setStartCity(String startCity) {
        this.startCity = startCity;
    }

    public void setEndCity(String endCity) {
        this.endCity = endCity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    /********************************************************************************************************
     * Accessor methods for Flight attributes
     * Return attributes
     * ******************************************************************************************************/
    public String getYear() { return year; }

    public String getStartCity() {
        return startCity;
    }

    public String getEndCity() {
        return endCity;
    }

    public double getPrice() {
        return price;
    }

    public int getDistance() {
        return distance;
    }

    /********************************************************************************************************
     * ToString method
     * @return String containing all attributes of each Flight object
     * ******************************************************************************************************/
    public String toString() {
        return (year + " " + startCity + " " + endCity + " " + price + " " + distance);
    }

    /********************************************************************************************************
     * @param obj should be Flight object from allFlights ArrayList
     * @return true or false depending on whether obj is equal to other obj
     * ******************************************************************************************************/
    public boolean equals (Object obj) {

        if (obj instanceof Flight) {

            Flight flight1 = (Flight) obj;

            return (this.year.equals(flight1.getYear()))
                    && (this.startCity.equals(flight1.getStartCity()))
                    && (this.endCity.equals(flight1.getEndCity()))
                    && (this.price == flight1.getPrice())
                    && (this.distance == flight1.getDistance());
        } else {
            return false;
        }
    }
}
