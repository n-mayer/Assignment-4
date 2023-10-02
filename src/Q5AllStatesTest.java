package src;

//package Assignment6;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class Q5AllStatesTests {
	/*
	 * Author: Nicholas Mayer
	 * Date: 9/30/2023
	 * Purpose: JUnit test cases for a list for Assignment 6
	 */
	@Test
	/*
	 * Test Case ID: TotalAvg-SysTest-013 (State Test Case, S2: List Empty)
	 * Purpose: Test case to cover what should happen if arrayTest starts empty
	 */
	void test() 
	{
		//Test setup
		Q5Class teamList = new Q5Class(new ArrayList<Integer>(){{}});

		//Input
		teamList.processList();

		//Expected output
		assertEquals(18, Q5Class.getTotal());
		assertEquals(4.5, Q5Class.getAverage());
	}

	void test2() {
		
	}
}
