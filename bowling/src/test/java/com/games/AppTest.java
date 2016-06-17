package com.games;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testComputeScore()
    {
    	/*
    	 * Test for the computeScore() function. Using the numbers given in the assignment, it should result
    	 * in a total score of 159.
    	 */
    	
    	Integer finalScore = new Integer(159);
    	
    	String scores = "2,3, x,, 5,/, 4,5, x,, x,, 6,/, 7,2, 8,1, x,x,x";
    	
        assertTrue(finalScore.equals(App.computeScore(scores)));
    	
    }
    
    public void testFinalSpare()
    {
    	
    	/*
    	 * Test case for if the game ends in a spare (and the one bonus roll after the spare).
    	 */
    	
    	Integer finalScore = new Integer(141);
    	
    	String scores = "2,3, x,, 5,/, 4,5, x,, x,, 6,/, 7,2, 8,1, 4, /,2";
    	
        assertTrue(finalScore.equals(App.computeScore(scores)));
    	
    }
    
    public void testScoreConversion()
    {
    	
    	Integer integerScore = new Integer(10);
    	
    	/*
    	 * Test case to make sure that "x" or "X" are converted to an integer value of 10.
    	 */
    	
    	String scores = "x";
    	
        assertTrue(integerScore.equals(App.convertFromString(scores, null)));
        
        scores = "X";
        assertTrue(integerScore.equals(App.convertFromString(scores, null)));
        
        /*
         * Test case to see what the value of the spare would be if the first roll was a 4. Should return 6.
         */
        
        String previous = "4";
        integerScore = new Integer(6);
        scores = "/";
        assertTrue(integerScore.equals(App.convertFromString(scores, previous)));
    	
    }
    
    
}
