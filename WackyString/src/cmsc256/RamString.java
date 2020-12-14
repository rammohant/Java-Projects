package cmsc256;
/**
 * Tara Ram Mohan
 * CMSC 256 Project 2: Implement WackyString Interface
 * Last updated 7 February 2020
 */

public class RamString implements WackyStringInterface{
    private String string;

    /**
     * Default RamString constructor
     *
     * Sets the value of the current string to "Rodney, the Ram"
     */
    public RamString() {
        this.string =  "Rodney, the Ram"; //String should never be allowed to be null
    }

    /**
     * Parameterized RamString constructor
     *
     * Sets the value of the current string
     * @param string The value to be set
     * @throws  IllegalArgumentException if parameter is null
     */
    public RamString(String string) {
        if (string == null) {
            throw new IllegalArgumentException("Cannot input null value");
        } else {
            this.string = string;
        }
    }

    /**
     * Sets the value of the current string.
     *
     * @param string The value to be set
     * @throws  IllegalArgumentException if parameter is null
     */
    @Override
    public void setWackyString(String string) throws IllegalArgumentException{
        if (string == null) {
            throw new IllegalArgumentException("Cannot input null value");
        } else {
            this.string = string;
        }
    }

    /**
     * Returns the current string
     *
     * @return Current string
     */
    @Override
    public String getWackyString() {
        return this.string;
    }

    /**
     * Returns a string that consists of all and only the characters
     * in every third positions (i.e., third, sixth, and so on) in
     * the current string, in the same order and with the same case as
     * in the current string. The first character in the string is
     * considered to be in Position 1.
     *
     * @return String made of characters in every third positions in the
     * current string
     */
    @Override
    public String getEveryThirdCharacter() {
        String thirdCharString = "";
        for(int i = 2; i < string.length(); i += 3) {
            char aChar = string.charAt(i);
            thirdCharString += aChar; //can I do this?
        }
        return thirdCharString;
    }

    /**
     * Returns a string that consists of all and only the characters
     * in either the odd positions (i.e., first, third, fifth, and so on)
     * or in the even positions (i.e., second, fourth, sixth, and so on) of
     * the current string, in the same order and with the same case as in
     * the current string. The first character in the string is
     * considered to be in Position 1.
     * @param  evenOrOdd String to determine if odd or even characters are to be returned.
     * @return String made of characters in odd positions in the
     * current string
     * @throws  IllegalArgumentException if parameter is other than "odd" or "even"
     */
    @Override
    public String getEvenOrOddCharacters(String evenOrOdd) throws IllegalArgumentException{
        String evenOrOddLC = evenOrOdd.toLowerCase().replaceAll("\\s", "");
        String evenString = "";
        String oddString = "";

        if (evenOrOddLC.equals("even")) {
            for (int i = 1; i < string.length(); i += 2) {
                char aChar = string.charAt(i);
            evenString += aChar; //can I do this?
        }
            return evenString;
        } else if (evenOrOddLC.equals("odd")) {
            for (int i = 0; i < string.length(); i += 2) {
                char aChar = string.charAt(i);
                oddString += aChar; //can I do this?
            }
            return oddString;
        } else {
            throw new IllegalArgumentException("Incorrect input, must even or odd");
        }

    }

    /**
     * Returns the number of characters that are digits in the current string
     *  if two (and no more than two) digits appear side by side.
     *
     * @return Number of double-digits in the current string
     */
    @Override
    public int countDoubleDigits() {
        int doubleDigit = 0;

        if ((string == null) || (string.length() == 0)) {
            return doubleDigit = 0;
        }

        for (int i = 1; i < string.length(); i++) {
            if (Character.isDigit(string.charAt(i)) && Character.isDigit(string.charAt(i - 1))) {
                if ((((i + 1) < string.length()) && ((Character.isDigit(string.charAt(i + 1)))))) {
                    doubleDigit += 0;
                    i+=2;
                } else {
                    doubleDigit += 1;
                }
            }
        }
        return doubleDigit;
    }

