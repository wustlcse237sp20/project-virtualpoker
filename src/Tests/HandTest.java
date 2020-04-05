package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import poker.Card;
import poker.Hand;
import poker.Ranks;
import poker.Suits;

class HandTest {

	@Test
	void testCreateHand() {
		Card tenOfHearts = new Card(Suits.HEARTS, Ranks.TEN);
		Card aceOfDiamonds = new Card(Suits.DIAMONDS, Ranks.ACE);
		Hand h = new Hand();
		assertEquals(h.getFirstCard().getRank(), Ranks.TEN);
	}

}
