package dao;

import java.util.ArrayList;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

 


/**
 * The class of the Game model.
 */
 

public class Game {
	
	
	
	/**
	 * The Logback logger of the <code>Game</code> class.
	 */
	private static Logger logger = LoggerFactory.getLogger(Game.class);
	
	/**
	 * Board of the dao game.
	 */
	private char[][] gameTable;
	
	
	/**
	 *  {@code Player} object used for storing which player is on turn.
	 */
	private Player playerOnTurn;
	 
	 
	/**
	 * Constructor of this object.	  
	 */
	public Game() {		
		super();
		Random r = new Random();
		int startPlayer = r.nextInt()%2;
		if(startPlayer==0) playerOnTurn = Player.RED;
		else playerOnTurn = Player.BLUE;
		  
		this.gameTable = new char[4][4];
		initGameTable();	
		logger.info("Game object created");
		
	}

	
	/**
	 * A method that executes a player step.
	 * @param coord Coordinates for the step.
	 * @return Returns true if the step was successful.
	 */
	public boolean step(StepCoordinates coord)
	{
		logger.info("Validating step.");
	if(!isValidStep(coord)) return false;	
		
	char p=  gameTable[ coord.getFromX() ] [ coord.getFromY() ];
	gameTable[ coord.getToX() ] [ coord.getToY() ] = p;	
	gameTable[ coord.getFromX() ] [ coord.getFromY() ] = 'O';
	
	
	logger.info("Successful player step.");
	return true;
	}
	
	
	/**
	 * A method that validates a player step.
	 * @param coord Coordinates for the step.
	 * @return Returns true if the player step is allowed.
	 */
	public boolean isValidStep(StepCoordinates coord)
	{
		
		if(coord.getFromX() < 0 || coord.getFromX() > 3) return false;
		if(coord.getFromY() < 0 || coord.getFromY() > 3) return false;
		if(coord.getToX() < 0 || coord.getToX() > 3) return false;
		if(coord.getToY() < 0 || coord.getToY() > 3) return false;
		if(coord.getToX() == coord.getFromX() && coord.getToY() == coord.getFromY()) return false;
		
		char ownerOfField = gameTable[ coord.getFromX() ] [ coord.getFromY() ];
		char playerOnTurnLabel = playerOnTurn.toString().toCharArray()[0];
		if(ownerOfField != playerOnTurnLabel) return false;
		 
		
		if( gameTable[ coord.getToX() ] [ coord.getToY() ] != 'O' ) return false;
	
			
			if( coord.getToX() == coord.getFromX()) {
				int min = Math.min(coord.getFromY(), coord.getToY());
				int max = Math.max(coord.getFromY(), coord.getToY());
				for (int i = min; i <=max ; i++) {
					if(gameTable[coord.getFromX()][i] != 'O' && i != coord.getFromY()) return false;					
				}
			}
			 
			if(coord.getToY() == coord.getFromY() ) {
				int min = Math.min(coord.getFromX(), coord.getToX());
				int max = Math.max(coord.getFromX(), coord.getToX());
				for (int i = min; i <=max ; i++) {
					if(gameTable[i][coord.getFromY()] != 'O'  && i != coord.getFromX()) return false;					
				}
			}
			 
			if(   Math.abs(coord.getFromX() - coord.getToX()) == Math.abs(coord.getFromY() - coord.getToY()) ) {
				
				int signX = coord.getToX() - coord.getFromX();
				signX = signX/Math.abs(signX);				
				int signY = coord.getToY() - coord.getFromY();
				signY = signY/Math.abs(signY);
				
				int numberOfFieldsInvolved = Math.abs(coord.getFromX() - coord.getToX());
				for (int i = coord.getFromX(), j = coord.getFromY(), db = 0; db < numberOfFieldsInvolved; db++, i = i + signX, j = j + signY) {
					
					if(gameTable[i][j] != 'O' && i!= coord.getFromX()) return false;
				}				
			}
			 
		return true;
	}
	
	
	/**
	 * A method that checks if someone won the game.
	 * @return Returns 'R' if they red player won, 'B' if the blue player won, and 'O' if there is no winner yet.
	 */
	public char winCheck(){
		logger.info("Checking if one of the player won.");
		int numOfRed=0,numOfBlue=0;
		for (int i = 0; i < gameTable.length; i++) {
			for (int j = 0; j < gameTable.length; j++) {
				if(gameTable[i][j]=='B') numOfBlue++;
				if(gameTable[i][j]=='R') numOfRed++;				
			}
			
			if(numOfBlue == 4) return 'B';
			if(numOfRed == 4) return 'R'; 
			
			numOfRed=0;
			numOfBlue=0;
		}
		
		
		numOfRed=0;
		numOfBlue=0;
		for (int i = 0; i < gameTable.length; i++) {
			for (int j = 0; j < gameTable.length; j++) {
				if(gameTable[j][i]=='B') numOfBlue++;
				if(gameTable[j][i]=='R') numOfRed++;				
			}
			
			if(numOfBlue == 4) return 'B';
			if(numOfRed == 4) return 'R'; 			
			numOfRed=0;
			numOfBlue=0;
		}
		
		if(gameTable[0][0] =='B' && gameTable[3][3] =='B' && gameTable[0][3] =='B' && gameTable[3][0] =='B') return 'B';
		if(gameTable[0][0] =='R' && gameTable[3][3] =='R' && gameTable[0][3] =='R' && gameTable[3][0] =='R') return 'R';
		
		
		ArrayList<int[]> blueDisk = new ArrayList<int[]>();
		ArrayList<int[]> redDisk = new ArrayList<int[]>();
		
		for (int i = 0; i < gameTable.length; i++) {
			for (int j = 0; j < gameTable.length; j++) {
				if(gameTable[i][j]=='B') blueDisk.add(new int[]{i,j});
				if(gameTable[i][j]=='R') redDisk.add(new int[]{i,j});				
			}
		}
		int distanceX,distanceY,maxDistanceX=-1,maxDistanceY=-1;
		for (int i = 0; i < blueDisk.size(); i++) {
			for (int j = 0; j < blueDisk.size(); j++) {
				distanceX = Math.abs(blueDisk.get(i)[0] - blueDisk.get(j)[0]);
				distanceY = Math.abs(blueDisk.get(i)[1] - blueDisk.get(j)[1]);
			
				if(distanceX > maxDistanceX) maxDistanceX=distanceX;
				if(distanceY > maxDistanceY) maxDistanceY=distanceY;
			}			
		}
		if(maxDistanceX <= 1 && maxDistanceY <=1) return 'B';
		
		maxDistanceX=-1;
		maxDistanceY=-1;
		for (int i = 0; i < redDisk.size(); i++) {
			for (int j = 0; j < redDisk.size(); j++) {
				distanceX = Math.abs(redDisk.get(i)[0] - redDisk.get(j)[0]);
				distanceY = Math.abs(redDisk.get(i)[1] - redDisk.get(j)[1]);
			
				if(distanceX > maxDistanceX) maxDistanceX=distanceX;
				if(distanceY > maxDistanceY) maxDistanceY=distanceY;
			}			
		}
		if(maxDistanceX <= 1 && maxDistanceY <=1) return 'R';
		
		if( gameTable[0][0] =='R' && gameTable[0][1]=='B' && gameTable[1][1]=='B' && gameTable[1][0]=='B' ) return 'R';		
		if( gameTable[0][0] =='B' && gameTable[0][1]=='R' && gameTable[1][1]=='R' && gameTable[1][0]=='R' ) return 'B';
		
		if( gameTable[3][3] =='R' && gameTable[3][2]=='B' && gameTable[2][2]=='B' && gameTable[2][3]=='B' ) return 'R';		
		if( gameTable[3][3] =='B' && gameTable[3][2]=='R' && gameTable[2][2]=='R' && gameTable[2][3]=='R' ) return 'B';
		
		if( gameTable[3][0] =='R' && gameTable[2][0]=='B' && gameTable[3][1]=='B' && gameTable[2][1]=='B' ) return 'R';		
		if( gameTable[3][0] =='B' && gameTable[2][0]=='R' && gameTable[3][1]=='R' && gameTable[2][1]=='R' ) return 'B';
		
		if( gameTable[0][3] =='R' && gameTable[0][2]=='B' && gameTable[1][2]=='B' && gameTable[1][3]=='B' ) return 'R';		
		if( gameTable[0][3] =='B' && gameTable[0][2]=='R' && gameTable[1][2]=='R' && gameTable[1][3]=='R' ) return 'B';
		
		
		
		return 'O';
	}
	
	
	
