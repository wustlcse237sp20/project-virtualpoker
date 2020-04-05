package poker;

import java.util.ArrayList;

public class Flop {
	public ArrayList<Card> flop = new ArrayList<Card>();

	public Flop() {
	
	}

	public Card getFirstCard() {
		return this.flop.get(0);
	}


	public Card getSecondCard() {
		return this.flop.get(1);
	}

	
	public Card getThirdCard() {
		return this.flop.get(1);
	}

	public void setCards(Deck d) {
		for(int i=0; i<3; i++) {
			flop.add(d.deal());
		}
	}
	
	public void newFlopCard(Deck d) {
		flop.add(d.deal());
	}
}
