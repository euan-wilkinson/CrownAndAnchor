/** 
 * <h1> Crown and Anchor GUI </h1>
 * The CrownAndAnchorGUI class is the functional front-end and initialiser of Crown and Anchor. 
 * The class handles the rendering of the game's graphics as well as the initialisation and running
 * of the program itself. It also passes data into the back-end. 
 * 
 * @author Euan Wilkinson, ID S5083285
 * @version 1.0
 */

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.PlainDocument;

public class CrownAndAnchorGUI 
{
	//Set JFrames
	private JFrame frame = new JFrame(); 		//Main window
	private JFrame helpFrame = new JFrame(); 	//Help window
	
	//Set JLabels
	private JLabel lblTitle = new JLabel(); 					//Title label
	private JLabel lblCrowns = new JLabel();					//Crown counter label
	private JLabel lblFeedback = new JLabel(); 					//Feedback label
	private JLabel lblGrid = new JLabel(); 						//The board 
	private JLabel lblDie1, lblDie2, lblDie3 = new JLabel();	//The three dice
	private JLabel lblHeart = new JLabel(); 					//heart image
	private JLabel lblCrown = new JLabel();						//crown image
	private JLabel lblDiamond = new JLabel();					//diamond image
	private JLabel lblSpades = new JLabel();					//spades image
	private JLabel lblAnchor = new JLabel();					//anchor image
	private JLabel lblClubs = new JLabel();						//clubs image
	private JLabel lblHelpTitle = new JLabel(); 				//The title for the help window
	private JLabel lblSubHelp = new JLabel(); 					//Help sub-title
	private JLabel lblCrownsImage = new JLabel(); 				//The little icon next to the crown counter
	
	//Set JButtons
	private JButton btnHelp, btnRoll = new JButton(); //the buttons
	
	//Set JTextFields
	private JTextField txtHeart = new JTextField(); 	// heart input
	private JTextField txtCrown = new JTextField(); 	// crown input
	private JTextField txtDiamond = new JTextField(); 	// diamond input
	private JTextField txtSpades = new JTextField(); 	// spades input 
	private JTextField txtAnchor = new JTextField(); 	// anchor input 
	private JTextField txtClubs = new JTextField(); 	// clubs input
	
	//Set JPanel for help window
	private JPanel helpPanel = new JPanel(); //help panel
	
	//Set text area for help text
	private JTextArea txtaHelp = new JTextArea(); //help text
	
	private boolean helpMade,betsPlaced = false; //a pair of flags to prevent unneeded repeating of methods
	
	//Declare integers
	private int betHearts; 			//hearts bet
	private int betCrown; 			//crown bet
	private int betDiamonds; 		//diamond bet
	private int betSpades; 			//spades bet
	private int betAnchor; 			// anchor bet
	private int betClubs; 			//clubs bet
	private int crownCount = 250; 	// back-end crown counter
	private int payoutMult; 		//Multiplier to calculate payouts
	
	//Declare strings
	private String inputHearts; 	//heart bet as a string
	private String inputCrown; 		//crown bet as a string
	private String inputDiamonds; 	//diamond bet as a string 
	private String inputSpades; 	//spades bet as a string 
	private String inputAnchor;		//anchor bet as a string
	private String inputClubs;		//clubs bet as a string
	private String crownText;		//front end crown counter
	private String dieResult1;		//die 1 result
	private String dieResult2;		//die 2 result
	private String dieResult3;		//die 3 result
	
	//Initialise the game
	private CrownAndAnchor game = new CrownAndAnchor();
	
	//Declare some images
	ImageIcon help = new ImageIcon("help.png");				//The help button image
	ImageIcon crowns = new ImageIcon("crowns.png");			//The image for the coin icon next to the crown counter
	ImageIcon defaultdie = new ImageIcon("defaultdie.png");	//the blank die image
	ImageIcon hdie = new ImageIcon("hdie.png");				//the die with the heart symbol on it
	ImageIcon cdie = new ImageIcon("cdie.png");				//the die with the crown symbol on it
	ImageIcon ddie = new ImageIcon("ddie.png");				//the die with the diamond symbol on it
	ImageIcon sdie = new ImageIcon("sdie.png");				//the die with the spade symbol on it
	ImageIcon adie = new ImageIcon("adie.png");				//the die with the anchor symbol on it
	ImageIcon cldie = new ImageIcon("cldie.png");			//the die with the club symbol on it
	ImageIcon grid = new ImageIcon("Grid.png");				//the grid showing the symbols on the board
	