    /**
     * Returns true if the current string contains one or more characters
     * before an '@' character, followed by either "vcu.edu" or "mymail.vcu.edu"
     * For example, RodneyTheRam@vcu.edu a valid vcu email address
     * and RamFan@gmail.com is not.
     * Returns false otherwise.
     *
     * @return true if current string is formated as valid VCU email address
     */
    @Override
    public boolean isValidVCUEmail() {
        //String string2 = string.replaceAll("\\s","");
        int atIndex = string.indexOf("@");
        int length = string.length();
        String substring = string.substring((atIndex+1),(length)).toLowerCase();
        if ((atIndex > 0) &&
            ((substring.equals("vcu.edu")) || (substring.equals("mymail.vcu.edu")))) {
            return true;
        }
        return false;
    }

    /**
     * Replace all occurence of a single zero (0) with the string "GoRams"
     * in the current string,
     * and all occurence of a double zero (00) with the string "CS@VCU"
     */
    @Override
    public void ramifyString() {
        String temp = "";
        for (int i = 0; i < string.length(); i++) {
           if (((string.charAt(i)) == '0') &&
                    ((i+1) < string.length()) &&
                    ((string.charAt(i+1)) == '0') &&
                    ((i+2) < string.length()) &&
                        ((string.charAt(i+2)) == '0'))  {
                    temp += "000";
                    i+=2;
            }
            else if (((string.charAt(i)) == '0') &&
                    ((i+1) < string.length()) &&
                    ((string.charAt(i+1)) == '0')) {
                temp += "CS@VCU";
                i++;
            } else if (string.charAt(i)=='0') {
                temp += "GoRams";
            } else {
                temp += string.charAt(i);
            }
        }  string = temp;

    }

    /**
     * Replace the _individual_ digits in the current string, between
     * startPosition and endPosition (included), with the corresponding
     * Roman numeral symbol(s). The first character in the string is
     * considered to be in Position 1. Digits are converted individually,
     * even if contiguous, and digit "0" is not converted (e.g., 460 is
     * converted to IVVI0). In case you are not familiar with Roman
     * numerals, see https://en.wikipedia.org/wiki/Roman_numerals
     *
     * @param startPosition
     *            Position of the first character to consider
     * @param endPosition
     *            Position of the last character to consider
     * @throws MyIndexOutOfBoundsException
     *            If either "startPosition" or "endPosition" are out of
     *            bounds (i.e., either less than 1 or greater then the
     *            length of the string)
     * @throws IllegalArgumentException
     *            If "startPosition" greater than "endPosition" (but both are
     *            within bounds)
     */
    @Override
    public void convertDigitsToRomanNumeralsInSubstring(int startPosition, int endPosition) throws MyIndexOutOfBoundsException, IllegalArgumentException {
        String ramString = "";

        if (startPosition < 1 || endPosition > string.length() || endPosition < 1 || startPosition > string.length()) {
            throw new MyIndexOutOfBoundsException("Either startPosition or endPosition are out of bounds (i.e., either less than 1 or greater then the length of the string");

        } else if (startPosition > endPosition) {
            throw new IllegalArgumentException("Your start position is greater than your end position.");

        } else {
            for (int i = 0; i < string.length(); i++) {
                char temp = string.charAt(i);
                if ((i >= (startPosition-1)) && (i <= (endPosition - 1))) {
                    switch (temp) {
                        case ('0'):
                            ramString += "0";
                            break;
                        case ('1'):
                            ramString += "I";
                            break;
                        case ('2'):
                            ramString += "II";
                            break;
                        case ('3'):
                            ramString += "III";
                            break;
                        case ('4'):
                            ramString += "IV";
                            break;
                        case ('5'):
                            ramString += "V";
                            break;
                        case ('6'):
                            ramString += "VI";
                            break;
                        case ('7'):
                            ramString += "VII";
                            break;
                        case ('8'):
                            ramString += "VIII";
                            break;
                        case ('9'):
                            ramString += "IX";
                            break;
                        default:
                            ramString += temp;
                    }
                } else {
                    ramString += temp;
                }
            }
            string = ramString;
        }
    }
}
