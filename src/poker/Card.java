package poker;

public class Card {
	
	Suits suit;
	Ranks rank;
	
	public Card(Suits suit, Ranks rank) {
		this.suit = suit;
		this.rank = rank;
	}

	public Suits getSuit() {
		return suit;
	}

	public Ranks getRank() {
		return rank;
	}


}