	//Set the fonts
	Font titleFont = new Font("Castellar", Font.PLAIN, 60); 	//the font used for titles
	Font counterFont = new Font("Castellar",Font.PLAIN, 36);	//the font used to count crowns
	Font entryFont = new Font("Castellar", Font.PLAIN, 20); 	//the font used for the other text
	
	/*
	 * This is the implementation of Rob Camick's "BackgroundPanel" class in this
	 * game used to use an image for the background.
	 */
	Toolkit tools = Toolkit.getDefaultToolkit();
	Image table = tools.getImage("Table.png");
	BackgroundPanel panel = new BackgroundPanel(table, BackgroundPanel.ACTUAL, 1.0f, 0.5f);
	
	/**
	 * Creates the GUI for the game using methods.
	 */
	public CrownAndAnchorGUI()
	{
		//create the window
		createForm();
		//add the graphical elements
		addGraphics();
		//add the user inputs
		addInputs();
		//render
		frame.add(panel);
		frame.setVisible(true);
	}
	
	/**
	 * Creates the actual window for the GUI.
	 */
	public void createForm()
	{
		frame = new JFrame();
		frame.setTitle("Crown and Anchor");
		frame.setSize(1920,1080);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.setLayout(null);
	}
	
	/**
	 * Adds the text and images to the GUI. 
	 */
	public void addGraphics()
	{
		//make the gold colour for the crown counter
		Color gold = new Color(233,194,78);
		
		//make the title
		lblTitle = new JLabel("Crown and Anchor");
		lblTitle.setBounds(565, -250, 825, 650);
		lblTitle.setFont(titleFont);
		lblTitle.setForeground(Color.BLACK);
		panel.add(lblTitle);
		
		//make the counter text
		lblCrowns = new JLabel(crownText);
		lblCrowns.setBounds(1720, 10, 400, 50);
		lblCrowns.setFont(counterFont);
		lblCrowns.setForeground(gold);
		panel.add(lblCrowns);
		updateCrowns();
		
		//make the feedback text
		lblFeedback = new JLabel("Welcome to Crown and Anchor!");
		lblFeedback.setBounds(300, 650, 1920, 650);
		lblFeedback.setFont(titleFont);
		lblFeedback.setForeground(Color.BLACK);
		panel.add(lblFeedback);
		
		//make the grid
		lblGrid = new JLabel(grid);
		lblGrid.setBounds(550, 100, 800,600 );
		panel.add(lblGrid);
		
		//make the image for the crown counter
		lblCrownsImage = new JLabel(crowns);
		lblCrownsImage.setBounds(1650, 0, 75,75);
		panel.add(lblCrownsImage);
		
		//make the image for die 1
		lblDie1 = new JLabel(defaultdie);
		lblDie1.setBounds(590,700,200,200);
		panel.add(lblDie1);
		
		//make the image for die 2
		lblDie2 = new JLabel(defaultdie);
		lblDie2.setBounds(850,700,200,200);
		panel.add(lblDie2);
		
		//make the image for die 3
		lblDie3 = new JLabel(defaultdie);
		lblDie3.setBounds(1110,700,200,200);
		panel.add(lblDie3);
	}
	
