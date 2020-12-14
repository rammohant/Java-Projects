package cmsc256;

public class Test {
    public static void main(String[] args) throws IndexOutOfBoundsException, IllegalArgumentException {
        int startPosition = 1;
        int endPosition = 3;
        String string = "460";

        String ramString = "";
        if (startPosition > endPosition) {
            throw new IllegalArgumentException("Your start position is greater than your end position.");
        }
        try {
            for (int i = (startPosition - 1); i <= (endPosition - 1); i++) {
                char temp = string.charAt(i);
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
                        throw new IllegalArgumentException("Invalid input");
                }
            }
        } catch (MyIndexOutOfBoundsException ex) {
            throw ex;
        } catch (IllegalArgumentException ex) {
            throw ex;
        }
        string = ramString;
        System.out.println(string);
    }
    }

