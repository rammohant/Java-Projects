/****************************************************************************
 * Ice Cream Shop
 ****************************************************************************
 * A system to keep track of customer’s icecream purchases
 * _____________________________________________________
 * Tara Ram Mohan
 * 29 October 2019
 * CMSC 255 Section 3
 ****************************************************************************/
import java.util.ArrayList;

public class Customer {

    /*****************************************************************************
     * Declare attributes of Customer object
     ****************************************************************************/
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private ArrayList<IceCream> iceCream;

    /*****************************************************************************
     * Default non-arg constructor
     ****************************************************************************/
    public Customer() {
        iceCream = new ArrayList<IceCream>();
    }

    /*****************************************************************************
     * Parameterized constructor
     ****************************************************************************/
    public Customer(String lastName, String firstName, String phone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        iceCream = new ArrayList<IceCream>();
    }

    /*****************************************************************************
     * Mutator methods
     ****************************************************************************/
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /*****************************************************************************
     * Accessor methods
     ****************************************************************************/
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    /*****************************************************************************
     * Accessor method for IceCream array list
     ****************************************************************************/
    public ArrayList<IceCream> getIceCream() {
        return iceCream;
    }

    /*****************************************************************************
     * Add icecream to customer order
     ****************************************************************************/
    public void orderIceCream(IceCream IceCream) {
        iceCream.add(IceCream);
    }

    /*****************************************************************************
     * Accessor method for numIceCream
     ****************************************************************************/
    public int getNumIceCream() {
        return iceCream.size();
    }

    /*****************************************************************************
     * ToString method to return each Customer object in correct format
     ****************************************************************************/
    public String toString() {
        StringBuilder result = new StringBuilder();
        String temp = String.format(this.firstName + " " + this.lastName + "\n" + this.phone + "\n" + this.email + "\nIce Cream Order:\n");
        result.append(temp);
        for (IceCream n: iceCream) {
            result.append(n);
        }
        return result.toString();
    }
}
