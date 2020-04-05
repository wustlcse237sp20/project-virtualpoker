package poker;


import java.util.ArrayList;

import poker.Hand.HandRank;

public class Test {
	public static void main(String[] args) {
		Hand hand = new Hand();
		ArrayList<Card> handCards = hand.getHand();
		Card nineOfDiamonds = new Card(Suit.DIAMONDS, Rank.NINE);
		Card fourOfDiamonds = new Card(Suit.DIAMONDS, Rank.FOUR);
		handCards.add(nineOfDiamonds);
		handCards.add(fourOfDiamonds);
		
		Hand opponentHand = new Hand();
		ArrayList<Card> opponentHandCards = opponentHand.getHand();
		Card kingOfClubs = new Card(Suit.CLUBS, Rank.KING);
		Card kingOfDiamonds = new Card(Suit.DIAMONDS, Rank.KING);
		opponentHandCards.add(kingOfClubs);
		opponentHandCards.add(kingOfDiamonds);
		
		ArrayList<Card> communityCards = new ArrayList<Card>();
		Card kingOfHearts = new Card(Suit.HEARTS, Rank.KING);
		Card jackOfDiamonds = new Card(Suit.DIAMONDS, Rank.JACK);
		Card aceOfDiamonds = new Card(Suit.DIAMONDS, Rank.ACE);
		Card sevenOfHearts = new Card(Suit.HEARTS, Rank.SEVEN);
		Card fourOfSpades = new Card(Suit.SPADES, Rank.FOUR);
		communityCards.add(kingOfHearts);
		communityCards.add(jackOfDiamonds);
		communityCards.add(aceOfDiamonds);
		communityCards.add(sevenOfHearts);
		communityCards.add(fourOfSpades);
		
		hand.determineHighestRank(communityCards);
		opponentHand.determineHighestRank(communityCards);
		
		System.out.println(hand.getHighestRank());
		System.out.println(opponentHand.getHighestRank());
		
		
		handCards.clear();
		Card aceOfSpades = new Card(Suit.SPADES, Rank.ACE);
		Card aceOfClubs = new Card(Suit.CLUBS, Rank.ACE);
		handCards.add(aceOfSpades);
		handCards.add(aceOfClubs);
		
		hand.determineHighestRank(communityCards);
		System.out.println(hand.getHigh());
		System.out.println(opponentHand.getHigh());
		System.out.println(hand.getHigh().compareTo(opponentHand.getHigh()));
		System.out.println(opponentHand.getHigh().compareTo(hand.getHigh()));
		
		System.out.println(hand.compareTo(opponentHand, communityCards));
	}
}
