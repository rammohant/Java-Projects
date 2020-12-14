package cmsc256;
/**
 *  A class that represents a person object
 *  a formatted string representation of the instance variables.
 *
 * @author modified 1/22/2020 by Tara Ram Mohan
 */
public class Student extends Person{
    private String level;

    /**
     *  Sets default values for the object.
     *  Super class variables are called
     *  Default value for level are "None given".
     */
    public Student() {
        super();
        this.level = "Freshman";
    }

    /**
     *  Sets up this Student object with the specified data
     *  @param	level	Student grade level, cannot be null value and must be a valid level
     *                  or throws IllegalArgument Exception
     */
    public Student(String first, String middle, String last, Address homeAddress, String phoneNumber, String email, String level){

        super(first, middle, last, homeAddress, phoneNumber, email);

        if(level == null) {
            throw new IllegalArgumentException("The streetAddress cannot be null.");
        }
        if(!isValidLevel(level)) {
            throw new IllegalArgumentException("The state abbreviation is incorrect.");
        }
        this.level = level;
    }

    /**
     *  Determines if level is either Freshman, Sophomore, Junior, or Senior or throws error
     */
    private boolean isValidLevel(String level) {

        String levelLC = level.toLowerCase();
        boolean isValid = true;

        if (levelLC.equals("freshman") || levelLC.equals("sophomore") || levelLC.equals("junior") || levelLC.equals("senior")) {
            isValid = true;
        } else {
            isValid = false;
        } return isValid;
    }

    /**
     *	Returns level of this Student object with super toString() method invocation
     */
    @Override
    public String toString() {
        return super.toString() + "\nStudent Level: " + this.level + "\n" ;
    }
}