	/**
	 * A method that switches to the next player.
	 */
	public void nextPlayer()
	{
		logger.info("Switching to the next player.");
		if(playerOnTurn == Player.BLUE) playerOnTurn = Player.RED;
		else playerOnTurn = Player.BLUE;
	}
	
	
	/**
	 * A method that sets the initial game board.	
	 */
	public void initGameTable()
	{		
		logger.info("Setting initial game table.");
		for (int i = 0; i < gameTable.length; i++) {
			for (int j = 0; j < gameTable.length; j++) {
				gameTable[i][j]='O';
			}		
		}
		gameTable[0][0]='R';
		gameTable[1][1]='R';
		gameTable[2][2]='R';
		gameTable[3][3]='R';
		
		gameTable[0][3]='B';
		gameTable[1][2]='B';
		gameTable[2][1]='B';
		gameTable[3][0]='B';
	}
	
	
	
	
	/**
	 * A method that prints game data to the console.
	 */
	public void printGameData()
	{
		logger.info("Printing game data.");
		for (int i = 0; i < gameTable.length; i++) {
			for (int j = 0; j < gameTable.length; j++) {
				System.out.print(gameTable[i][j] + " ");
			}		
			System.out.println();			
		}
		System.out.println(playerOnTurn + " player is on turn!");
	}

	/**
	 * Returns the game board.
	 * 
	 * @return Returns the gameTable object.
	 */
	public char[][] getGameTable() {
		return gameTable;
	} 
	
	
	/**
	 * Returns which player is on turn.
	 * 
	 * @return Returns the playerOnTurn object.
	 */
	public Player getPlayerOnTurn() {
		return playerOnTurn;
	}


	
	
	/**
	 * Sets the player on turn to the parameter.
	 * 
	 * @param playerOnTurn
	 *            The playerOnTurn object.
	 */
	public void setPlayerOnTurn(Player playerOnTurn) {
		this.playerOnTurn = playerOnTurn;
	}


	/**
	 * Sets the game board.
	 * 
	 * @param gameTable
	 *            The game board.
	 */
	public void setGameTable(char[][] gameTable) {
		this.gameTable = gameTable;
	}
 
	
	

}
