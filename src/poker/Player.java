package poker;

import javax.swing.JButton;
import java.util.Scanner;
import GUI.pokerTable;
import GUI.ButtonPress;

public class Player {
	String name;
	Hand hand;
	int money;
	int bet;
	//int choiceHolder;
	Scanner in = new Scanner(System.in);
	

	/**
	 * Constructor for player class
	 * @param name
	 * @param money
	 */
	public Player(String name, int money) {
		this.name = name;
		this.money = money;
		
	}

	public enum PlayerChoice {
		CHECK,CALL,FOLD,RAISE,NULL
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Hand getHand() {
		return hand;
	}
	
	public void setHand(Hand hand) {
		this.hand = hand;
	}

	public void receiveCard(Card c) {
		hand.receiveCard(c);
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getBet() {
		return bet;
	}

	
	
	/**
	 * determine the amount of bet placed
	 * @param bet
	 * @return the amount of money bet
	 */
	public int bet(int bet) {
		int sizeOfBet = Math.min(money, bet);
		this.bet += sizeOfBet;
		money -= sizeOfBet;
		return sizeOfBet;
	}

	public void resetBet() {
		this.bet = 0;
	}
	
	public boolean isAllIn() {
		if(money == 0) {
			return true;
		}
		return false;
	}

	
	//public PlayerChoice checkRaise() {
//		System.out.println("Please enter your choice:");
//		System.out.println("1 to CHECK and 2 to RAISE");
		
		
		
	/*	int playerStrategy = pokerTable.getUserInput("Please enter: 1 to CHECK and 2 to RAISE");
		
		
		while (true) {
			switch (playerStrategy) {
			case 1:
				return PlayerChoice.CHECK;
			case 2:
				return PlayerChoice.RAISE;
			default:
				pokerTable.displayMessage("That's not an option. Try again!");
				System.out.println("That's not an option. Try again!");
				break;
			}
		}
		*/
	//}
	
	
	
	/*public PlayerChoice callRaiseFold() {
//		Scanner in = new Scanner(System.in);
//		System.out.println("Please enter your choice:");
//		System.out.println("1 to CALL, 2 to RAISE, and 3 to FOLD");
		
		int playerStrategy = pokerTable.getUserInput("Please enter: 1 to CALL, 2 to RAISE and 3 to FOLD");
		
		while (true) {
			switch (playerStrategy) {
			case 1:
				return PlayerChoice.CALL;
			case 2:
				return PlayerChoice.RAISE;
			case 3:
				return PlayerChoice.FOLD;
			default:
				pokerTable.displayMessage("That's not an option. Try again!");
				System.out.println("That's not an option. Try again!");
				break;
			}
		}
	}
	*/
}