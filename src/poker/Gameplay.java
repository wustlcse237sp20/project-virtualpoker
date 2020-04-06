package poker;

public class Gameplay {
	
	private Player player;
	private Player computer;
	private Hand playerHand;
	private Hand computerHand;
	
	public Gameplay() {
		playerHand = new Hand();
		player = new Player ("Name", playerHand, 100);
		computerHand = new Hand();
		computer = new Player ("Name", computerHand, 100);
	}
	
	public String newRound() {
		//initialize game
		Deck deck = new Deck();
		deck.shuffle();
		deck.firstDeal(player.getHand(), computer.getHand());
		//create community cards
		Flop flop = new Flop();
		deck.firstFlop(flop);
		//deal turn card
		flop.newFlopCard(deck);
		//deal river card
		flop.newFlopCard(deck);
		
	//at this point cards are compared to determine winner
	//I'm not sure if we're including betting at this point so it's not here
	return "the winner is: ";
	}
	
	
}
		
		
		
		
	
	
	

