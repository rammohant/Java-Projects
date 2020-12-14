package cmsc256;
/**
 *  A class that represents a person object
 *  a formatted string representation of the instance variables.
 *
 * @author modified 1/22/2020 by Tara Ram Mohan
 */
public class Faculty extends Employee {
    private String rank;

    /**
     * Sets default values for the object.
     * Super class variables are called
     * Default value for rank as "Instructor"
     */
    public Faculty() {
        super();
        this.rank = "Instructor";
    }

    /**
     * Sets up this Faculty object with the specified data
     *
     * @param    rank rank, cannot be null and must be valid
     */
    public Faculty(String first, String middle, String last, Address homeAddress,
                   String phoneNumber, String email, String office, int salary, int month, int dayOfMonth, int year, String rank) {

        super(first, middle, last, homeAddress, phoneNumber, email, office, salary, month, dayOfMonth, year);

        if (rank == null) {
            throw new IllegalArgumentException("The rank cannot be null.");
        }
        if (!(isValidRank(rank))) {
            throw new IllegalArgumentException("Rank is invalid.");
        }

        this.rank = rank;

    }

    /**
     * Determines if level is Adjunct, Instructor, Assistant Professor, or Professor or throws error
     */
    private boolean isValidRank(String rank) {
        String levelLC = rank.toLowerCase();
        boolean isValid = true;

        if (levelLC.equals("adjunct") || levelLC.equals("instructor") || levelLC.equals("assistant professor") || levelLC.equals("professor")) {
            isValid = true;
        } else {
            isValid = false;
        }
        return isValid;
    }

    /**
     * Returns rank of this Faculty object with super toString() method invocation
     */
    @Override
    public String toString() {
        return super.toString() + "\nRank: " + this.rank + "\n";
    }
}
