package exercise_sheet_5;

import java.util.ArrayList;

public class IntList {
	private ArrayList<Integer> list = new ArrayList<Integer>();
	
	public synchronized void add(Integer o){
		list.add(o);
		notifyAll();
	}
	
	public synchronized Integer get() throws InterruptedException{
		while (isEmpty()){
			wait();
		}
		int num = list.get(0);
		list.remove(0);
		return num;
	}
	
	public boolean isEmpty(){
		if (list.size() == 0)
			return true;
		else
			return false;
	}

}
