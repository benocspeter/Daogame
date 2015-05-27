/**
 * 
 */
package daoTest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import dao.Game;
import dao.Main;
import dao.Player;
import dao.StepCoordinates;
 
public class MainTest {
	 private Game g;
	 
	
	@Before
	public void Setup(){
		g= new Game();
	  
	}

	@Test
	public void testSetGameTable1() {
		
		for (int i = 0; i < 4; i++) {
			assertEquals('R', g.getGameTable()[i][i]);
		}
	}
	
	
	@Test
	public void testSetGameTable2() {		
		for (int i = 0; i < 4; i++) {
			assertEquals('B', g.getGameTable()[i][3-i]);
		}
	}
	
	@Test
	public void testPlayerSwitch() {
		g.setPlayerOnTurn(Player.RED); 
		g.nextPlayer();
		
		assertEquals(Player.BLUE, g.getPlayerOnTurn()) ;
		
	}
	
	public void testPlayerSwitch2() {
		g.setPlayerOnTurn(Player.BLUE); 
		g.nextPlayer();
		
		assertEquals(Player.RED, g.getPlayerOnTurn()) ;
		
		
	}
	
	
	@Test
	public void testWinCheck1() {
		char[][] state =
		{
		{'R','R','R','R'},
		{'O','O','O','O'},
		{'O','O','O','O'},
		{'O','O','O','O'}				
		};		
	
		g.setGameTable(state);
		assertEquals('R',g.winCheck());
	}	
	
	@Test
	public void testWinCheck2() {
		char[][] state =
			{
			{'B','O','O','O'},
			{'B','O','O','O'},
			{'B','O','O','O'},
			{'B','O','O','O'}				
			};		
	
		g.setGameTable(state);
		assertEquals('B',g.winCheck());
	}	
	
	@Test
	public void testWinCheck3() {
		char[][] state =
			{
			{'R','R','O','O'},
			{'R','R','O','O'},
			{'O','O','O','O'},
			{'O','O','O','O'}				
			};		
	
		g.setGameTable(state);
		System.out.println(g.winCheck());
		assertEquals('B',g.winCheck());
	}	

	
	@Test
	public void testWinCheck4() {
		char[][] state =
			{
			{'B','O','O','B'},
			{'O','O','O','O'},
			{'O','O','O','O'},
			{'B','O','O','B'}				
			};		
	
		g.setGameTable(state);
		assertEquals('B',g.winCheck());
	}	
	
	@Test
	public void testWinCheck5() {
		char[][] state =
			{
			{'R','R','O','B'},
			{'R','R','O','O'},
			{'O','O','B','O'},
			{'B','B','O','O'}				
			};		
	
		g.setGameTable(state);
		assertEquals('R',g.winCheck());
	}	
	
	@Test
	public void testWinCheck6() {
		char[][] state =
			{
			{'R','R','O','O'},
			{'O','O','O','R'},
			{'B','B','R','O'},
			{'B','B','O','O'}				
			};		
	
		g.setGameTable(state);
		assertEquals('B',g.winCheck());
	}	
	
	@Test
	public void testWinCheck7() {
		char[][] state =
			{
			{'R','B','O','O'},
			{'B','B','B','O'},
			{'O','O','O','R'},
			{'O','R','R','O'}				
			};		
	
		g.setGameTable(state);
		assertEquals('R',g.winCheck());
	}	
	@Test
	public void testWinCheck8() {
		char[][] state =
			{
			{'B','R','O','O'},
			{'R','R','B','O'},
			{'O','O','O','R'},
			{'O','R','R','O'}				
			};		
	
		g.setGameTable(state);
		assertEquals('B',g.winCheck());
	}	

	@Test
	public void testStep() {
		 g.setPlayerOnTurn(Player.RED);
		char[][] state =
			{
			{'R','O','O','O'},
			{'O','O','O','O'},
			{'O','O','O','O'},
			{'O','O','O','O'}				
			};		
	 
		g.setGameTable(state);
		StepCoordinates s = new StepCoordinates('0','0', '3', '3');
		g.step(s);
		g.printGameData();
		assertEquals('R', g.getGameTable()[3][3]);
	}	

