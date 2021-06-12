/** 
 * <h1> Crown and Anchor </h1>
 * The CrownAndAnchor class is the functional back-end of Crown and Anchor. 
 * The class handles the logic and work behind the GUI by making three dice objects, rolling those dice, inputs bets into
 * an array from the GUI, and calculates the result of the bet. 
 * 
 * @author Euan Wilkinson, ID S5083285
 * @version 1.0
 */

public class CrownAndAnchor 
{
	//Make three dice
	CAADie die1 = new CAADie();
	CAADie die2 = new CAADie();
	CAADie die3 = new CAADie();
	
	//Set three results for dice
	private int die1Result;
	private int die2Result;
	private int die3Result;
	
	//Initialise an array for the six symbols and all the variables needed
	int[] betArray = new int[6];
	private int payoutMult;
	private int winnings;
	private int bet1;
	private int bet2;
	private int bet3;
	private int doubleMoneyFlag;
	private boolean sideBet;
	
	/**
	 * Handles rolling the three dice and determining their results.
	 * Also determines the payout received due to those results and sets a flag used to determine feedback in the GUI.
	 */
	public void rollDice()
	{
		//set multiplier to 0 to make sure no errors
		payoutMult = 0;
		
		//Roll die 1, get the result and check the result
		die1.roll();	
		die1Result = die1.getValue();
		die1.checkResult(betArray);
	
		//Roll die 2, get the result and check the result
		die2.roll();
		die2Result = die2.getValue();
		die2.checkResult(betArray);
		
		//Roll die 3, get the result and check the result
		die3.roll();
		die3Result = die3.getValue();
		die3.checkResult(betArray);
		
		//Result determination logic 
		if (die1.getValue() ==  die2.getValue() && die1.getValue() == die3.getValue() && die1.isBetCorrect())
		{
			payoutMult = 3;
		}
		//All the different types of double money results
		else if (die1.getValue() == die2.getValue() && die1.isBetCorrect())
		{
			payoutMult = 2;
			doubleMoneyFlag = 1;
		}
		else if (die1.getValue() == die3.getValue() && die3.isBetCorrect())
		{
			payoutMult = 2;
			doubleMoneyFlag = 2;
		}
		else if (die2.getValue() == die3.getValue()&& die2.isBetCorrect())
		{
			payoutMult = 2;
			doubleMoneyFlag = 3;
		}
		//For if only one is right
		else if (die1.isBetCorrect() == true || die2.isBetCorrect() == true || die3.isBetCorrect() == true)
		{
			payoutMult = 1;
		}
		//If none of the above, it's a loss
	
	}
	
	/**
	 * Places bets from the GUI into an array for the game.
	 * @param inHearts 		the bet taken from the text box for the heart symbol in the GUI
	 * @param inCrown		the bet taken from the text box for the crown symbol in the GUI
	 * @param inDiamonds	the bet taken from the text box for the diamond symbol in the GUI
	 * @param inSpades		the bet taken from the text box for the spades symbol in the GUI
	 * @param inAnchor		the bet taken from the text box for the anchor symbol in the GUI
	 * @param inClubs		the bet taken from the text box for the clubs symbol in the GUI
	 */
	public void setBetArray(int inHearts,int inCrown,int inDiamonds,int inSpades,int inAnchor, int inClubs)
	{
			betArray[0] = inHearts;
			betArray[1] = inCrown;
			betArray[2] = inDiamonds;
			betArray[3] = inSpades;
			betArray[4] = inAnchor;
			betArray[5] = inClubs;
	}

	/**
	 * Calculates earned winning based on the payout multiplier determined earlier
	 */
	public void calculateWinnings()
	{
		//Determine the bets 
		bet1 = betArray[(die1.getValue()) - 1];
		bet2 = betArray[(die2.getValue()) - 1];
		bet3 = betArray[(die3.getValue()) - 1];
		//Determine the payout
		switch (payoutMult)
		{
		//if three dice come up
		case 3: 
			winnings = bet1 + bet1 * payoutMult;
			break;
		//If two dice come up
		case 2:
			//Then check which two dice it was
			switch (doubleMoneyFlag)
			{
			//If die1 and die2 are the double
			case 1:
				//If the third symbol hasn't been bet on
				if (die3.isBetCorrect() == false)
				{
					winnings = bet1 + bet1 * payoutMult;
					break;
				}
				//If the third symbol has had a bet placed on it
				else if (die3.isBetCorrect() == true)
				{
					winnings = (bet1 + bet1 * payoutMult) + bet3;
					/*
					 * Sidebet is used to make sure that if a player places a bet on a third die and wins on that bet, they still get
					 *  their even money on that side bet.
					 */
					setSideBet(true);
					break;
				}
			//If die1 and die3 are the double 
			case 2:
				//If the second symbol hasn't been bet on
				if (die2.isBetCorrect() == false)
				{
					winnings = bet3 + bet3 * payoutMult; 
					break;
				}
				//If the second symbol has had a bet placed on it
				else if (die2.isBetCorrect() == true)
				{
					winnings = (bet3 + bet3 * payoutMult) + bet2;
					setSideBet(true);
					break;
				}
			//If die2 and die3 are the double 
			case 3:
				//If the first symbol hasn't been bet on
				if (die1.isBetCorrect() == false)
				{
					winnings = bet2 + bet2 * payoutMult; 
					break;
				}
				//If the first symbol has been bet on
				else if (die1.isBetCorrect() == true)
				{
					winnings = (bet2 + bet2 * payoutMult) + bet1;
					setSideBet(true);
					break;
				}
			}
			break;
		//If only one die comes up
		case 1:
			if (die1.isBetCorrect() == true) 
			{
				winnings = bet1 * 2;
			}
			else if (die2.isBetCorrect() == true)
			{
				winnings = bet2 * 2;
			}
			else if (die3.isBetCorrect() == true)
			{
				winnings = bet3 * 2;
			}
			
			
		} 
	}

	/**
	 * Getter for the result of die1
	 * 
	 * @return int returns the number value of die1 
	 */
	public int getResult()
	{
		return die1Result;
	}
		
	/**
	 * Getter for the result of die2
	 * 
	 * @return int returns the number value of die2
	 */
	public int getResult2()
	{
		return die2Result;
	}
		
	/**
	 * Getter for the result of die3
	 * 
	 * @return int returns the number value of die3
	 */
	public int getResult3()
	{
		return die3Result;
	}
	
	/**
	 * Getter for the payout multiplier
	 * 
	 * @return int returns the payout multiplier
	 */
	public int getPayoutMult()
	{
		return payoutMult;
	}
	
	/**
	 * Getter for the winnings
	 * 
	 * @return int returns the winnings
	 */
	public int getWinnings()
	{
		return winnings;
	}

	/**
	 * Getter for the sideBet
	 * 
	 * @return boolean returns whether or not the player set two bets
	 */
	public boolean getSideBet() 
	{
		return sideBet;
	}
	/**
	 * setter for the sideBet
	 * 
	 * @param sideBet a flag for whether or not the player set two bets
	 */
	public void setSideBet(boolean sideBet) 
	{
		this.sideBet = sideBet;
	}
	
	
}
