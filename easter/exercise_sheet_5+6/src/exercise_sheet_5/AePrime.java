package exercise_sheet_5;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class AePrime implements Runnable {
	private Integer[] vector;
	static int numThreads = 0;
	
	
	public AePrime(Integer[] subVector){
		System.out.println("new thread, totalling " + ++numThreads + " AePrime threads");
		vector = subVector;
	}

	public static boolean checkPrime(Integer n) {
    	//BigInteger used because it allows for combining of division and modulo operations to one method.
    	BigInteger N = BigInteger.valueOf(n.intValue());
    	BigInteger[] divResult;
		BigInteger i = new BigInteger("2");
    	
    	do {
    		//"N.divideAndRemainder(i)" produces a vector of length 2: [N/i, N%i]
    		//reduces two operations to one, big efficiency saving for large n.
    	     divResult = N.divideAndRemainder(i);
    	     if (divResult[1].compareTo(BigInteger.ZERO) == 0)
    	    	 return false;
    	     
    	     i = i.add(BigInteger.ONE);
    	
    	//the scope of the search reduces by a factor of i as potential factors are eliminated. Big computation saving.
    	} while (i.compareTo(divResult[0]) < 1);
    	
		return true;
    }

	@Override
	public void run() {
		for (Integer n : vector){
			if (checkPrime(n))
				System.out.println("Int " + n + " is prime");
			else
				System.out.println("Int " + n + " is not prime");
		}
	}
	
	public static void main(String[] args){
		final int vectorSize = 100000;
		//creates vector of random integers up to 100,000.
		Integer[] vector = new Integer[vectorSize];
		for (int i = 0; i<vectorSize; i++)
			vector[i] = ThreadLocalRandom.current().nextInt(0,100000);
		
		//splits vector into sub-vectors of length 10 for parallel processing in AePrime thread.
		int i = 0;
		while ( i < vectorSize)
			new AePrime(Arrays.copyOfRange(vector, i, i = i + vectorSize/10)).run();
		
	}
}
