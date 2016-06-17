package com.games;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;


public class App 
{
    public static void main( String[] args ) throws Exception
    {
        
    	/*
    	 * Check if a filename was specified in the argument, or if the file or filename exists.
    	 */
    	
    	if (args == null || args.length == 0)
    	{
    		System.out.println("Please specify a filename.");
    		
    		System.exit(1);
    	}
    	
    	File myFile = new File(args[0]);
    	
    	if (!myFile.exists())
    	{
             System.out.println("File does not exist, please specify a filename.");
             
             System.exit(2);
    	}
    	
    	System.out.println("");
    	System.out.println("Opening " + args[0] + ". . .");
    	System.out.println("");

    	/*
    	 * Pulls the contents from the file.
    	 */
    	
    	BufferedReader br = new BufferedReader(new FileReader(myFile));
    	String line = null;
    	
    	/*
    	 * Main calculation of the project, passes the values from the file into the function computeScore().
    	 */
    	
    	while ((line = br.readLine()) != null)
    	{
    		
    		System.out.println("The scores from the file add up to " + computeScore(line) + ".");
    		
    	}
    	

    	
    }
    
    
    
    public static int computeScore(String line)
    {
    	/*
    	 * Puts the values from the file into a String array, replacing the extra commas and deleting whitespaces (so the algorithm will work).
    	 */

    	String [] stringArray = line.replaceAll(",,", ",").replaceAll("\\s+","").split(",");
        int totalScore = 0;
        int frameCount = 0;
        int singleCount = 0;
		
        /*
         * Traverses array, identifying each value and taking action.
         */
        
		for (int i = 0; i < stringArray.length; i++)
		{	
			
			/*
			 * If the value is a strike, 10 points are added to totalScore while also finding the next 2 values from the array in integer format
			 * and adding them to totalScore as well. 
			 */
			
			if (stringArray[i].toLowerCase().equals("x"))
			{   
				int next = convertFromString(stringArray[i+1], stringArray[i]) +  convertFromString(stringArray[i+2], stringArray[i+1]);
				totalScore += 10 + next;
				
				frameCount++; //If the value is a strike, the frameCount goes up by one, meaning the frame is completed.
				
				System.out.println(stringArray[i] + " Strike. Score so far: " + totalScore);
				
				if (frameCount == 10) //At the 10th frame, the game is over and the totalScore is returned.
				{
					System.out.println("");
					System.out.println(stringArray[i+1] + " Bonus roll. ");
					System.out.println(stringArray[i+2] + " Bonus roll. ");
					System.out.println("");
					
					return totalScore;
				}
				
				
			}
			
			/*
			 * If the value is a spare, add the value of the pins knocked down from the spare (calculated with the convertFromString function)
			 * and add the next value in the array.
			 */
			
			else if (stringArray[i].equals("/"))
			{
				
				int next = convertFromString(stringArray[i], stringArray[i-1]) + convertFromString(stringArray[i+1], stringArray[i]);
				totalScore += next;
				
				singleCount++; //singleCount will add a frame for every 2 throws that don't result in a strike (which would end the frame immediately).
				
				System.out.println(stringArray[i] + " Spare.  Score so far: " + totalScore);
				
				if (singleCount % 2 == 0) 
				{
					frameCount++;
				}
				
				if (frameCount == 10)
				{
					System.out.println("");
					System.out.println(stringArray[i+1] + " Bonus roll. ");
					System.out.println("");
					
					return totalScore;
				}
				
				
		
			}
			
			/*
			 * Since it is not a strike or spare, the other numbers (1-9) will only have to be converted from string to integer.
			 */
			
			else
			{
				totalScore += Integer.parseInt(stringArray[i]);
				
				singleCount++;
				
				if (singleCount % 2 == 0)
				{
					frameCount++;
				}
				
				System.out.println(stringArray[i] + "         Score so far: " + totalScore);
				
				if (frameCount == 10)
				{
					return totalScore;
				}
				
				
			}
		    
			
		 }
		
		
		
		
		return -1;
    	
    }
    
    
    public static int convertFromString(String val, String prev)
    {
    	
    	/*
    	 * 
    	 * This function converts the true value of each entry in the file. This is used primarily for when there is a strike or a spare,
    	 * because if it's a strike it adds the next 2 rolls (and the next roll if it's a spare). If the next roll after a strike or a spare
    	 * is another strike, it needs to add the value of 10. This also calculates the value of each spare by figuring out how many pins
    	 * were knocked down out of 10 to cause the spare.
    	 * 
    	 */
    	
    	if (val.toLowerCase().equals("x"))
		{
			return 10;
		}
		
		else if (val.equals("/"))
		{
			return 10 - Integer.parseInt(prev);
		}
		
		
		else
		{
			return Integer.parseInt(val);
		}
    	
    }
    
    
    
}


