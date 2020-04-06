package poker;

import java.util.ArrayList;

public class Game {

	public int smallBlind = 5;
	public int startingMoney = 100;

	Player player = new Player("Player", startingMoney);
	Player computer = new Player("Computer", startingMoney);
	ArrayList<Player> players = new ArrayList<Player>();

	boolean isPlayerTurn = true;
	ArrayList<Card> communityCards = new ArrayList<Card>();

	Deck deck = new Deck();

	public Game(int smallBlind, int startingMoney) {
		this.smallBlind = smallBlind;
		this.startingMoney = startingMoney;
	}

	public void startGame() {
		players.add(player);
		players.add(computer);
		deck.shuffle();
		playRound();
	}

	public int getSmallBlind() {
		return smallBlind;
	}

	public void dealPlayerCards(Player player, Player computer) {
		player.receiveCard(deck.deal());
		computer.receiveCard(deck.deal());
		player.receiveCard(deck.deal());
		computer.receiveCard(deck.deal());
	}

	public void playRound() {
		playPreflop();
		playFlop();
		playTurn();
	}

	public void playPreflop() {
		dealPlayerCards(player, computer);
		playBettingRound();
	}

	public void playFlop() {
		dealFlop();
		playBettingRound();
	}

	public void dealFlop() {
		deck.deal();
		deck.deal();
		deck.deal();
		communityCards.add(deck.deal());
		communityCards.add(deck.deal());
		communityCards.add(deck.deal());
	}

	public void playTurn() {
		dealTurn();
		playBettingRound();
	}

	public void dealTurn() {
		deck.deal();
		communityCards.add(deck.deal());
	}

	public void playRiver() {
		dealRiver();
		playBettingRound();
	}

	public void dealRiver() {
		deck.deal();
		communityCards.add(deck.deal());
	}

	public void playBettingRound() {
		Player playerOnTurn;
		int maxBet = getMaxBet();
		if(isPlayerTurn) {
			if(playerHasBet(player, maxBet)) {
				switch(player.hasBetAction()) {
				case CHECK:
					break;
				case RAISE:
					int raiseAmount = askPlayerForBet();
					maxBet += raiseAmount;
					break;
				}
			}
			else {
				switch(player.callRaiseFold()) {
				case FOLD:
					break;
				case CALL:
					break;
				case RAISE:
					break;
				
				}
			}
		}
		else {
			computerBettingRound();
		}
	}

	public int getMaxBet() {
		int maxBet = 0;

		if (player.getBet() > maxBet) {
			maxBet = player.getBet();
		}
		else if(computer.getBet() > maxBet) {
			maxBet = computer.getBet();
		}
		return maxBet;
	}
	
	public boolean playerHasBet(Player player, int maxBet) {
		if(player.getBet() < maxBet) {
			return false;
		}
		return true;
	}

	
	public int getCurrentPot() {
        int pot = 0;
        
        for (Player player : players) {
            pot += player.getBet();
        }
      
        return pot;
    }

}
