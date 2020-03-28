package poker;

public class Gameplay {
	public static void main(String[] args) { 
		playGame("Banana", 500);
	}
	
	public static void playGame(String playerName, int startingMoney) {
		Hand playerHand = new Hand();
		Hand computerHand = new Hand();
		Player player = new Player(playerName, playerHand, startingMoney);
		Player computer = new Player("Computer", computerHand, startingMoney);
		newRound(player, computer);
	}
	
	public static void newRound(Player player, Player computer) {
		Deck deck = new Deck();
		deck.shuffle();
	}
}
