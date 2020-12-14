package cmsc256;

import java.time.LocalDate;

/**
 *  A class that represents a person object
 *  a formatted string representation of the instance variables.
 *
 * @author modified 1/22/2020 by Tara Ram Mohan
 */

public class Employee extends Person {
    private String office;
    private int salary;
    private LocalDate hireDate;

    /**
     * Sets default values for the object.
     * Super class variables are called
     * Default value for office, salary, and hireDate are "Unassigned", 0, and null respectively.
     */
    public Employee() {
        super();
        this.office = "Unassigned";
        this.salary = 0;
        this.hireDate = null;
    }

    /**
     * Sets up this Employee object with the specified data
     *
     * @param    office    office name, cannot be null
     * @param    salary    salary as int value
     * @param    month month of LocalDate object
     * @param    dayOfMonth    day of LocalDate object
     * @param    year    year of LocalDate object
     */
    public Employee(String first, String middle, String last, Address homeAddress,
                    String phoneNumber, String email, String office, int salary, int month, int dayOfMonth, int year) {

        super(first, middle, last, homeAddress, phoneNumber, email);

        if (office == null)
            throw new IllegalArgumentException("The office cannot be null.");

        if (salary < 0) {
            throw new IllegalArgumentException("The salary cannot be less than 0.");
        }
        this.office = office;
        this.salary = salary;
        this.hireDate = LocalDate.of(year, month, dayOfMonth);
    }

    /**
     * Returns office, salary, and date hired of this Employee object with super toString() method invocation
     */
    @Override
    public String toString() {
        if (!(hireDate == null)) {
            return super.toString() + "\nOffice: " + this.office + "\nSalary: $" + this.salary + "\nDate Hired: " +
                    this.hireDate.getMonthValue() + "/" + this.hireDate.getDayOfMonth() + "/" + this.hireDate.getYear();
        } else
            return super.toString() + "\nOffice: " + this.office + "\nSalary: $" + this.salary + "\nDate Hired: Not available.";

    }
}
