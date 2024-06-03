package features;

import java.util.ArrayList;
import java.util.List;

public class UnnamedPatternsAndVariables {

	public static void main(String[] args) {
		
		List<Integer> list = new ArrayList<Integer>();
		list.add(0);
		list.add(1);
		list.add(2);
		// Fetch the first element (element at index 0)
		int firstElement = list.getFirst();
		// Fetch the last element
		int lastElement = list.getLast();
	}
}
