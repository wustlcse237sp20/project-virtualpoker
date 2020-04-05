package Tests;


import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import poker.Card;
import poker.Deck;
import poker.Ranks;
import poker.Suits;
import poker.calculateHand;

class calculateHandTests {

	@Test
	void checkInputForEmptyHand(){
		ArrayList<Card> EmptyHand = new ArrayList<Card>();	
		boolean check = checkInput(EmptyHand);
		Assert.assertEquals(checkInput(EmptyHand),true);
	}
	

	@Test
	void checkInputForWrongNumberOfHand(){
		ArrayList<Card> handWithWrongNumberOfCards = new ArrayList<Card>();
		Card card1 = new Card(Suits.HEARTS, Ranks.FOUR);
		Card card2 = new Card(Suits.SPADES, Ranks.JACK);
		Card card3 = new Card(Suits.HEARTS, Ranks.TEN);
		Card card4 = new Card(Suits.CLUBS, Ranks.FOUR);
		
		handWithWrongNumberOfCards.add(card1);
		handWithWrongNumberOfCards.add(card2);
		handWithWrongNumberOfCards.add(card3);
		handWithWrongNumberOfCards.add(card4);
		assertEquals(checkInput(handWithWrongNumberOfCards),"Calculate Hand called with wrong number of cards");
		
	}
}

