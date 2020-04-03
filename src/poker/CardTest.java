package poker;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CardTest {

	@Test
	void testCreateCard() {
		Rank r = new Rank(0, "TWO");
		Card c = new Card(Suits.HEARTS, r);
		assertEquals(c.getSuit(), Suits.HEARTS);
		assertEquals(c.getRank().toString(), "TWO");
		assertEquals(c.toString(), "TWO of HEARTS");
	}

}
