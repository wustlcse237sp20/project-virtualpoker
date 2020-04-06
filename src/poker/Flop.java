package poker;

import java.util.ArrayList;

public class Flop {
	public ArrayList<Card> flop = new ArrayList<Card>();

	public Flop() {
	
	}

	public Card getFirstCard() {
		return this.flop.get(0);
	}

	public void setFirstCard(Card firstCard) {
		this.flop.set(0, firstCard);
	}

	public Card getSecondCard() {
		return this.flop.get(1);
	}
	public void setSecondCard(Card SecondCard) {
		this.flop.set(1, SecondCard);
	}

	
	public Card getThirdCard() {
		return this.flop.get(2);
	}
	
	public void setThirdCard(Card thirdCard) {
		this.flop.set(2, thirdCard);
	}

	//public void setCards(Deck d) {		
		//for(int i=0; i<3; i++) {
			//flop.add(d.deal());
		//}
	//}
	
	public void newFlopCard(Deck d) {
		flop.add(d.deal());
	}
}
