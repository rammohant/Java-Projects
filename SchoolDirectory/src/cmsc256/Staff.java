package cmsc256;
/**
 *  A class that represents a person object
 *  a formatted string representation of the instance variables.
 *
 * @author modified 1/22/2020 by Tara Ram Mohan
 */
public class Staff extends Employee{
    private String title;

    /**
     *  Sets default values for the object.
     *  Super class variables are called
     *  Default value for title as "None given"
     */
    public Staff() {
        super();
        this.title = "None given";
    }
    /**
     *  Sets up this Staff object with the specified data
     *  @param	title    rank, cannot be null and must be valid
     */
    public Staff(String first, String middle, String last, Address homeAddress, String phoneNumber, String email, String office,
          int salary, int month, int dayOfMonth, int year, String title){

        super(first, middle, last, homeAddress, phoneNumber, email, office, salary, month, dayOfMonth, year);

        if(title == null)
            throw new IllegalArgumentException("The title cannot be null.");
        this.title = title;
    }

    /**
     *	Returns title of this Staff object with super toString() method invocation
     */
    @Override
    public String toString() {
        return super.toString() + "\nTitle: " + this.title + "\n";
        //are we meant to throw a null exception for title
    }
}
