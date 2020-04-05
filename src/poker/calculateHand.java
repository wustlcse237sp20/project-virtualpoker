package poker;

import java.util.ArrayList;
import java.util.Arrays;

public class calculateHand {

	public static final int highestCard = 1;
	public static final int pair = 2;
	public static final int twoPair = 3;
	public static final int threeOfAKind = 4;
	public static final int straight = 5;
	public static final int flush = 6;
	public static final int fullHouse = 7;
	public static final int fourOfAKind = 8;
	public static final int straightFlush = 9;
	public static final int royalFlush = 10;
	
	public calculateHand(ArrayList<Card> fullHand) {
		checkInput(fullHand);
		 Card[] handArray = new Card[7];
		 
		 for(int i=0; i < 7; i++) {
			 handArray[i] = fullHand.get(i);
		 }
		 
		 Arrays.sort(handArray);
		 public Score myScore = new Score();
		
	}
	
	public void checkInput(ArrayList<Card> fullHand){
		if(fullHand.isEmpty()) {
			throw new IllegalArgumentException("Calculate Hand called with no cards passed");
		}
		else if(fullHand.size() !=7) {
			throw new IllegalArgumentException("Calculate Hand called with wrong number of cards");
		}
	}

	
	
}
