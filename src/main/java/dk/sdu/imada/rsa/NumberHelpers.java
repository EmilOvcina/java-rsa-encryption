package dk.sdu.imada.rsa;

import java.math.BigInteger;

public class NumberHelpers {

	private static final BigInteger TWO = new BigInteger("2");

	/**
	 * Extended euclidean algorithm
	 * @param a
	 * @param b
	 * @return [r,s,t] where r = gcd(a,b) and sa + tb = r
	 */
	public static BigInteger[] extendedEuclideanAlgorithm(BigInteger a, BigInteger b) {
		BigInteger a0 = a;
		BigInteger b0 = b;
		BigInteger t0 = BigInteger.ZERO;
		BigInteger t = BigInteger.ONE;
		BigInteger s0 = BigInteger.ONE;
		BigInteger s = BigInteger.ZERO;
		
		BigInteger q = a0.divide(b0);
		BigInteger r = a0.subtract(q.multiply(b0));
		
		while(r.compareTo(BigInteger.ZERO) > 0) {
			BigInteger temp = t0.subtract(q.multiply(t));
			t0 = t;
			t = temp;
			
			temp = s0.subtract(q.multiply(s));
			
			s0 = s;
			s = temp;
			
			a0 = b0;
			b0 = r;
			
			q = a0.divide(b0);
			
			r = a0.subtract(q.multiply(b0));
		}
		r = b0;
		
		return new BigInteger[] {r,s,t};
	}

	/**
	 * Method handling modular exponentiation efficiently
	 * @param base
	 * @param pow
	 * @param modulus
	 * @return BigDecimal with the value of base^pow mod modulus
	 */
	public static BigInteger recursiveModularExponentiation(BigInteger base, BigInteger pow, BigInteger modulus) 
	{	
		if(pow.equals(BigInteger.ZERO)) 
			return BigInteger.ONE;
		
		if(pow.equals(BigInteger.ONE))
			return base;
		
		if(pow.mod(TWO).compareTo(BigInteger.ZERO) == 0) 
		{
			BigInteger temp = recursiveModularExponentiation(base, pow.divide(TWO), modulus);
			return (temp.multiply(temp)).mod(modulus);
		} else 
		{		
			BigInteger temp = recursiveModularExponentiation(base, pow.divideAndRemainder(TWO)[0], modulus);
			return (base.multiply(temp.multiply(temp))).mod(modulus);
		}
	}
}
