package poker;

public class Play {
	public static void main(String[] args) {
		Player player = new Player("Player", 100);
		ComputerPlayer computer = new ComputerPlayer("Computer", 100);
		Game game = new Game(player, computer);
		// game.startGame();
	}
}
