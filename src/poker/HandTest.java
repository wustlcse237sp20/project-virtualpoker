package poker;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HandTest {

	@Test
	void testCreateHand() {
		Card tenOfHearts = new Card(Suits.HEARTS, Ranks.TEN);
		Card aceOfDiamonds = new Card(Suits.DIAMONDS, Ranks.ACE);
		Hand h = new Hand();
		assertEquals(h.getFirstCard().getRank(), Ranks.TEN);
	}

}
