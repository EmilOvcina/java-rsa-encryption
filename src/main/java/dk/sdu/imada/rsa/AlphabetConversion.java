package dk.sdu.imada.rsa;

import java.math.BigInteger;

public class AlphabetConversion {

	/**
	 * Converts a number to a string, according to the alphabet conversion rules of the assignment
	 * @param number
	 * @return
	 */
	public static String numberToString(BigInteger number) 
	{		
		BigInteger tmp = number;
		String result = "";
		for(int i = 4; i >= 0; i--)
		{
			long l = (long) (Math.pow(37, i));
			BigInteger next = tmp.divide(new BigInteger(String.valueOf(l)));
			result += CharacterConversion.numberToChar(next);
			tmp = tmp.subtract(next.multiply(new BigInteger(String.valueOf(l))));
		}
				
		return result;
	}


	/**
	 * Convert a string of length 5 to a BigInteger number
	 * @param string The string to convert
	 * @return The converted number
	 */
	public static BigInteger stringToNumber(String string) {
		if(string.length() > 5)
			throw new IllegalArgumentException("String is too long");
		if(string.length() < 5)
			for(int i = string.length(); i <= 5; i++)
				string += " ";
		
		string = flipString(string);
		
		long nlong = 0;
		
		for(int i = 0; i < 5; i++)
		{
			String es = String.valueOf((int)(Math.pow(37, i)));
			BigInteger first = CharacterConversion.charToNumber(string.charAt(i));
			BigInteger second = new BigInteger(es);
			nlong += (first.multiply(second)).longValue();
		}
		BigInteger result = new BigInteger(String.valueOf(nlong));
		
		return result;
	}

	private static String flipString(String s)
	{
		String result = "";
		for(int i = s.length()-1; i >= 0; i--) 
			result += s.charAt(i);
		return result;
	}

}
