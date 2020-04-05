package poker;

import java.util.Arrays;
import java.util.Comparator;

public class Hand {
	public Card[] hand = new Card[2];
	
	public Rank high;
	public Rank low;
	public Card[] kickers;
    
    protected boolean usesLow = false;
    protected boolean usesAllCards = false;
    

	public HandRank bestRank;

	public enum HandRank {
		ROYAL_FLUSH, STRAIGHT_FLUSH, FOUR_OF_A_KIND, FULL_HOUSE, FLUSH, STRAIGHT, THREE_OF_A_KIND, TWO_PAIR, PAIR,
		HIGH_CARD,
	}

	public Hand() {
	}

	public Card[] getHand() {
		return hand;
	}
	
	public Card[] getKickers() {
		return kickers;
	}

	public void displayHand() {
		for (int i = 0; i < 2; i++) {
			System.out.println(hand[i]);
		}
	}

	public HandRank determineHandRank(Card[] communityCards) {
		Card[] allCards = concatenateArrays(communityCards);
		if (isRoyalFlush(allCards)) {
			return HandRank.ROYAL_FLUSH;
		} else if (isStraightFlush(allCards)) {
			return HandRank.STRAIGHT_FLUSH;
		} else if (isFourOfAKind(allCards)) {
			return HandRank.FOUR_OF_A_KIND;
		} else if (isFullHouse(allCards)) {
			return HandRank.FULL_HOUSE;
		} else if (isFlush(allCards)) {
			return HandRank.FLUSH;
		} else if (isStraight(allCards)) {
			return HandRank.STRAIGHT;
		} else if (isThreeOfAKind(allCards)) {
			return HandRank.THREE_OF_A_KIND;
		} else if (isTwoPair(allCards)) {
			return HandRank.TWO_PAIR;
		} else if (isPair(allCards)) {
			return HandRank.PAIR;
		} else {
			return HandRank.HIGH_CARD;
		}
	}

	public Card[] concatenateArrays(Card[] communityCards) {
		Card[] allCards = new Card[7];
		for (int i = 0; i < 2; i++) {
			allCards[i] = hand[i];
		}
		for (int j = 0; j < 5; j++) {
			allCards[j + 2] = communityCards[j];
		}
		return allCards;
	}

	public boolean isRoyalFlush(Card[] allCards) {
		if (isFlush(allCards)) {
			boolean aceExists = false;
			boolean kingExists = false;
			boolean queenExists = false;
			boolean jackExists = false;
			boolean tenExists = false;
			for (Card c : allCards) {
				switch (c.getRank().toString()) {
				case "ACE":
					aceExists = true;
					break;
				case "KING":
					kingExists = true;
					break;
				case "QUEEN":
					queenExists = true;
					break;
				case "JACK":
					jackExists = true;
					break;
				case "TEN":
					tenExists = true;
					break;
				}
			}
			return (aceExists && kingExists && queenExists && jackExists && tenExists);
		} else {
			return false;
		}
	}

	private boolean isStraightFlush(Card[] allCards) {
		usesLow = false;
	    usesAllCards = false;
		return isFlush(allCards) && isStraight(allCards);
	}
	
	private boolean isFourOfAKind(Card[] allCards) {
		usesLow = false;
		usesAllCards = false;
		
		int cardRepeats = 1;
		int i = 0;
		int k = i + 1;
		
		while (i < allCards.length) {
			cardRepeats = 1;
			while (k < allCards.length) {
				if (allCards[i].getRank().getValue() == allCards[k].getRank().getValue()) {
					cardRepeats++;
					if (cardRepeats == 4) {
						high = allCards[i].getRank();
						kickers = removeByRank(high, allCards, 4);
						return true;
					}
				}
				k++;
			}
			i++;
			k = i + 1;
		}
		return false;
	}
	
	public boolean isFlush(Card[] allCards) {
		usesLow = false;
		usesAllCards = true;
		
		Card[] hearts = new Card[7];
		Card[] spades = new Card[7];
		Card[] clubs = new Card[7];
		Card[] diamonds = new Card[7];
		
		int numOfHearts = 0;
		int numOfSpades = 0;
		int numOfClubs = 0;
		int numOfDiamonds = 0;
		for (Card c : allCards) {
			switch (c.getSuit()) {
			case HEARTS:
				hearts[numOfHearts] = c;
				numOfHearts++;
				break;
			case SPADES:
				spades[numOfSpades] = c;
				numOfSpades++;
				break;
			case CLUBS:
				clubs[numOfClubs] = c;
				numOfClubs++;
				break;
			case DIAMONDS: 
				diamonds[numOfDiamonds] = c;
				numOfDiamonds++;
				break;
			}
		}
		
		if(numOfHearts >= 5) {
			Arrays.sort(hearts);
			high = hearts[numOfHearts].getRank();
		}
		else if(numOfSpades >= 5) {
			Arrays.sort(spades);
			high = spades[numOfSpades].getRank();
		}
		if(numOfClubs >= 5) {
			Arrays.sort(clubs);
			high = clubs[numOfClubs].getRank();
		}
		if(numOfDiamonds >= 5) {
			Arrays.sort(diamonds);
			high = diamonds[numOfDiamonds].getRank();
		}
		
		return (numOfHearts >= 5 || numOfSpades >= 5 || numOfClubs >= 5 || numOfDiamonds >= 5);
	}


