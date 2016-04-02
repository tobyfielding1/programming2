package exercise_sheet_5;

import java.math.BigInteger;
import java.util.concurrent.ThreadLocalRandom;

public class SynchAePrime implements Runnable {
	static int numThreads = 0;
	IntList list;
	
	
	public SynchAePrime(IntList vector){
		System.out.println("new thread, totalling " + ++numThreads + " AePrime threads");
		list = vector;
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
		int n;
		while(true){
			try {
				if (checkPrime(n = list.get()))
					System.out.println("Int " + n + " is prime");
				else
					System.out.println("Int " + n + " is not prime");
			} catch (InterruptedException e) {
				System.out.println("** thread terminating **");
				return;
			}
		}
	}
	
	public static void main(String[] args){
		
		final int vectorSize = new Integer(args[0]);
		final int numThreads = new Integer(args[1]);
		Thread[] threads = new Thread[numThreads];
		IntList vector = new IntList();
		
		
		//creates and starts desired number of primality testing threads;
		for (int i = 0; i < numThreads; i++){
			threads[i] = new Thread(new SynchAePrime(vector));
			threads[i].start();	
		}
	
		//creates vector of random integers up to 100,000.
		for (int i = 0; i<vectorSize; i++)
			vector.add(ThreadLocalRandom.current().nextInt(0,100000));
		
		//waiting for completion
		while (!vector.isEmpty()){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//terminating
		for (Thread thread : threads){
			thread.interrupt();
		}
	}
}
