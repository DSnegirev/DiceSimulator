package threadeddice;

/*
 * Software Engineer: Darrel Karbginsky
 * Computer Science Instructor
 * Chemeketa Community College
 */


import java.io.*;
import java.lang.*;

 /** 
  * This class defines a single die object.
  * 
  * @author <a href= "mailto:dkarbgin@chemeketa.edu" >Darrel Karbginsky</a>
  */ 
public class Die
{
	/** Minimum number of faces allowed on a single die object */
   	private final int MIN_FACES = 3;
	/** Number of sides on the die */
   	private int numFaces; 
	/** Current value showing on the die */
   	private int faceValue;  

	/**
	 * This constructor creates a Die object and defaults to a 
	 * six-sided die, initially with the face showing 1.
	 */
   	public Die ()
   	{
      	numFaces = 6;
      	faceValue = 1;
   	}

	/**
	 * This constructor explicitly sets the size of the die. Defaults to a 
     * size of six if the parameter is invalid.  Initial face value is 1.
	 * @param faces Number of faces desired.
	 * @return Returns a single Die object with the number of faces
	 * passed in a a parameter.
	 */
   	public Die (int faces)
   	{
    	if (faces < MIN_FACES)
         	numFaces = 6;
      	else
         	numFaces = faces;

      	faceValue = 1;
   	}

	/**
	 * Rolls the die and returns the result.
	 * @return Returns the face value of the die.
	 */	
   	public int roll ()
   	{
      	faceValue = (int) (Math.random() * numFaces) + 1;
      	return faceValue;
   	}

	/**
	 * Returns the current die value.
	 * @return Returns the current die value.
	 */
   	public int getFaceValue ()
   	{
      	return faceValue;
   	}
	
	/**
	 * Typical toString method
	 * @return Returns a logical representation of the state of the object
	 */
	public String toString()
	{
		return String.valueOf(faceValue);
	}
}
