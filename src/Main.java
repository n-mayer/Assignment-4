package src;
import java.util.ArrayList;
//package Assignment6;

/*
 * Author: Team 8
 * Date: 9/30/2023
 * Purpose: Create a program the process a list of integers, finds the sum of 
 * and average of the positive integers in the list.
 */

public class Main {
    
    public static void main(String[] args) {

        // Tester setup
        //Q5AllStatesTests p1 = new Q5AllStatesTests();
        //Q5AllTransTests p2 = new Q5AllTransTests();

		// Get the list
        Q5Class teamList = new Q5Class(new ArrayList<Integer>(){{
            add(3);
            add(7);
            add(6);
            add(2);
            add(-3);
        }});
        
        // Processes a list
        teamList.processList();
		
		System.out.println("The total is " + Q5Class.getTotal() + ", and the average is " + Q5Class.getAverage() + ".");
	}
}