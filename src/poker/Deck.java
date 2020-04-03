package poker;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	private ArrayList<Card> cards = new ArrayList<Card>();
	private ArrayList<Rank> ranks = new ArrayList<Rank>();

	public Deck() {
		generateRanks();
		cards = new ArrayList<Card>();
		for (Suits suit : Suits.values()) {
			for (Rank rank : ranks) {
				Card card = new Card(suit, rank);
				cards.add(card);
			}
		}
	}

	private void generateRanks() {
		this.ranks.add(new Rank(0, "TWO"));
		this.ranks.add(new Rank(1, "THREE"));
		this.ranks.add(new Rank(2, "FOUR"));
		this.ranks.add(new Rank(3, "FIVE"));
		this.ranks.add(new Rank(4, "SIX"));
		this.ranks.add(new Rank(5, "SEVEN"));
		this.ranks.add(new Rank(6, "EIGHT"));
		this.ranks.add(new Rank(7, "NINE"));
		this.ranks.add(new Rank(8, "TEN"));
		this.ranks.add(new Rank(9, "JACK"));
		this.ranks.add(new Rank(10, "QUEEN"));
		this.ranks.add(new Rank(11, "KING"));
		this.ranks.add(new Rank(12, "ACE"));
	}

	public void shuffle() {
		cards = new ArrayList<Card>();
		for (Suits suit : Suits.values()) {
			for (Rank rank : ranks) {
				Card card = new Card(suit, rank);
				cards.add(card);
			}
		}
		Collections.shuffle(this.cards);
	}

	public Card deal() {
		Card topCard = this.cards.get(0);
		this.cards.remove(0);
		return topCard;
	}
}
