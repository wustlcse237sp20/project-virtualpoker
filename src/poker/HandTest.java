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
	
	void testDetermineHandRank() {
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

}
