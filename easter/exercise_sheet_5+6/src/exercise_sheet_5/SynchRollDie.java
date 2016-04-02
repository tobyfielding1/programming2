package exercise_sheet_5;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class SynchRollDie extends RollDie implements Runnable {

	private boolean rollFinished = false;
	
	@Override
	public void run() {
		rollFinished = false;
		super.run();
		rollFinished = true;
	}
	
	public int getVal(){
		while(!rollFinished){
			try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}
		
		return numOnFace;
	}
	
	public static void main(String[] args) {
		
		JFrame window = new JFrame("ui");
		window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
		window.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		
		JPanel pane = new JPanel();
		window.setContentPane(pane);
		pane.setLayout(new GridLayout(2,3));
		window.setVisible(true);
		
		
		int total = 0;
		SynchRollDie[] dice = new SynchRollDie[5];
		
		for (int i = 0; i < dice.length; i++){
			dice[i] = new SynchRollDie();
			pane.add(dice[i]);
		}
		
		for (SynchRollDie die : dice){
			die.init();
			new Thread(die).start();
		}
		
		for (SynchRollDie die : dice){
			int increment = die.getVal();
			total = total + increment;
		}
		
		System.out.println("total of 5 dice: " + total);
	}

}
