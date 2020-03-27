package poker;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CardTest {

	@Test
	void testCreateCard() {
		Card c = new Card(Suits.HEARTS, Ranks.TEN);
		assertEquals(c.getSuit(), Suits.HEARTS);
		assertEquals(c.getRank(), Ranks.TEN);
	}

}
