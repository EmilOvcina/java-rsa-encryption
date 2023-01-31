package dk.sdu.imada.rsa;

import java.math.BigInteger;

public class CharacterConversion {

    /**
     * Converts a char to a BigInteger according to this alphabet conversion:
     *  0-9 should return their value  0->0, 5->5, 9->9
     *  a should map to 10, b to 11, c to 12 etc. up to z->35
     *  Space should map to 36
     * If a char not in the alphabet [ a-z0-0] is input, an IllegalArgumentException must be thrown
     * @param c Input char
     * @return BigInteger value for the character conversion
     */
    public static BigInteger charToNumber(char c) 
    {
        if (!Character.toString(c).matches("[ a-z0-9]")) {
            throw new IllegalArgumentException("Invalid input: '" + c + "' - input must be a-z, 0-9 or space");
        }
        if(c == ' ')
            return BigInteger.valueOf(36);
        int result = Character.getNumericValue(c);
        return BigInteger.valueOf(result);
    }

    /**
     * Converts a number in the interval [0:36] to the corresponding char
     * The conversion should be the opposite as in the method above.
     * If a number not in the interval specified above is input, an IllegalArgumentException must be thrown
     * @param number The number to convert
     * @return The converted char
     */
    public static char numberToChar(BigInteger number) {
        if(number == null || number.intValue() < 0 || number.intValue() > 36) {
            throw new IllegalArgumentException("Input must be in a BigInteger in the interval [0:36]");
        }
        int n = number.intValue();
        String t = "0123456789abcdefghijklmnopqrstuvwxyz ";
        return t.charAt(n);
    }

}
