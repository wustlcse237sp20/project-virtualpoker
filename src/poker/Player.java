package poker;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.Scanner;
import GUI.pokerTable;
import poker.Player.PlayerChoice;
//import GUI.ButtonPress;

public class Player {
	String name;
	Hand hand;
	int money;
	int bet;
	
	
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

	
	

	
	
	
	
	
	
	
	
	
	
	

	
}