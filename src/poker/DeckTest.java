package poker;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DeckTest {

	@Test
	void testCreateDeckAndDeal() {
		Deck d = new Deck();
		Card c = d.deal();
		assertEquals(c.getSuit(), Suit.SPADES);
		assertEquals(c.getRank(), Rank.ACE);
	}
	
	@Test
	void testCreateDeckAndShuffle(){
		Deck d = new Deck();
		Card a = d.deal();
		d.shuffle();
		Card c = d.deal();
		assertNotEquals(c.getSuit(), a.getSuit());
		assertNotEquals(c.getRank(), a.getRank());
	}
	
	@Test
	void testCreateDeckAndDeal1() {
		Deck d = new Deck();
		Card c = d.deal();
		
		assertEquals(c.getSuit(), Suit.SPADES);
		assertEquals(c.getRank(), Rank.ACE);
		Deck deck = new Deck();
		Card a = deck.deal();
		Card b = deck.deal();
		assertEquals(b.getSuit(), Suit.SPADES);
		assertNotEquals(b.getRank(), Rank.ACE);
		
	}
	
	
	

}