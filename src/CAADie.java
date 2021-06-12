/** 
 * <h1> Crown and Anchor Die </h1>
 * The CAADie class is designed to be used in specifically Crown and Anchor, which extends the basic die class.
 * The class will map a number value from the basic class to a symbol and checks the result. 
 * 
 * @author Euan Wilkinson, ID S5083285
 * @version 1.0
 * @see Die
 */

public class CAADie extends Die {

	//Set a few variables
	private boolean betCorrect;
	private String imageValue = translateNumber();
	
	/**
	 * Maps a number value to a string which is associated with an image elsewhere using a switch statment.
	 * 
	 * @return String this returns a string value representing a symbol
	 */
	public String translateNumber()
	{
		switch(getValue())
		{
		case 1: 
			imageValue = ("Hearts");
			break;
		case 2: imageValue = ("Crown");
			break;
		case 3: imageValue = ("Diamonds");
			break;
		case 4: imageValue= ("Spades");
			break;
		case 5: imageValue = ("Anchor");
			break;
		case 6: imageValue = ("Clubs");
			break;
		}
		return imageValue;
	}
	
	/**
	 * Checks the result of the die roll based on whether the player bet on the symbol which was rolled.
	 * Uses a switch statement to detect the die's result and check for a bet placed on that result. It then uses a boolean
	 * flag to indicate whether a bet was placed on that symbol or not.
	 * 
	 * @param betArray	the array of bets from the game
	 * @return boolean this returns a boolean value which determines whether or not the bet placed was correct
	 */
	public boolean checkResult(int betArray[])
	{
		//Make sure the game doesn't think it's right when the dice haven't been rolled yet
		setBetCorrect(false);
		
		//Get the value on the dice
		switch(getValue())
		{
			case 1: 
				if (betArray[0] > 0)
				{
					//Set the flag for the correct bet
					setBetCorrect(true);
					break;
				}
				else
				{
					//This will happen if the player doesn't bet on the symbol
					setBetCorrect(false);
					break;
				}
			case 2: 
				if (betArray[1] > 0)
				{
					//Set the flag for the correct bet
					setBetCorrect(true);
					break;
				}
				else
				{
					//This will happen if the player doesn't bet on the symbol
					setBetCorrect(false);
					break;
				}
			case 3: 
				if (betArray[2] > 0)
				{
					//Set the flag for the correct bet
					setBetCorrect(true);
					break;
				}
				else
				{
					//This will happen if the player doesn't bet on the symbol
					setBetCorrect(false);
					break;
				}
			case 4: 
				if (betArray[3] > 0)
				{
					//Set the flag for the correct bet
					setBetCorrect(true);
					break;
				}
				else
				{
					//This will happen if the player doesn't bet on the symbol
					setBetCorrect(false);
					break;
				}
			case 5:
				if (betArray[4] > 0)
				{
					//Set the flag for the correct bet
					setBetCorrect(true);
					break;
				}
				else
				{
					//This will happen if the player doesn't bet on the symbol
					setBetCorrect(false);
					break;
				}
			case 6: 
				if (betArray[5] > 0)
				{
					//Set the flag for the correct bet
					setBetCorrect(true);
					break;
				}
				else
				{
					//This will happen if the player doesn't bet on the symbol
					setBetCorrect(false);
					break;
				}
		}
		//return the flag so the game knows
		return betCorrect;
	}
	
	/**
	 * Getter for whether or not the bet was correct.
	 * 
	 * @return boolean returns whether the bet was correct
	 */
	public boolean isBetCorrect() 
	{
		return betCorrect;
	}

	/**
	 * Setter for whether or not the bet is correct.
	 * 
	 * @param betCorrect	the flag for whether or not the bet placed was correct
	 */
	public void setBetCorrect(boolean betCorrect) 
	{
		this.betCorrect = betCorrect;
	}
}

