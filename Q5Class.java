//contains main
//package Assignment6;

import java.util.*;

/*
 * Author: Team 8
 * Date: 9/30/2023
 * Purpose: Create a program the process a list of integers, finds the sum of 
 * and average of the positive integers in the list.
 */

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
    public void processList() {

        if (myList.isEmpty()) {

            System.out.println("The list is empty.");
            return;
        }
		else {

			System.out.println("Processing...");
			for (int i = 0; i < myList.size(); i++) {

        		int num = myList.get(i);

        		if (num > 0) addTotal(num);
			}
			setAverage();
		}
    }

	public void genList(List<Integer> numbers) {

		for (Integer i : numbers) myList.add(i);
	}
	
	// adds num to total
	private static void addTotal(int num) {
		total = total + num;
		count++;
	}
	
	// returns total
	public static int getTotal() { return total; }
	
	// computes average
	private static void setAverage() { average = (double)total/count; }
	
	// returns average
	public static double getAverage() { return average; }
}
