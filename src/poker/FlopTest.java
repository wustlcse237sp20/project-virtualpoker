package poker;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

class FlopTest {

		@Test
		void testCreateCard() {
			Flop f = new Flop();
			Card c1 = new Card(Suits.HEARTS, Ranks.TEN);
			Card c2 = new Card(Suits.HEARTS, Ranks.NINE);
			Card c3 = new Card(Suits.HEARTS, Ranks.EIGHT);
			f.setFirstCard(c1);
			f.setSecondCard(c2);
			f.setThirdCard(c3);
			
			assertEquals(f.getFirstCard(), new Card(Suits.HEARTS, Ranks.TEN));
			assertEquals(f.getSecondCard(), new Card(Suits.HEARTS, Ranks.NINE));
			assertEquals(f.getThirdCard(), new Card(Suits.HEARTS, Ranks.EIGHT));
		}

	}


