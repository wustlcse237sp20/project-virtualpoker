package poker;

import java.util.ArrayList;

public class Hand {
	public ArrayList<Card> hand = new ArrayList<Card>();

	public Hand() {
	}

	public Card getFirstCard() {
		return this.hand.get(0);
	}

	public void setFirstCard(Card firstCard) {
		this.hand.set(0, firstCard);
	}

	public Card getSecondCard() {
		return this.hand.get(1);
	}

	public void setSecondCard(Card secondCard) {
		this.hand.set(1, secondCard);
	}

	public void dealtCard(Deck d) {
		hand.add(d.deal());
	}
	
}
