package src;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class Q5AllTransTests {
    
    /*
	 * Author: Nicholas Mayer
	 * Date: 10/02/2023
	 * Purpose: JUnit test cases for a list for Assignment 6
	 */
	@Test
	/*
	 * ***COPY FROM THE TEST CASE
	 * Test Case ID:
	 * Purpose:
	 * Test Setup
	 * Input:
	 * Expected output: 18, 4.5
	 */
    void test() 
	{

        //fail("not yet implemented");
		//Test setup
		//Input
		Q5Class teamList = new Q5Class(Arrays.asList(1, 5, 9, 3, -3));

        teamList.processList();
		//Expected output
		assertTrue(Q5Class.getTotal() == 18);
		assertTrue(Q5Class.getAverage() == 4.5);
	}
}
