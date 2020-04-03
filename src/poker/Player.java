package poker;

public class Player {
	String name;
	Hand hand;
	int money;

	public Player(String name, int money) {
		this.name = name;
		this.money = money;
	}

	public String getName() {
		return name;
	}

	public Hand getHand() {
		return hand;
	}

	public int getMoney() {
		return money;
	}

	public int bet(int bet) {
		if (this.money >= bet) {
			this.money -= bet;
			return bet;
		} else {
			int currentMoney = this.money;
			this.money = 0;
			return currentMoney;
		}
	}
}
