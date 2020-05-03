package poker;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

//import java.util.Scanner;
//import GUI.ButtonPress;

import GUI.pokerTable;
import poker.Player.PlayerChoice;


public class Game {

	public int smallBlind = 5;
	public int startingMoney = 100;
	public int again = 1;
	long sleepTime = 40000;
	
	

	Player player = new Player("Player", startingMoney);
	ComputerPlayer computer = new ComputerPlayer("Computer", startingMoney);
	ArrayList<Player> players = new ArrayList<Player>();
	pokerTable table = new pokerTable();

	Hand playerHand = player.getHand();
	Hand computerHand = computer.getHand();
	
	boolean isPlayerTurn = true;
	ArrayList<Card> communityCards = new ArrayList<Card>();
	ArrayList<Card> playerHandArray = new ArrayList<Card>();
	ArrayList<Card> computerHandArray = new ArrayList<Card>();

	boolean isPreflop = true;

	boolean playerActed = false;
	boolean computerActed = false;

	int maxBet = getMaxBet();

	Player winner = null;

	Deck deck = new Deck();
	
	
	public Game(Player player, ComputerPlayer computer) {
		this.player = player;
		this.computer = computer;
	}

	public Game(int smallBlind, int startingMoney, ArrayList<Player> players) {
		this.smallBlind = smallBlind;
		this.startingMoney = startingMoney;
		this.players = players;
	}

	public void startGame(String playerName) {
		players.add(player);
		players.add(computer);
		player.setHand(new Hand());
		computer.setHand(new Hand());
//		pokertable = new pokerTable(playerName);
//		while (!checkForWinner()) {
//			playRound();
//		}
	}

