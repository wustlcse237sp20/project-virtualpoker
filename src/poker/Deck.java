package poker;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	private ArrayList<Card> cards = new ArrayList<Card>();

	
	// should it return a list of cards back?
	public Deck() {
		for (Suits suits : Suits.values()) {
			for (Ranks ranks : Ranks.values()) {
				Card card = new Card(suits, ranks);
				cards.add(card);
			}
		}
	}

	public void shuffle() {
		Collections.shuffle(this.cards);
	}

	public Card deal() {
		Card topCard = this.cards.get(0);
		this.cards.remove(0);
		return topCard;
	}
	
	public ArrayList<Card> deckList(){
		return this.cards;
	}
}