	public boolean isStraight(Card[] allCards) {
		usesLow = false;
		usesAllCards = false;
		
		Arrays.sort(allCards, byRank);
		int numOfCardsInRow = 0;
		int position = 0;

		while (position < allCards.length - 1) {
			if (allCards[position + 1].getRank().getValue() - allCards[position].getRank().getValue() == 1) {
				numOfCardsInRow++;
				if (numOfCardsInRow == 4) {
					high = allCards[position+1].getRank();
					return true;
				} else {
					position++;
				}
			} else {
				numOfCardsInRow = 0;
				position++;
			}
		}
		return false;
	}

	
	public Comparator<Card> byRank = (Card firstCard, Card secondCard) -> {
		if (firstCard.getRank().getValue() < secondCard.getRank().getValue()) {
			return -1;
		} else {
			return 1;
		}
	};
	
	private boolean isFullHouse(Card[] allCards) {
		usesLow = true;
		usesAllCards = false;
		high = null;
		low = null;
		
		int[] values = new int[13];
		for (int i = 0; i < allCards.length; i++) {
			values[allCards[i].getRank().getValue()]++;
		}
		boolean isThreeOfAKind = false;
		boolean isTwoOfAKind = false;
		for (int i = 0; i < 13; i++)
			if (values[i] == 3) {
				isThreeOfAKind = true;
			}
			else if(values[i] == 2) {
				isTwoOfAKind = true;
			}
		return isThreeOfAKind && isTwoOfAKind;
	}
	
	private boolean isAFullHouse(Card[] allCards) {
		int cardRepeats = 1;
		int i = 0;
		int k = i + 1;
		
		boolean isThreeOfAKind = false;
		boolean isTwoOfAKind = false;
		
		while (i < allCards.length) {
			cardRepeats = 1;
			while (k < allCards.length) {
				if (allCards[i].getRank().getValue() == allCards[k].getRank().getValue()) {
					cardRepeats++;
					if (cardRepeats == 3) {
						high = allCards[i].getRank();
						kickers = this.removeByRank(high, allCards, 3);
						return isPair(kickers);
					}
				}
				k++;
			}
			i++;
			k = i + 1;
		}
		return false;
	}

	private boolean isThreeOfAKind(Card[] allCards) {
		int cardRepeats = 1;
		int i = 0;
		int k = i + 1;
		while (i < allCards.length) {
			cardRepeats = 1;
			while (k < allCards.length) {
				if (allCards[i].getRank().getValue() == allCards[k].getRank().getValue()) {
					cardRepeats++;
					if (cardRepeats == 3) {
						high = allCards[i].getRank();
						kickers = this.removeByRank(high, allCards, 3);
						return true;
					}
				}
				k++;
			}
			i++;
			k = i + 1;
		}
		return false;
	}

	public boolean isTwoPair(Card[] allCards) {
		int[] values = new int[13];
		for (int i = 0; i < allCards.length; i++) {
			values[allCards[i].getRank().getValue()]++;
		}
		int count = 0;
		for (int i = 0; i < 13; i++)
			if (values[i] == 2) {
				count++;
			}
		return (count == 2);
	}

	private boolean isPair(Card[] allCards) {
		int cardRepeats = 1;
		int i = 0;
		int k = i + 1;
		while (i < allCards.length) {
			cardRepeats = 1;
			while (k < allCards.length) {
				if (allCards[i].getRank().getValue() == allCards[k].getRank().getValue()) {
					cardRepeats++;
					if (cardRepeats == 2) {
						high = allCards[i].getRank();
						kickers = this.removeByRank(high, allCards, 2);
						return true;
					}
				}
				k++;
			}
			i++;
			k = i + 1;
		}
		return false;
	}

	

	public Card getHighCard(Card[] allCards) {
		Arrays.sort(allCards, byRank);
		return allCards[0];
	}

	public Card getHandHighCard() {
		Arrays.sort(hand, byRank);
		return hand[0];
	}
	
	public Card[] removeByRank(Rank rank, Card[] allCards, int count) { 
		int remaining = count;
		Card[] allCardsWithRemovedRanks = new Card[7-count];
		int i = 0;
		for (Card card : allCards) {
            if (card.getRank().equals(rank) && remaining > 0) {
                remaining--;
            } else {
                allCardsWithRemovedRanks[i] = card;
                i++;
            }
        }
		return allCardsWithRemovedRanks;
	}
	
	public int compareTo(Hand opponentHand, Card[] communityCards) {
		HandRank bestRank = this.determineHandRank(communityCards);
		HandRank bestOpponentRank = opponentHand.determineHandRank(communityCards);
		
		if(bestRank == bestOpponentRank) {
			
		}
		else {
			return -1 * bestRank.compareTo(bestOpponentRank);
		}
	}
}