	public int getSmallBlind() {
		return smallBlind;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	
	public void playRound() {
		System.out.println("New Round!");
		player.resetBet();
		computer.resetBet();
		System.out.println(player.getName() + " has " + player.getMoney() + ".");
		pokerTable.displayMessage(player.getName() + " has " + player.getMoney() + ".");
		System.out.println(computer.getName() + " has " + computer.getMoney() + ".");
		pokerTable.displayMessage(computer.getName() + " has " + computer.getMoney() + ".");
		
//		doBlinds();
//		
//		playPreflop();
//		
//		if (winner == null) {
//			playFlop();
//		}
//		if (winner == null) {
//			playTurn();
//		}
//		if (winner == null) {
//			playRiver();
//		}
//		if (winner == null) {
//			playShowdown();
//		}
//		doRoundWinner();
	}
	
	public boolean isWinner() {
		if (winner == null) {
			return false;
		}
		return true;
	}
	
	
	public void doBlinds() {
		if(isPlayerTurn){
			doSmallBlind(player);
			doBigBlind(computer);
		}
		else {
			doSmallBlind(computer);
			doBigBlind(player);
		}
		System.out.println("Current Pot: " + this.getCurrentPot());
		pokerTable.displayMessage("Current Pot: " + this.getCurrentPot());
	}
	
	public int doSmallBlind(Player player) {
		player.bet(smallBlind);
		System.out.println(player.getName() + " is the small blind and bets " + smallBlind + ".");
		pokerTable.displayMessage(player.getName() + " is the small blind and bets " + smallBlind + ".");
		return smallBlind;
		
	}

	public int doBigBlind(Player player) {
		int bigBlind = smallBlind * 2;
		player.bet(bigBlind);
		System.out.println(player.getName() + " is the big blind and bets " + bigBlind + ".");
		pokerTable.displayMessage(player.getName() + " is the big blind and bets " + bigBlind + ".");
		return bigBlind;
	}
	
	public ArrayList<Card> playPreflop() {
		isPreflop = true;
		deck.shuffle();
		dealPlayerCards();
		Hand playerHand = player.getHand();
		System.out.println("Your hand:");
		playerHand.displayHand();
		playerHandArray = playerHand.getHand();
		return playerHandArray;
//		playBettingRound(isPreflop);
	}
	
	
	public ArrayList<Card> playerHandArray(){
		 playerHandArray = playerHand.getHand();
		return playerHandArray;
	}

	public void dealPlayerCards() {
		player.setHand(new Hand());
		computer.setHand(new Hand());
		player.receiveCard(deck.deal());
		computer.receiveCard(deck.deal());
		player.receiveCard(deck.deal());
		computer.receiveCard(deck.deal());
	}

//	ArrayList<Card> communityCards
	public ArrayList<Card> playFlop() {
		System.out.println("FLOP");
		isPreflop = false;
		dealFlop();
		displayCommunityCards();
		return communityCards;
//		playBettingRound(isPreflop);
	}

	public void dealFlop() {
		deck.deal();
		deck.deal();
		deck.deal();
		communityCards.clear();
		communityCards.add(deck.deal());
		communityCards.add(deck.deal());
		communityCards.add(deck.deal());
	}

	public ArrayList<Card> playTurn() {
		System.out.println("TURN");
		dealTurn();
		displayCommunityCards();
//		playBettingRound(isPreflop);
		return communityCards;
	}

	public void dealTurn() {
		deck.deal();
		communityCards.add(deck.deal());
	}

	public ArrayList<Card> playRiver() {
		System.out.println("RIVER");
		dealRiver();
		displayCommunityCards();
//		playBettingRound(isPreflop);
		return communityCards;
	}

	public void dealRiver() {
		deck.deal();
		communityCards.add(deck.deal());
	}

	public void displayCommunityCards() {
		System.out.println("Community cards:");
		for (int i = 0; i < communityCards.size(); i++) {
			System.out.println(communityCards.get(i));
		}

	}

	public boolean isPreflop() {
		if (isPreflop == true) {
			return true;
		}
		
		return false;
	}
	
		
	public void playBettingRound(boolean isPreflop) {
		maxBet = getMaxBet();
		do {
			playerActed = false;
			computerActed = false;
			if (isPlayerTurn) {
				isPlayerTurn = !isPlayerTurn;
				while(table.getPlayerHolder()==0) {	
					System.out.println("paused for player choice");
				}
				playerBettingRound();
				if(winner == null && !(computerActed && playerActed)) {
					computerBettingRound();
				}
			} else {
				isPlayerTurn = !isPlayerTurn;
				computerBettingRound();
				if(winner == null  && !(computerActed && playerActed)) {
					playerBettingRound();
				}
			}
			System.out.println("Current Pot: " + this.getCurrentPot());
		} while (((!playerHasBet(player, maxBet) || !playerHasBet(computer, maxBet)) || !(computerActed && playerActed))
				&& (winner == null) && !player.isAllIn() && !computer.isAllIn());
	}

	public void playerBettingRound() {
		isPlayerTurn = !isPlayerTurn;
		if ((!player.isAllIn())&&(table.getPlayerHolder()!=0)) {
			if (playerHasBet(player, maxBet)) {
				switch (checkRaise()) {
				case CHECK:
					playerActed = true;
					System.out.println(player.getName() + " checks.");
					pokerTable.displayMessage(player.getName() + " checks.");
					break;
				case RAISE:
					playerActed = true;
					computerActed = false;
					int raiseAmount = player.bet(askPlayerForBet(player));
					maxBet += raiseAmount;
					System.out.println(player.getName() + " raises " + raiseAmount + ".");
					pokerTable.displayMessage(player.getName() + " raises " + raiseAmount + ".");
					break;
				case CALL:
					//countHolder=0;
					break;
				case FOLD:
					//countHolder=0;
					break;
				default:
					break;
				}
			} else {
				switch (callRaiseFold()) {
				case CALL:
					playerActed = true;
					computerActed = true;
					player.bet(maxBet - player.getBet());
					System.out.println(player.getName() + " calls.");
					pokerTable.displayMessage(player.getName() + " calls.");
					break;
				case RAISE:
					playerActed = true;
					computerActed = false;
					int raiseAmount = player.bet(askPlayerForBet(player));
					maxBet += raiseAmount;
					System.out.println(player.getName() + " raises " + raiseAmount + ".");
					pokerTable.displayMessage(player.getName() + " raises " + raiseAmount + ".");
					break;
				case FOLD:
					System.out.println(player.getName() + " folds.");
					pokerTable.displayMessage(player.getName() + " folds.");
					winner = computer;
					break;
				case CHECK:
					break;
				default:
					break;

				}
			}
		}
	}

	public void computerBettingRound() {
		if ((!computer.isAllIn())&&(table.getPlayerHolder()!=0)) {
			if (playerHasBet(computer, maxBet)) {
				switch (computer.simulateCheckRaise(isPreflop, communityCards)) {
				case CHECK:
					computerActed = true;
					System.out.println(computer.getName() + " checks.");
					pokerTable.displayMessage(computer.getName() + " checks.");
					break;
				case RAISE:
					computerActed = true;
					playerActed = false;
					int raiseAmount = computer.bet(smallBlind * 2);
					maxBet += raiseAmount;
					System.out.println(computer.getName() + " raises " + raiseAmount + ".");
					pokerTable.displayMessage(computer.getName() + " raises " + raiseAmount + ".");
					break;
				case CALL:
					break;
				case FOLD:
					break;
				default:
					break;
				}
			} else {
				switch (computer.simulateCallRaiseFold(isPreflop, communityCards)) {
				case CALL:
					computerActed = true;
					playerActed = true;
					computer.bet(maxBet - computer.getBet());
					System.out.println(computer.getName() + " calls.");
					pokerTable.displayMessage(computer.getName() + " calls.");
					break;
				case RAISE:
					computerActed = true;
					playerActed = false;
					System.out.println(computer.getName() + " ");
					int raiseAmount = computer.bet(maxBet-computer.getBet() + smallBlind * 2);
					maxBet += raiseAmount;
					System.out.println(computer.getName() + " raises " + raiseAmount + ".");
					pokerTable.displayMessage(computer.getName() + " raises " + raiseAmount + ".");
					break;
				case FOLD:
					System.out.println(computer.getName() + " folds.");
					pokerTable.displayMessage(computer.getName() + " folds.");
					winner = player;
					break;
				case CHECK:
					break;
				default:
					break;

				}
			}
		}

	}

	public void playShowdown() {
		System.out.println("SHOWDOWN");
		Hand playerHand = player.getHand();
		Hand computerHand = computer.getHand();
		System.out.println("Computer hand: ");
		computerHand.displayHand();
		playerHand.determineHighestRank(communityCards);
		computerHand.determineHighestRank(communityCards);
		System.out.println(player.getName() + ": " + player.getHand().getHighestRank());
		pokerTable.displayMessage(player.getName() + ": " + player.getHand().getHighestRank());
		
		System.out.println(computer.getName() + ": " + computer.getHand().getHighestRank());
		pokerTable.displayMessage(computer.getName() + ": " + computer.getHand().getHighestRank());
		
		int comparison = playerHand.compareTo(computerHand, communityCards);
		if (comparison > 0) {
			winner = player;
		} else if (comparison < 0) {
			winner = computer;
		}
	}

	public int getMaxBet() {
		int maxBet = 0;

		if (player.getBet() > maxBet) {
			maxBet = player.getBet();
		}
		if (computer.getBet() > maxBet) {
			maxBet = computer.getBet();
		}
		return maxBet;
	}

	public boolean playerHasBet(Player player, int maxBet) {
		if (player.getBet() < maxBet) {
			return false;
		}
		return true;
	}

	public int getCurrentPot() {
		int pot = 0;

		pot += player.getBet();
		pot += computer.getBet();

		return pot;
	}

	public int askPlayerForBet(Player player) {
//		Scanner in = new Scanner(System.in);
//		System.out.println("How much would you like to bet?");
//		return in.nextInt();
		int betAmount = pokerTable.getUserInput("How much would you like to bet?");
		return betAmount;
	}

	public void doRoundWinner() {
		if (winner == player) {
			player.setMoney(player.getMoney() + this.getCurrentPot());
		} else if (winner == computer) {
			computer.setMoney(computer.getMoney() + this.getCurrentPot());
		} else {
			player.setMoney(player.getMoney() + player.getBet());
			computer.setMoney(computer.getMoney() + computer.getBet());
		}
		System.out.println(winner.getName() + " wins the hand and the pot of " + this.getCurrentPot() + "!");
		pokerTable.displayMessage(winner.getName() + " wins the hand and the pot of " + this.getCurrentPot() + "!");
		System.out.println("");
		winner = null;
		playAgain();

	}
	
	public boolean playAgain() {
		int again = pokerTable.getUserInput("Want to Play? 1=YES, 2=NO");
		if(again==1) {
			return true;
		}
		else {
			return false;
		}
		
	}

	public boolean checkForWinner() {
		if (player.getMoney() == 0) {
			System.out.println("The computer wins the game!");
			pokerTable.displayMessage("The computer wins the game!");
			
			return true;
		} else if (computer.getMoney() == 0) {
			System.out.println("You win the game!");
			pokerTable.displayMessage("You win the game!");
			return true;
		}
		return false;
	}
	
	public boolean isPlayerTurn() {
		if(isPlayerTurn) {
			return true;
		}
		return false;
	}
	
	public ArrayList<Card> getComputerHandArray(){
		computerHandArray = computerHand.getHand();
		return computerHandArray;
	}
	
	public String ComputerFirstCardtoString() {
		return "card=" + computerHandArray.get(0);
	}
	
	public String ComputerSecondCardtoString() {
		return "card=" + computerHandArray.get(1);
	}
	
	public String PlayerFirstCardtoString() {
		return "card=" + playerHandArray.get(0);
	}
	
	public String PlayerSecondCardtoString() {
		return "card=" + playerHandArray.get(1);
	}
	
	
	public String firstCommunityCardToString() {
		return "card=" + communityCards.get(0);
	}
	
	public String secondCommunityCardToString() {
		return "card=" + communityCards.get(1);
	}
	
	public String thirdCommunityCardToString() {
		return "card=" + communityCards.get(2);
	}
	
	public String fourthCommunityCardToString() {
		return "card=" + communityCards.get(3);
	}
	
	public String fifthCommunityCardToString() {
		return "card=" + communityCards.get(4);
	}
	
	public PlayerChoice checkRaise() {
		if(table.getPlayerHolder() ==1) {
			table.setPlayerHolder(0);
			return PlayerChoice.CALL;
			 }
			else {
				if(table.getPlayerHolder()==2) {
					table.setPlayerHolder(0);
					return PlayerChoice.RAISE;
				}
				else {
			if(table.getPlayerHolder()==3) {
				table.setPlayerHolder(0);
					return PlayerChoice.FOLD;
			 }
			else {
				return PlayerChoice.NULL;
			}
				}
				}
		
	}
	
	
	public PlayerChoice callRaiseFold() {

		if(table.getPlayerHolder() ==1) {
			table.setPlayerHolder(0);
			return PlayerChoice.CALL;
			 }
			else {
				if(table.getPlayerHolder()==2) {
					table.setPlayerHolder(0);
					return PlayerChoice.RAISE;
				}
				else {
			if(table.getPlayerHolder()==3) {
				table.setPlayerHolder(0);
					return PlayerChoice.FOLD;
			 }
			else {
				return PlayerChoice.NULL;
			}
				}
				}
		
	}

	

	
	
}
