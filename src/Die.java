/** 
 * <h1> Universal Die </h1>
 * The die class is designed to be used in dice games which 
 * generates a random number the way a six-sided die would and returns 
 * the generated number.
 * 
 * @author Euan Wilkinson, ID S5083285
 * @version 1.0
 */

//Get random library
import java.util.Random;

public class Die
{
	//set a variable for the result
	private  int value;
	
	/**
	 * Generates and returns the value of a die roll.
	 * It uses Random to make a random number generator from 1 - 6 inclusive.
	 * 
	 * @return int returns the result of a die roll.
	 */
	public int roll()
	{
		//make a generator, generate the number
		Random dieGenerator = new Random();
		setValue(dieGenerator.nextInt(6) + 1);
		return getValue();
		
	}

	/**
	 * Getter for the value of the die. 
	 * 
	 * @return int returns the result of the die roll.
	 */
	//getter for the value
	public int getValue() 
	{
		return value;
	}

	/**
	 * Setter for the value of the die.
	 * 
	 * @param value the value of the die
	 */
	//setter for the value
	public void setValue(int value) 
	{
		this.value = value;
	}

}
