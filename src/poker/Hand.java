package poker;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

public class Hand {
	public Card[] hand = new Card[2];

	public enum HandRank {
		ROYAL_FLUSH, STRAIGHT_FLUSH, FOUR_OF_A_KIND, FULL_HOUSE, FLUSH, STRAIGHT, THREE_OF_A_KIND, TWO_PAIR, PAIR,
		HIGH_CARD,
	}

	public Hand() {
	}

	public Card[] getHand() {
		return hand;
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
		for(int i = 0; i < 2; i ++) {
			allCards[i] = hand[i];
		}
		for(int j = 0; j < 5; j++) {
			allCards[j+2] = communityCards[j]; 
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
		return isFlush(allCards) && isStraight(allCards);
	}

	public boolean isStraight(Card[] allCards) {
		boolean isStraight = false;
		Arrays.sort(allCards, byRank);
		int numOfCardsInRow = 0;
		int position = 0;

		while (position < allCards.length - 1 && !isStraight) {
			if (allCards[position + 1].getRank().getValue() - allCards[position].getRank().getValue() == 1) {
				numOfCardsInRow++;
				if (numOfCardsInRow == 4) {
					isStraight = true;
				} else {
					position++;
				}
			} else {
				numOfCardsInRow = 0;
				position++;
			}
		}
		return isStraight;
	}

	public boolean isFlush(Card[] allCards) {
		int numOfHearts = 0;
		int numOfSpades = 0;
		int numOfClubs = 0;
		int numOfDiamonds = 0;
		for (Card c : allCards) {
			switch (c.getSuit()) {
			case HEARTS:
				numOfHearts++;
				break;
			case SPADES:
				numOfSpades++;
				break;
			case CLUBS:
				numOfClubs++;
				break;
			case DIAMONDS:
				numOfDiamonds++;
				break;
			}
		}
		return (numOfHearts >= 5 || numOfSpades == 5 || numOfClubs == 5 || numOfDiamonds == 5);
	}

	public Comparator<Card> byRank = (Card firstCard, Card secondCard) -> {
		if (firstCard.getRank().getValue() < secondCard.getRank().getValue()) {
			return -1;
		} else {
			return 1;
		}
	};

	private boolean isThreeOfAKind(Card[] allCards) {
		int cardRepeats = 1;
		int i = 0;
		int k = i + 1;
		while (i < allCards.length) {
			System.out.println("First while");
		    cardRepeats = 1;
		    while (k < allCards.length) {
		    	System.out.println("Second while");
		        if (allCards[i].getRank().getValue() == allCards[k].getRank().getValue()) {
		            cardRepeats++;
		            System.out.println(cardRepeats);
		            if (cardRepeats == 3) {
		                return true;
		            }
		        }
		        k++;
		    }
		    i++;
		    k = i+1;
		}
		return false;
	}

	private boolean isTwoPair(Card[] allCards) {
		int cardRepeats = 1;
		int numOfCardRepeats = 0;
		int i = 0;
		int k = i + 1;
		while (i < allCards.length) {
			cardRepeats = 1;
			while (k < allCards.length) {
				if (allCards[i].getRank().getValue() == allCards[k].getRank().getValue()) {
					cardRepeats++;
					if (cardRepeats == 2) {
						cardRepeats = 1;
						numOfCardRepeats++;
						if (numOfCardRepeats == 2) {
							return true;
						}
					}

				}
				k++;
			}
			i++;
		    k = i+1;
		}
		return false;
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
						return true;
					}
				}
				k++;
			}
			i++;
		    k = i+1;
		}
		return false;
	}

	private boolean isFullHouse(Card[] allCards) {
		Arrays.sort(allCards, byRank);
		int noOfRepeats = 1;
		boolean isThreeOfAKind = false;
		boolean isTwoOfAKind = false;
		for (int i = 0; i < allCards.length - 1; i++) {
			if (allCards[i].getRank().getValue() == allCards[i + 1].getRank().getValue()) {
				noOfRepeats++;
				if (noOfRepeats == 3) {
					isThreeOfAKind = true;
					noOfRepeats = 1;
				} else if (noOfRepeats == 2) {
					isTwoOfAKind = true;
					noOfRepeats = 1;
				}
			} else {
				noOfRepeats = 1;
			}
		}
		return (isTwoOfAKind && isThreeOfAKind);

	}

	private boolean isFourOfAKind(Card[] allCards) {
		int cardRepeats = 1;
		int i = 0;
		int k = i + 1;
		while (i < allCards.length) {
			cardRepeats = 1;
			while (k < allCards.length) {
				if (allCards[i].getRank().getValue() == allCards[k].getRank().getValue()) {
					cardRepeats++;
					if (cardRepeats == 4) {
						return true;
					}
				}
				k++;
			}
			i++;
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
}
