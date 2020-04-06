package poker;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CardTest {

	@Test
	void testCreateCard() {
		Card c = new Card(Suit.HEARTS, Rank.TWO);
		assertEquals(c.getSuit(), Suit.HEARTS);
		assertEquals(c.getRank(), Rank.TWO);
		assertEquals(c.toString(), "TWO of HEARTS");
	}
	
}