	/**
	 * Adds all user inputs to the GUI.
	 */
	public void addInputs()
	{
		//make the button to roll the dice
		btnRoll = new JButton ("Roll the Dice!");
		btnRoll.setBounds(1550, 750, 150, 75);
		btnRoll.addActionListener(new RollHandler());
		panel.add (btnRoll);
		
		//make the button to open the help window
		btnHelp = new JButton(help);
		btnHelp.setToolTipText("About and How-to");
		btnHelp.setBounds(0,0,75,75);
		btnHelp.setContentAreaFilled(false);
		btnHelp.setBorderPainted(false);
		btnHelp.addActionListener(new HelpHandler());
		panel.add (btnHelp);
		
		//make the textfield for the heart symbol
		txtHeart = new JTextField();
		txtHeart.setText("0");
		txtHeart.setToolTipText("Type your bet on Heart here using numbers");
		txtHeart.setBounds(615, 350, 200, 25);
		txtHeart.setFont(entryFont);
		txtHeart.setForeground(Color.BLACK);
		txtHeart.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
		panel.add(txtHeart);
		//These two lines implement the docfilter made by StackOverflow user "Hovercraft Full Of Eels"
		PlainDocument heartDoc = (PlainDocument) txtHeart.getDocument();
	    heartDoc.setDocumentFilter(new MyIntFilter());
        
	    //make the label for the heart symbol
		lblHeart = new JLabel("Bet:");
		lblHeart.setBounds(570,313,50,100);
		lblHeart.setFont(entryFont);
		lblHeart.setForeground(Color.BLACK);
		panel.add(lblHeart);

		//make the textfield for the crown symbol
		txtCrown = new JTextField();
		txtCrown.setToolTipText("Type your bet on Crown here using numbers");
		txtCrown.setText("0");
		txtCrown.setBounds(873, 350, 200, 25);
		txtCrown.setFont(entryFont);
		txtCrown.setForeground(Color.BLACK);
		txtCrown.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
		panel.add(txtCrown);
		//These two lines implement the docfilter made by StackOverflow user "Hovercraft Full Of Eels"
		PlainDocument crownDoc = (PlainDocument) txtCrown.getDocument();
	    crownDoc.setDocumentFilter(new MyIntFilter());
	    //make the label for the crown symbol
		lblCrown = new JLabel("Bet:");
		lblCrown.setBounds(825,313,50,100);
		lblCrown.setFont(entryFont);
		lblCrown.setForeground(Color.BLACK);
		panel.add(lblCrown);
		
		//make the textfield for the diamond symbol
		txtDiamond = new JTextField();
		txtDiamond.setToolTipText("Type your bet on Diamond here using numbers");
		txtDiamond.setText("0");
		txtDiamond.setBounds(1133, 350, 200, 25);
		txtDiamond.setFont(entryFont);
		txtDiamond.setForeground(Color.BLACK);
		txtDiamond.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
		panel.add(txtDiamond);
		//These two lines implement the docfilter made by StackOverflow user "Hovercraft Full Of Eels"
		PlainDocument diamondDoc = (PlainDocument) txtDiamond.getDocument();
	    diamondDoc.setDocumentFilter(new MyIntFilter());
	    //make the label for the diamond symbol
		lblDiamond = new JLabel("Bet:");
		lblDiamond.setBounds(1085,313,50,100);
		lblDiamond.setFont(entryFont);
		lblDiamond.setForeground(Color.BLACK);
		panel.add(lblDiamond);
		
		//make the textfield for the spades symbol
		txtSpades = new JTextField();
		txtSpades.setToolTipText("Type your bet on Spades here using numbers");
		txtSpades.setText("0");
		txtSpades.setBounds(615, 625, 200, 25);
		txtSpades.setFont(entryFont);
		txtSpades.setForeground(Color.BLACK);
		txtSpades.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
		panel.add(txtSpades);
		//These two lines implement the docfilter made by StackOverflow user "Hovercraft Full Of Eels"
		PlainDocument spadesDoc = (PlainDocument) txtSpades.getDocument();
	    spadesDoc.setDocumentFilter(new MyIntFilter());
	    //make the label for the spades symbol
		lblSpades = new JLabel("Bet:");
		lblSpades.setBounds(570,587,50,100);
		lblSpades.setFont(entryFont);
		lblSpades.setForeground(Color.BLACK);
		panel.add(lblSpades);
		
		//make the textfield for the anchor symbol
		txtAnchor = new JTextField();
		txtAnchor.setToolTipText("Type your bet on Anchor here using numbers");
		txtAnchor.setText("0");
		txtAnchor.setBounds(873, 625, 200, 25);
		txtAnchor.setFont(entryFont);
		txtAnchor.setForeground(Color.BLACK);
		txtAnchor.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
		panel.add(txtAnchor);
		//These two lines implement the docfilter made by StackOverflow user "Hovercraft Full Of Eels"
		PlainDocument anchorDoc = (PlainDocument) txtAnchor.getDocument();
	    anchorDoc.setDocumentFilter(new MyIntFilter());
		//make the label for the anchor symbol
		lblAnchor = new JLabel("Bet:");
		lblAnchor.setBounds(825,587,50,100);
		lblAnchor.setFont(entryFont);
		lblAnchor.setForeground(Color.BLACK);
		panel.add(lblClubs);
		
		//make the textfield for the clubs symbol
		txtClubs = new JTextField();
		txtClubs.setToolTipText("Type your bet on Clubs here using numbers");
		txtClubs.setText("0");
		txtClubs.setBounds(1133, 625, 200, 25);
		txtClubs.setFont(entryFont);
		txtClubs.setForeground(Color.BLACK);
		txtClubs.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
		panel.add(txtClubs);
		//These two lines implement the docfilter made by StackOverflow user "Hovercraft Full Of Eels"
		PlainDocument clubsDoc = (PlainDocument) txtClubs.getDocument();
	    clubsDoc.setDocumentFilter(new MyIntFilter());
		//make the label for the clubs symbol
		lblClubs = new JLabel("Bet:");
		lblClubs.setBounds(1085,587,50,100);
		lblClubs.setFont(entryFont);
		lblClubs.setForeground(Color.BLACK);
		panel.add(lblClubs);
	}
	
