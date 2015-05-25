package dao;

import java.util.Scanner;

/**
 * The class handling the game execution.
 */
public class Main {

	
	 
	/*
	 * Main method of the program. Responsible for handling the game execution.	
	 * @param args Command line arguments. 
	**/ 
	
	public static void main(String[] args) {		
		
		Scanner sc = new Scanner(System.in);
		String s;
		StepCoordinates coord; 
		
		System.out.println("Welcome to the DAO game! Give coordinates in (x,y)-(x,y) format!\n");
		Game g = new Game();
		g.printGameData();
		
		
		while(true)
		{		
						
			while(true)
			{
				s = sc.nextLine();
				char[] coordinates= s.toCharArray();
				while(!checkFormat(coordinates)){
					System.out.println("Bad format! Use (x,y)-(x,y) format!");
					s = sc.nextLine();
					coordinates= s.toCharArray();
					
				}				
				coord= new StepCoordinates(coordinates[1], coordinates[3], coordinates[7], coordinates[9]);				
				if(g.step(coord)) break;
				else System.out.println("Impossible move!");
			}
				
			if(g.winCheck() != 'O'){
				
				if(g.winCheck()=='R') 	System.out.println("The winner is the red player!\n");
				if(g.winCheck()=='B') 	System.out.println("The winner is the blue player!\n");
				break;
			}
			
			g.nextPlayer();
			g.printGameData();	
	
		}
		
		
		
		
}

	/**
	 * A method that checks the format of user input.
	 * @param coordinates The user input in character array form.
	 */
	private static boolean checkFormat(char[] coordinates) {
		///(x,y)-(x,y)
		if(coordinates.length!=11) return false;
		if( ( coordinates[0] =='(' && coordinates[4] ==')' && coordinates[6] =='(' && coordinates[10]==')') &&
			coordinates[2] ==',' && coordinates[8] ==',' && Character.isDigit(coordinates[1]) && Character.isDigit(coordinates[3]) &&
			Character.isDigit(coordinates[7]) && Character.isDigit(coordinates[9]) ) return true;
		
		return false;
	}
	
	

}
