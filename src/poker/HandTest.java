package poker;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import poker.Hand.HandRank;

class HandTest {

	@Test
	void testCreateHand() {
		Rank three = new Rank(1, "THREE");
		Rank ace = new Rank(12, "ACE");
		Card threeOfHearts = new Card(Suits.HEARTS,three);
		Card aceOfDiamonds = new Card(Suits.DIAMONDS, ace);
		Hand h = new Hand();
		Card[] hand = h.getHand();
		hand[0] = threeOfHearts;
		hand[1] = aceOfDiamonds;
		assertEquals(hand[0].getRank().toString(), "THREE");
		assertEquals(hand[0].getSuit(), Suits.HEARTS);
	}
	
	@Test
	void testDealToHand() {
		Deck d = new Deck();
		Hand h = new Hand();
		Card[] hand = h.getHand();
		hand[0] = d.deal();
		hand[1] = d.deal();
		
		Card[] communityCards = new Card[5];
		communityCards[0] = d.deal();
		communityCards[1] = d.deal();
		communityCards[2] = d.deal();
		communityCards[3] = d.deal();
		communityCards[4] = d.deal();
		
		
		
		assertEquals(h.determineHandRank(communityCards), HandRank.STRAIGHT_FLUSH);
	}
	
	@Test
	void testDetermineHandRank() {
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
		hand[1] = new Card(Suits.CLUBS, two);
		
		communityCards[0] = new Card(Suits.HEARTS, two);
		communityCards[1] = new Card(Suits.SPADES, two);
		communityCards[2] = new Card(Suits.DIAMONDS, three);
		communityCards[3] = new Card(Suits.CLUBS, three);
		communityCards[4] = new Card(Suits.HEARTS, three);

		assertEquals(h.determineHandRank(communityCards), HandRank.FOUR_OF_A_KIND);
		
		communityCards[0] = new Card(Suits.HEARTS, six);
		communityCards[1] = new Card(Suits.DIAMONDS, ace);
		
		assertEquals(h.determineHandRank(communityCards), HandRank.FULL_HOUSE);
		
		communityCards[3] = new Card(Suits.CLUBS, four);
		
		assertEquals(h.determineHandRank(communityCards), HandRank.TWO_PAIR);
		
	}

}
