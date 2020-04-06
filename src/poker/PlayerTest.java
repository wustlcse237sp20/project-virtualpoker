package poker;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PlayerTest {
	@Test
	void testCreatePlayer() {
		Hand h = new Hand();
		Player p = new Player("name", h, 10);
		assertEquals(p.getName(), "name");
		assertEquals(p.getHand(), h);
		assertEquals(p.getMoney(), 10);
	}
}
