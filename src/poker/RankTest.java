package poker;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RankTest {

	@Test
	void testIsOneLower() {
		boolean a = Rank.THREE.isOneLower(Rank.TWO);
		assertEquals(true,a);
		
		boolean b = Rank.FOUR.isOneLower(Rank.THREE);
		assertEquals(true,b);
		
		boolean c = Rank.ACE.isOneLower(Rank.KING);
		assertEquals(true,c);

		boolean d = Rank.QUEEN.isOneLower(Rank.JACK);
		assertEquals(true,d);
		
		boolean e = Rank.KING.isOneLower(Rank.QUEEN);
		assertEquals(true,e);
		
	}

}
