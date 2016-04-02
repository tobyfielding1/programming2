package exercise_sheet_5;

public class NumberPrinter extends Thread {
	int number;
	
	public NumberPrinter(int num){
		number = num;
	}
	public void run() {
		try {
			Thread.sleep(1000*(3 - number));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        System.out.println("Thread " + number + " has terminated");
    }
	
	public static void main(String[] args) {
		for (int i = 1; i<=3; i++){
			(new NumberPrinter(i)).start();
		}
	}
}
