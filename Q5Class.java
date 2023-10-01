//contains main
//package Assignment6;

import java.util.*;

public class Q5Class {

	// initialize variables
	private static int count = 0;
	private static int total = 0;
	private static double average = 0.0;
	
	// Container to hold the list of integers
	private static ArrayList<Integer> myList;
	
	
	// Constructor

	public Q5Class() {

		myList = new ArrayList<Integer>();
	}

	// checks if list is empty. If it is not, process each positive element from the list & adds it to total. Count is increased by 1.
    public void processList(ArrayList<Integer> numbers) {

		myList = numbers;

        if (myList.isEmpty()) {

            System.out.println("The list is empty.");
            return;
        }
		else {

			for(Integer i : myList) if(i > 0) addTotal(i);
			setAverage();
		}
    }
	
	// adds num to total
	private static void addTotal(int num) {
		
		total = total + num;
		count++;
		System.out.println(total);
	}
	
	// returns total
	public static int getTotal() { return total; }
	
	// computes average
	private static void setAverage() {

		average = (double)total/count;
		//System.out.println(average);
	}
	
	// returns average
	public static double getAverage() { return average; }
}
