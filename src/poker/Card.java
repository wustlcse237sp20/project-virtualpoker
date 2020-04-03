package poker;

public class Card {
	
	private Suits suit;
	private Rank rank;
	
	public Card(Suits suit, Rank rank) {
		this.suit = suit;
		this.rank = rank;
	}

	public Suits getSuit() {
		return suit;
	}

	public Rank getRank() {
		return rank;
	}
	
	public @Override String toString() {
		return rank + " of " + suit;
				}
}