	@Test
	public void testStep2() {
		 g.setPlayerOnTurn(Player.RED);
		char[][] state =
			{
			{'R','O','O','O'},
			{'O','B','O','O'},
			{'O','O','O','O'},
			{'O','O','O','O'}				
			};		
	 
		g.setGameTable(state);
		StepCoordinates s = new StepCoordinates('0','0', '3', '3');
		g.step(s);
		g.printGameData();
		assertEquals('O', g.getGameTable()[3][3]);
	}	
	
	
	@Test
	public void testStep3() {
		 g.setPlayerOnTurn(Player.RED);
		char[][] state =
			{
			{'R','B','O','O'},
			{'O','O','O','O'},
			{'O','O','O','O'},
			{'B','O','O','O'}				
			};		
	 
		g.setGameTable(state);
		StepCoordinates s = new StepCoordinates('0','0', '3', '0');
		g.step(s);
		g.printGameData();
		assertEquals('B', g.getGameTable()[3][0]);
	}	
	
	@Test
	public void testStep4() {
		 g.setPlayerOnTurn(Player.RED);
		char[][] state =
			{
			{'R','O','O','O'},
			{'O','O','O','O'},
			{'O','O','O','O'},
			{'O','O','O','O'}				
			};		
	 
		g.setGameTable(state);
		StepCoordinates s = new StepCoordinates('0','0', '1', '3');
		g.step(s);
		g.printGameData();
		assertEquals('O', g.getGameTable()[3][3]);
	}	
	
	@Test
	public void testStepValidation() {
		 g.setPlayerOnTurn(Player.RED);
		char[][] state =
			{
			{'R','O','O','O'},
			{'O','O','O','O'},
			{'O','O','O','O'},
			{'O','O','O','O'}				
			};		
	 
		g.setGameTable(state);
		StepCoordinates s = new StepCoordinates('0','0', '0', '0');		 
		assertEquals(false, g.isValidStep(s)); 
	}	
	@Test
	public void testStepValidation2() {
		 g.setPlayerOnTurn(Player.RED);
		char[][] state =
			{
			{'R','B','O','O'},
			{'O','O','O','O'},
			{'O','O','O','O'},
			{'O','O','O','O'}				
			};		
	 
		g.setGameTable(state);
		StepCoordinates s = new StepCoordinates('0','0', '0', '2');		 
		assertEquals(false, g.isValidStep(s)); 
	}	
	
	@Test
	public void testStepValidation3() {
		 g.setPlayerOnTurn(Player.RED);
		char[][] state =
			{
			{'R','O','O','O'},
			{'O','O','O','O'},
			{'O','O','O','O'},
			{'O','O','O','O'}				
			};		
	 
		g.setGameTable(state);
		StepCoordinates s = new StepCoordinates('0','0', '3', '1');		 
		assertEquals(false, g.isValidStep(s)); 
	}	
	
	@Test
	public void testStepValidation4() {
		 g.setPlayerOnTurn(Player.RED);
		char[][] state =
			{
			{'R','O','O','O'},
			{'O','B','O','O'},
			{'O','O','O','O'},
			{'O','O','O','O'}				
			};		
	 
		g.setGameTable(state);
		StepCoordinates s = new StepCoordinates('0','0', '2', '2');		 
		assertEquals(false, g.isValidStep(s)); 
	}	
	
	@Test
	public void testStepValidation5() {
		 g.setPlayerOnTurn(Player.RED);
		char[][] state =
			{
			{'R','O','O','O'},
			{'O','B','O','O'},
			{'O','O','O','O'},
			{'O','O','O','O'}				
			};		
	 
		g.setGameTable(state);
		StepCoordinates s = new StepCoordinates('0','0', '1', '1');		 
		assertEquals(false, g.isValidStep(s)); 
	}	
	
