package poker;


import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class GameTest {

	@Test
	void testDoBlinds() {
		Player player = new Player("Player", 50);
		ComputerPlayer computer = new ComputerPlayer("Computer", 50);
		player.setHand(new Hand());
		computer.setHand(new Hand());
		Game game = new Game(player, computer);
		
		game.doBlinds();
		
		assertEquals(player.getBet(), 5);
		assertEquals(computer.getBet(), 10);
	}
	
	@Test
	void testDealPlayerCards() {
		Player player = new Player("Player", 50);
		ComputerPlayer computer = new ComputerPlayer("Computer", 50);
		player.setHand(new Hand());
		computer.setHand(new Hand());
		Game game = new Game(player, computer);
		
		game.dealPlayerCards();
		
		Hand playerHand = player.getHand();
		Hand computerHand = computer.getHand();
		ArrayList<Card> handCards = playerHand.getHand();
		ArrayList<Card> computerHandCards = computerHand.getHand();
		
		assertEquals(handCards.size(), 2);
		assertEquals(computerHandCards.size(), 2);
	}
	
	@Test
	void testGetMaxBet(){
		Player player = new Player("Player", 50);
		ComputerPlayer computer = new ComputerPlayer("Computer", 50);
		Game game = new Game(player, computer);
		player.bet(30);
		computer.bet(40);
		
		int maxBet = game.getMaxBet();
		
		assertEquals(maxBet, 40);
	}
	
	@Test
	void testCheckForWinner() {
		Player player = new Player("Player", 50);
		ComputerPlayer computer = new ComputerPlayer("Computer", 50);
		Game game = new Game(player, computer);
		
		player.bet(50);
		assertEquals(game.checkForWinner(), true);
		
		
	}
	
	@Test
	void testCheckForDraw() {
		Player player = new Player("Player", 50);
		ComputerPlayer computer = new ComputerPlayer("Computer", 50);
		Game game = new Game(player, computer);
		
		player.bet(30);
		computer.bet(40);
		player.bet(10);
		assertEquals(game.checkForWinner(), false);
		
		
	}
	@Test
	void testCheckHigherBet() {
		Player player = new Player("Player", 50);
		ComputerPlayer computer = new ComputerPlayer("Computer", 50);
		Game game = new Game(player, computer);
		
		player.bet(60);
		assertEquals(game.checkForWinner(), true);
		
		
	}
	
}
