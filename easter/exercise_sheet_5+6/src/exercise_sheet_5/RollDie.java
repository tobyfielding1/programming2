package exercise_sheet_5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JFrame;

public class RollDie extends Die implements Runnable {
	int numOnFace;
	ArrayList<Integer> numsShown = new ArrayList<Integer>();
	
	static synchronized void displayResult(int n, ArrayList<Integer> results, int fin){
		System.out.println("die rolled " + n + " times as follows: ");
		for(Integer result : results)
			System.out.print(result + ", ");
		System.out.println("Final result: " + fin);
	}
	
	@Override
	public void run() {
		int numTurns = ThreadLocalRandom.current().nextInt(1,21);

		
		
		for (int i = 0; i< numTurns ;i++){
			numOnFace = ThreadLocalRandom.current().nextInt(1,7);
			this.updateVal(numOnFace);
			numsShown.add(numOnFace);
			
			try {
				Thread.sleep(ThreadLocalRandom.current().nextInt(50,150) + 35*i);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		displayResult(numTurns,numsShown,numOnFace);
	}

	public static void main(String[] args) {
		
		JFrame window = new JFrame("ui");
		window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
		
		RollDie die = new RollDie();
		die.init();
		window.add(die);
		window.setSize(die.getSize());
		
		window.setVisible(true);

		die.init();
		new Thread(die).start();
	}

}
