package poker;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.stream.Stream;

import poker.Hand.HandRank;

public class Tester {
	public static void main(String[] args) {
		Hand h = new Hand();
		Card[] hand = h.getHand();
		Card[] communityCards = new Card[5];
		
		Rank two = (new Rank(0, "TWO"));
		Rank three = (new Rank(1, "THREE"));
		Rank four = (new Rank(2, "FOUR"));
		Rank five = (new Rank(3, "FIVE"));
		Rank six = (new Rank(4, "SIX"));
		Rank seven = (new Rank(5, "SEVEN"));
		Rank eight = (new Rank(6, "EIGHT"));
		Rank nine = (new Rank(7, "NINE"));
		Rank ten = (new Rank(8, "TEN"));
		Rank jack = (new Rank(9, "JACK"));
		Rank queen = (new Rank(10, "QUEEN"));
		Rank king = (new Rank(11, "KING"));
		Rank ace= (new Rank(12, "ACE"));
		
		hand[0] = new Card(Suits.DIAMONDS, two);
		hand[1] = new Card(Suits.CLUBS, five);
		
		communityCards[2] = new Card(Suits.DIAMONDS, three);
		communityCards[3] = new Card(Suits.CLUBS, two);
		communityCards[4] = new Card(Suits.HEARTS, two);
		
		
		communityCards[0] = new Card(Suits.HEARTS, three);
		communityCards[1] = new Card(Suits.DIAMONDS, ace);
		
		h.displayHand();
		for (int i = 0; i < 5; i++) {
			System.out.println(communityCards[i]);
		}
		

		System.out.println( h.determineHandRank(communityCards));

	}
}
