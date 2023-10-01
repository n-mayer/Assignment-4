import java.util.ArrayList;

/*
 * Author: Team 8
 * Date: 9/30/2023
 * Purpose: Create a program the process a list of integers, finds the sum of 
 * and average of the positive integers in the list.
 */

public class Main {
    
    public static void main(String[] args) {

		// Get the list
        Q5Class teamList = new Q5Class();

        teamList.genList(new ArrayList<Integer>(){{
            add(1);
            add(5);
            add(9);
            add(3);
            add(-3);
        }});
        
        // Process the list
        teamList.processList();
		
		System.out.println("The total is " + teamList.getTotal() + ", and the average is " + teamList.getAverage() + ".");
	}
}