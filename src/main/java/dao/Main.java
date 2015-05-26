package dao;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 

/**
 * The class handling the game execution.
 */
public class Main {

	
	
	/**
	 * The Logback logger of the <code>Main</code> class.
	 */
	private static Logger logger = LoggerFactory.getLogger(Main.class);
	
	
	/**
	 * {@code Scanner} object for handling user input.
	 */
	public static Scanner sc = new Scanner(System.in);
	
	
	/**
	 * String object used for processing user input.
	 */
	public static String s;
	
	
	
	/**
	 * {@code StepCoordinates} object for storing and handling user movement coordinates. 
	 */
	public static StepCoordinates coord;
	
	
	
	/**
	 * {@code Game} object for handling game events.
	 */
	public static Game g;
	
	
	 
	/**
	 * Main method of the program. Responsible for handling the game execution.	
	 * 
	 * @param args Command line arguments. Not used.
	**/ 	
	public static void main(String[] args) {		
		logger.info("Main method started.");
		sc = new Scanner(System.in);
		System.out.println("Welcome to the DAO game! Give coordinates in (x,y)-(x,y) format!\n");
		g = new Game();
		g.printGameData();
		
		logger.info("Game cycle starting.");
		while(true)
		{		
						
			while(true)
			{
				s = sc.nextLine();
				char[] coordinates= s.toCharArray();
				while(!checkFormat(coordinates)){
					System.out.println("Bad format! Use (x,y)-(x,y) format!");
					logger.warn("Bad format.");
					s = sc.nextLine();
					coordinates= s.toCharArray();					
				}			
				logger.info("A well formed coordinate was given.");
				coord= new StepCoordinates(coordinates[1], coordinates[3], coordinates[7], coordinates[9]);				
				if(g.step(coord)) break;
				else System.out.println("Impossible move!");
			} 
			char c = g.winCheck();
			if(c != 'O'){
				
				if(c == 'R'){
					System.out.println("The winner is the red player!\n");
					logger.warn("Red player won!");
				}
				
				if(c =='B'){
					System.out.println("The winner is the blue player!\n");
					logger.warn("Blue player won!");
				}
				
				
				break;
			}
			 
			g.nextPlayer();
			g.printGameData();	
	
		}
		
		
		
		
}

	/**
	 * A method that validates the format of user input.
	 * @param coordinates The user input in character array form.
	 * @return Returns true if they user input is well formed.
	 */
	public static boolean checkFormat(char[] coordinates) {
		logger.info("Checking user input format.");
		if(coordinates.length!=11) return false;
		if( ( coordinates[0] =='(' && coordinates[4] ==')' && coordinates[6] =='(' && coordinates[10]==')') &&
			coordinates[2] ==',' && coordinates[8] ==',' && Character.isDigit(coordinates[1]) && Character.isDigit(coordinates[3]) &&
			Character.isDigit(coordinates[7]) && Character.isDigit(coordinates[9]) ) return true;
		logger.warn("Bad user input format.");
		return false;
	}
	
	

}