	/**
	 * Builds the window for the Help UI.
	 */
	public void createHelp()
	{
		helpFrame = new JFrame();
		helpFrame.setTitle("Crown and Anchor Help");
		helpFrame.setSize(800,860);
		helpFrame.setResizable(true);
		helpFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		helpPanel.setLayout(new BoxLayout(helpPanel, BoxLayout.Y_AXIS));
		helpFrame.add(helpPanel);
		helpFrame.setVisible(true);
		helpPanel.setVisible(true);
	}
	
	/**
	 * Adds the text to the Help UI
	 */
	public void addHelpText()
	{
		//Add title
		lblHelpTitle = new JLabel ("CROWN AND ANCHOR");
        lblHelpTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblHelpTitle.setFont(titleFont);
        lblHelpTitle.setForeground(Color.BLACK);
        helpPanel.add(lblHelpTitle);
        
        //Add help subpanel
        lblSubHelp = new JLabel ("HELP");
        lblSubHelp.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblSubHelp.setFont(titleFont);
        lblSubHelp.setForeground(Color.BLACK);
        helpPanel.add(lblSubHelp);
        //Add text area
        txtaHelp = new JTextArea("Crown and anchor is an obscure game invented by the Royal Navy in the 18th Century. "
        		+ "				\nIt is a simple dice-based gambling game - the player bets money on a square. If the image on the square comes up on the dice, they are awarded according to the number of times one of the squares they bet on appears. In addition to their bet, they recieve even money if it appears once, double money if twice, and triple money if thrice."
        		+ "				\nA player may bet on any of the six squares and may bet on any number of them concurrently. Betting in this game is done using an in-game currency called 'Crowns'. "
        		+ "				\nYour Crowns are displayed in the top right. To bet, type the amount you wish to bet using numbers in the box beneath the symbol on which you want to bet, then click the 'Place Bets' button."
        		+ "				\nWhen you are ready, click the 'Roll Dice' button, which will roll the dice. The outcome of your roll will be displayed on the dice at the bottom of the screen and information on the result will appear under the dice. If you win, the appropriate number of Crowns will be awarded to you, but you will lose your bet if you do not. "
        		+ "				\nBeware! You are not infinitely wealthy - if you run out of Crowns, you lose.");
        txtaHelp.setAlignmentX(Component.CENTER_ALIGNMENT);
        txtaHelp.setFont(entryFont);
        txtaHelp.setForeground(Color.BLACK);
        txtaHelp.setBackground(null);
        txtaHelp.setEditable(false);
        txtaHelp.setLineWrap(true);
        txtaHelp.setWrapStyleWord(true);
        helpPanel.add(txtaHelp);
       
        //Flag that it was built so it doesn't rebuild every time it opens 
        helpMade = true;
       
	}
	
