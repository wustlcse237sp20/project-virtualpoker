package poker;

public class Rank {

	private int value;
	private String rank;
	
	public Rank() {
	}
	
	public Rank(int value, String rank) {
	    this.value = value;
	    this.rank = rank;
	}
	
	public int getValue() {
	    return value;
	}
	
	@Override
	public String toString() {
	    return rank;
	}
	
}

