package poker;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class CardSetTest {

	@Test
	void testGetSuit() {
		ArrayList<Card> cards = new ArrayList<Card>();
		Card card1 = new Card(Suit.CLUBS, Rank.ACE);
		Card card2 = new Card(Suit.DIAMONDS, Rank.EIGHT);
		cards.add(card1);
		cards.add(card2);
		
		CardSet cardSet = new CardSet(cards);
		ArrayList<Card> clubs = cardSet.getSuit(Suit.CLUBS);
		
		assertEquals(clubs.get(0), card1);
	}
	
	@Test
	void testGetRank() {
		ArrayList<Card> cards = new ArrayList<Card>();
		Card card1 = new Card(Suit.CLUBS, Rank.ACE);
		Card card2 = new Card(Suit.DIAMONDS, Rank.EIGHT);
		cards.add(card1);
		cards.add(card2);
		
		CardSet cardSet = new CardSet(cards);
		ArrayList<Card> clubs = cardSet.getRank(Rank.EIGHT);
		
		assertEquals(clubs.get(0), card2);
	}

	@Test
	void testRemoveByRank() {
		ArrayList<Card> cards = new ArrayList<Card>();
		Card card1 = new Card(Suit.CLUBS, Rank.ACE);
		Card card2 = new Card(Suit.DIAMONDS, Rank.EIGHT);
		Card card3 = new Card(Suit.DIAMONDS, Rank.ACE);
		cards.add(card1);
		cards.add(card2);
		cards.add(card3);
		
		CardSet cardSet = new CardSet(cards);
		ArrayList<Card> clubs = cardSet.removeByRank(Rank.ACE, 2);
		
		assertEquals(clubs.get(0), card2);
	}

}