	/**
	 * Opens the help UI
	 */
	public void openHelp()
	{
		//if the help window hasn't been made
		if (helpMade == false)
		{
			//make it
			createHelp();
			addHelpText();
		}
		//else just make the window visible
		else
		{
			helpFrame.setVisible(true);
		};
		
	}
	
	/**
	 * Changes the images representing the dice in the game window.
	 */
	public void changeDice()
	{
		//Get the results
		dieResult1 = game.die1.translateNumber();
		dieResult2 = game.die2.translateNumber();
		dieResult3 = game.die3.translateNumber();

		//Check result for die1, update accordingly
		switch(dieResult1)
		{
		case "Hearts":
			lblDie1.setIcon(hdie);
			break;
		case "Crown":
			lblDie1.setIcon(cdie);
			break;
		case "Diamonds":
			lblDie1.setIcon(ddie);
			break;
			
		case "Spades":
			lblDie1.setIcon(sdie);
			break;
			
		case "Anchor":
			lblDie1.setIcon(adie);
			break;
			
		case "Clubs":
			lblDie1.setIcon(cldie);
			break;
			
		}
		
		//Check result for die2, update accordingly
		switch(dieResult2)
		{
		case "Hearts":
			lblDie2.setIcon(hdie);
			break;
			
		case "Crown":
			lblDie2.setIcon(cdie);
			break;
			
		case "Diamonds":
			lblDie2.setIcon(ddie);
			break;
			
		case "Spades":
			lblDie2.setIcon(sdie);
			break;
			
		case "Anchor":
			lblDie2.setIcon(adie);
			break; 
			
		case "Clubs":
			lblDie2.setIcon(cldie);
			break;
		}

		//Check result for die3, update accordingly
		switch(dieResult3)
		{
		case "Hearts":
			lblDie3.setIcon(hdie);
			break;
			
		case "Crown":
			lblDie3.setIcon(cdie);
			break;
			
		case "Diamonds":
			lblDie3.setIcon(ddie);
			break; 
			
		case "Spades":
			lblDie3.setIcon(sdie);
			break; 
			
		case "Anchor":
			lblDie3.setIcon(adie);
			break;
			
		case "Clubs":
			lblDie3.setIcon(cldie);
			break;
		}
		
		
	}
	
	/**
	 * Handles the saving of bets to the game.
	 */
	public void placeBets()
	{
		//Avoid bugs by resetting
		betsPlaced = false;
	
		//Get all bets
		inputHearts = txtHeart.getText();
		txtHeart.setText("0");
		betHearts = Integer.parseInt(inputHearts);
	
		inputCrown = txtCrown.getText();
		txtCrown.setText("0");
		betCrown = Integer.parseInt(inputCrown);
		
		inputDiamonds = txtDiamond.getText();
		betDiamonds = Integer.parseInt(inputDiamonds);
		txtDiamond.setText("0");
		
		inputSpades = txtSpades.getText();
		betSpades = Integer.parseInt(inputSpades);
		txtSpades.setText("0");
		
		inputAnchor = txtAnchor.getText();
		betAnchor = Integer.parseInt(inputAnchor);
		txtAnchor.setText("0");
		
		inputClubs = txtClubs.getText();
		betClubs = Integer.parseInt(inputClubs);
		txtClubs.setText("0");
		
		//Make sure player has enough crowns to bet
		if ((betHearts +betCrown + betDiamonds + betSpades+betAnchor+betClubs) > crownCount)
		{
			//Tell them if they don't 
			System.err.println("Not enough crowns!");
			lblFeedback.setText("Not enough crowns!");
			lblFeedback.setBounds(550, 650, 1920, 650);
			betsPlaced = false;
		}
		//If they do, subtracts bets from their purse
		else if ((betHearts +betCrown + betDiamonds + betSpades+betAnchor+betClubs) > 0)
		{
			crownCount = crownCount - (betHearts +betCrown + betDiamonds + betSpades+betAnchor+betClubs);
			crownText = Integer.toString(crownCount);
			lblCrowns.setText(crownText);
			game.setBetArray(betHearts, betCrown, betDiamonds, betSpades, betAnchor, betClubs);
			betsPlaced = true;
		}
		//If they don't bet, tell them
		else
		{
			System.out.println("Bets not placed");
			lblFeedback.setText("Place a bet first!");
			lblFeedback.setBounds(650, 650, 1920, 650);
		}
	}
	
