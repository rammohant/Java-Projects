package cmsc256;
/**
 *  A class that represents a person object
 *  a formatted string representation of the instance variables.
 *
 * @author modified 1/22/2020 by Tara Ram Mohan
 */
public class Person {
    private int id;
    private String phoneNumber;
    private String email;
    private static int RecordNumber;
    private Name name;
    private Address homeAddress;

    /**
     *  Increments RecordNumber by 1 each time a Person object is created
     */
    {
        RecordNumber += 1;
    }

    /**
     *  Sets default values for the object.
     *  Default values for phone number are "None given".
     *  Default Name() and Address() objects created
     */
    public Person() {
        this.phoneNumber = "None given";
        this.email = "None given";
        this.name = new Name();
        this.homeAddress = new Address();
        id = RecordNumber;
    }

    /**
     *  Sets up this Person object with the specified data.
     *  @param	first	first name for Name object
     *  @param	middle	middle name for Name object
     *  @param	last	last name for Name object
     *  @param	homeAddress			home address of type Address object
     *  @param	phoneNumber			standard 10-digit phone number
     *  @param	email               email
     */

    //WWJu ar eupi

    public Person(String first, String middle, String last, Address homeAddress,
                  String phoneNumber, String email) {

        this.name = new Name(first,middle,last);
        this.homeAddress = homeAddress;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.id = RecordNumber;

    }

    /**
     *	Returns String of name object
     */
    public String getName(){
        return this.name.toString();
    }

    /**
     *	Returns this Person object as a string with name, address, phone number, email address, and ID number
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + ": \n" +
            "---------------------------------------\n" +
            this.name +
            "\n---------------------------------------\n" +
            "Home Address: " + this.homeAddress +
            "\nPhone Number: " + this.phoneNumber +
            "\nEmail Address: " + this.email +
            "\nID: " + this.id;
    }
}
