package poker;

public class Player {
	String name;
	Hand hand;
	int money;
	
	public Player(String name, Hand hand, int money) {
		this.name = name;
		this.hand = hand;
		this.money = money;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Hand getHand() {
		return hand;
	}

	public void setHand(Hand hand) {
		this.hand = hand;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}
	
	
}