	/**
	 * Handles the game over mechanic for when the player runs out of Crowns and provides a restart option.
	 */
	public void gameOver()
	{
		//make an option pane
		Object[] options = {"Play Again","Quit"};
		int n = JOptionPane.showOptionDialog(null,
		            "You're out of crowns! What do you want to do?",
		            "Game Over",
		            JOptionPane.YES_NO_CANCEL_OPTION,
		            JOptionPane.DEFAULT_OPTION,
		            null,
		            options,
		            options[1]);  

		//If they click play again
		if(n==0)
		{  
			//restore the game to its original state
		    crownCount = 250;
		    crownText = Integer.toString(crownCount);
			lblCrowns.setText(crownText);
			lblFeedback.setText("Welcome to Crown And Anchor!");
			lblFeedback.setBounds(300, 650, 1920, 650);
			lblDie1.setIcon(defaultdie);
			lblDie2.setIcon(defaultdie);
			lblDie3.setIcon(defaultdie);
		}
		//otherwise, just close the game
		else if(n==1)
		{
		    System.exit(0);
		}
	}
	
	/**
	 * Updates crown counter visible to the player by using the variable in the back-end.
	 */
	public void updateCrowns()
	{
		crownCount = crownCount + game.getWinnings();
		crownText = Integer.toString(crownCount);
		lblCrowns.setText(crownText);	
	}
	
	/**
	 * Figures out which feedback message is appropriate to give to the player based on the payout multiplier from 
	 * the back-end.
	 */
	public void deduceFeedback()
	{
		//If the winnings are 0, they must have lost.
		if (game.getWinnings() == 0)
		{
			lblFeedback.setText("Unlucky, old chap! You lost!");
			lblFeedback.setBounds(375, 650, 1920, 650);
		}
		
		//If they tripled their money
		else if (payoutMult == 3)
		{
			lblFeedback.setText("You tripled one of your bets!");
			lblFeedback.setBounds(375, 650, 1920, 650);
			updateCrowns();
		}
		
		//if they doubled their money and successfully bet on the third die
		else if (payoutMult == 2 && game.getSideBet() == true)
		{
			lblFeedback.setText("Doubled one bet, even on the other!");
			lblFeedback.setBounds(300, 650, 1920, 650);
			updateCrowns();
		}
		//if they doubled their money but didn't successfully bet on the second die - for any reason
		else if (payoutMult == 2 && game.getSideBet() == false)
		{
			lblFeedback.setText("You doubled one of your bets!");
			lblFeedback.setBounds(375, 650, 1920, 650);
			updateCrowns();
		}
		//if they successfully bet on one symbol
		else if (payoutMult == 1)
		{
			lblFeedback.setText("You won even money!");
			lblFeedback.setBounds(525, 650, 1920, 650);
			updateCrowns();
		}
		//this shouldn't happen - the loss should have been caught earlier.
		else
		{
			lblFeedback.setText("Unlucky, old chap! You lost!");
		}
	}
	
	//This initialises the GUI
	public static void main(String[] args) 
	{
		@SuppressWarnings("unused")
		CrownAndAnchorGUI Game = new CrownAndAnchorGUI();
	}
	
	/**
	 * Handles the button click for the help button.
	 */
	class HelpHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent helpClicked)
		{
			System.out.println("Help button clicked");
			openHelp();
		}
	}

	/**
	 * Handles the button click for the roll button.
	 */
	class RollHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent rollClicked)
		{
			//only permit bets if they haven't lost
			if (crownCount > 0) 
			{
				//place the bets
				placeBets();
				if (betsPlaced == true)
				{
					//reset the muliplier to avoid issues
					payoutMult = 0;
					//roll dice
					game.rollDice();
					changeDice();
					//figure out winnings
					game.calculateWinnings();
					payoutMult = game.getPayoutMult();
					//figure out feedback
					deduceFeedback();
					//if they are now out of money, they lost
					if (crownCount <= 0)
					{
						gameOver();
					}
				}
			}
			else 
			{
				//if they have lost, they've lost
				gameOver();
			}
		}		
	}
}

