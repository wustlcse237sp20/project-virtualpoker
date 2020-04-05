package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import poker.Card;
import poker.Deck;
import poker.Ranks;
import poker.Suits;
import poker.calculateHand;

class DeckTest {

//	@Test
//	void testCreateDeckAndDeal() {
//		Deck d = new Deck();
//		Card c = d.deal();
//		assertEquals(c.getSuit(), Suits.SPADES);
//		assertEquals(c.getRank(), Ranks.ACE);
//	}
	
	
	@Test
	void testCreateDeck() {
		Deck d = new Deck();
		ArrayList<Card> cards = d.deckList();
		Card firstCard = cards.get(0);
		assertEquals(firstCard.getSuit(), Suits.SPADES);
		assertEquals(firstCard.getRank(), Ranks.ACE);
	}
	
	
	@Test
	void testCheckCardDeal() {
		Deck d = new Deck();
		Card c = d.deal();
		assertEquals(c.getSuit(), Suits.SPADES);
		assertEquals(c.getRank(), Ranks.ACE);
	}
		 
}
