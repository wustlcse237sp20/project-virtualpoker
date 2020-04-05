package poker;

public class Gameplay {
	//Player player = new Player(playerName, playerHand, startingMoney);
	//Player computer = new Player("Computer", computerHand, startingMoney);
	//newRound(player, computer);
	
	
	//i need to make initializers and stuff but i think this is okay??...i hope
	
	
	public void newRound(Player player, Player computer) {
		//initialize game
		Deck deck = new Deck();
		deck.shuffle();
		Hand playerHand = new Hand();
		Hand computerHand = new Hand();
		deck.deckAfterDeal(playerHand, computerHand);
		//create community cards
		Flop flop = new Flop();
		flop.setCards(deck);
		//deal turn card
		flop.newFlopCard(deck);
		//deal river card
		flop.newFlopCard(deck);
		
	//at this point cards are compared to determine winner
	//I'm not sure if we're including betting at this point so it's not here
	}
}
		
		
		
		
	
	
	