	public void testStepValidation6() {
		g.setPlayerOnTurn(Player.RED);
		 
		StepCoordinates s = new StepCoordinates('5','6', '0', '0');		 
		assertEquals(false, g.isValidStep(s)); 
	}	
	
	
	public void testStepValidation7() {
		g.setPlayerOnTurn(Player.RED);
		 
		StepCoordinates s = new StepCoordinates('0','0', '5', '5');		 
		assertEquals(false, g.isValidStep(s)); 
	}	
	
	@Test
	public void testStepValidation8() {
		g.setPlayerOnTurn(Player.RED);
		char[][] state =
			{
			{'R','O','O','O'},
			{'O','B','O','O'},
			{'O','O','O','O'},
			{'O','O','O','O'}				
			};		
	 
		g.setGameTable(state);
		StepCoordinates s = new StepCoordinates('1','1', '3', '3');		 
		assertEquals(false, g.isValidStep(s)); 
	}	
	
	@Test
	public void testStepValidation9() {
		 g.setPlayerOnTurn(Player.RED);
		char[][] state =
			{
			{'R','B','O','O'},
			{'O','B','O','O'},
			{'O','O','O','O'},
			{'O','O','O','O'}				
			};		
	 
		g.setGameTable(state);
		StepCoordinates s = new StepCoordinates('0','0', '0', '3');		 
		assertEquals(false, g.isValidStep(s)); 
	}	
	
	@Test
	public void testStepValidation10() {
		 g.setPlayerOnTurn(Player.RED);
		char[][] state =
			{
			{'R','O','O','O'},
			{'B','B','O','O'},
			{'B','O','O','O'},
			{'O','O','O','O'}				
			};		
	 
		g.setGameTable(state);
		StepCoordinates s = new StepCoordinates('0','0', '3', '0');		 
		assertEquals(false, g.isValidStep(s)); 
	}	
	
	
	@Test
	public void testStepValidation11() {
		 g.setPlayerOnTurn(Player.RED);
		char[][] state =
			{
			{'R','O','O','O'},
			{'O','B','O','O'},
			{'O','O','O','O'},
			{'O','O','O','O'}				
			};		
	 
		g.setGameTable(state);
		StepCoordinates s = new StepCoordinates('0','0', '3', '3');		 
		assertEquals(false, g.isValidStep(s)); 
	}	
	
	@Test
	public void testStepValidation12() {
		 g.setPlayerOnTurn(Player.RED);
		char[][] state =
			{
			{'R','O','O','O'},
			{'O','B','O','O'},
			{'O','O','O','O'},
			{'O','O','O','O'}				
			};		
	 
		g.setGameTable(state);
		StepCoordinates s = new StepCoordinates('0','0', '3', '1');		 
		assertEquals(false, g.isValidStep(s)); 
	}	
	
	
	@Test
	public void testUserInputFormat() {
		 String s ="(0,0)-(2,2)";
		assertEquals(true,Main.checkFormat(s.toCharArray()));
	}	
	@Test
	public void testUserInputFormat2() {
		 String s ="(3,0)-(0,2)";
		assertEquals(true,Main.checkFormat(s.toCharArray()));
	}	
	
	@Test
	public void testUserInputFormat3() {
		 String s ="(10,10)-(2,2)";
		assertEquals(false,Main.checkFormat(s.toCharArray()));
	}	
	
	@Test
	public void testUserInputFormat4() {
		 String s ="(0,0)(2,2)";
		assertEquals(false,Main.checkFormat(s.toCharArray()));
	}	
	
	@Test
	public void testUserInputFormat5() {
		 String s ="0,0,2,2";
		assertEquals(false,Main.checkFormat(s.toCharArray()));
	}
	@Test
	public void testUserInputFormat6() {
		 String s ="(x,y)(d,g)";
		assertEquals(false,Main.checkFormat(s.toCharArray()));
	}
}
