package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import poker.Card;
import poker.ComputerPlayer;
import poker.Game;
import poker.Player;

public class newPokerTable {

	private static JFrame frame;
	private static Game pokerGame;

	static ArrayList<Card> communityCards = new ArrayList<Card>();
	static ArrayList<Card> playerHandArray = new ArrayList<Card>();
	static ArrayList<Card> computerHandArray = new ArrayList<Card>();

	static int playersMoney = 0;
	static int computersMoney = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		String playerName = (String) JOptionPane.showInputDialog(frame, "Input Player Name:", "Input Name",
				JOptionPane.PLAIN_MESSAGE, null, null, null);

		Player player = new Player(playerName, 100);
		ComputerPlayer computer = new ComputerPlayer("Computer", 100);

		pokerGame = new Game(player, computer);

		playersMoney = player.getMoney();
		computersMoney = computer.getMoney();

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					newPokerTable window = new newPokerTable(playerName);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		pokerGame.startGame(playerName);

		while (!pokerGame.checkForWinner()) {
			displayMessage("Round: New Round!");

			playersMoney = player.getMoney();
			computersMoney = computer.getMoney();

			pokerGame.playRound();

			pokerGame.doBlinds();

			playerHandArray = pokerGame.playPreflop();

			// display player hand cards
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						newPokerTable window = new newPokerTable(playerName);
						window.frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});

			pokerGame.playBettingRound(pokerGame.isPreflop());

			playersMoney = player.getMoney();
			computersMoney = computer.getMoney();

			if (!pokerGame.isWinner()) {

				// Play Flop
				displayMessage("Round: FLOP!");

				communityCards = pokerGame.playFlop();

				// display the community cards
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							newPokerTable window = new newPokerTable(playerName);
							window.frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});

				// call palyBetting Round in FLOP
				pokerGame.playBettingRound(pokerGame.isPreflop());

				playersMoney = player.getMoney();
				computersMoney = computer.getMoney();
			}

			if (!pokerGame.isWinner()) {
				// Play Turn
				displayMessage("Round: TURN!");
				communityCards = pokerGame.playTurn();

				// display the community cards
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							newPokerTable window = new newPokerTable(playerName);
							window.frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});

				// call palyBetting Round in TURN
				pokerGame.playBettingRound(pokerGame.isPreflop());

				playersMoney = player.getMoney();
				computersMoney = computer.getMoney();
			}

			if (!pokerGame.isWinner()) {
				// Play River
				displayMessage("Round: RIVER!");

				communityCards = pokerGame.playRiver();

				// display the community cards
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							newPokerTable window = new newPokerTable(playerName);
							window.frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});

				// call palyBetting Round in RIVER
				pokerGame.playBettingRound(pokerGame.isPreflop());

				playersMoney = player.getMoney();
				computersMoney = computer.getMoney();

			}

			if (!pokerGame.isWinner()) {
				// Play ShowDown
				displayMessage("Round: SHOWDOWN!");
				computerHandArray = pokerGame.getComputerHandArray();
				pokerGame.playShowdown();
			}

			pokerGame.doRoundWinner();

			playersMoney = player.getMoney();
			computersMoney = computer.getMoney();

		}

	}

	/**
	 * Create the application.
	 */
	public newPokerTable(String playerName) {
		initialize(playerName);
	}

	public static void displayMessage(String message) {
		JOptionPane.showMessageDialog(frame, message);
	}

	public static int getUserInput(String message) {
		int playerStrategy = Integer.parseInt(JOptionPane.showInputDialog(message));
		return playerStrategy;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String playerName) {

		String playersFirstCard = "backOfCard";
		String playersSecondCard = "backOfCard";

		String computerFirstCard = "backOfCard";
		String computerSecondCard = "backOfCard";

		String communityCard1 = "";
		String communityCard2 = "";
		String communityCard3 = "";
		String communityCard4 = "";
		String communityCard5 = "";

		frame = new JFrame();
		frame.setBounds(100, 100, 983, 730);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.WEST);
		panel.setLayout(new GridLayout(3, 1, 0, 0));

		JButton btnNewButton = new JButton("CALL");
		panel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("RAISE");
		panel.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("CHECK");
		panel.add(btnNewButton_2);

		JLayeredPane layeredPane = new JLayeredPane();
		frame.getContentPane().add(layeredPane, BorderLayout.CENTER);

		if (communityCards.size() != 0) {

			communityCard1 = pokerGame.firstCommunityCardToString();
			communityCard2 = pokerGame.secondCommunityCardToString();
			communityCard3 = pokerGame.thirdCommunityCardToString();

			JLabel lblNewLabel_1 = new JLabel("communityCard1");
			lblNewLabel_1.setBounds(217, 187, 75, 98);
			Image imgCard1 = new ImageIcon(this.getClass().getResource("/" + communityCard1 + ".png")).getImage();
			lblNewLabel_1.setIcon(new ImageIcon(imgCard1));
			layeredPane.add(lblNewLabel_1);

			JLabel lblNewLabel_2 = new JLabel("communityCard2");
			lblNewLabel_2.setBounds(304, 187, 75, 98);
			Image imgCard2 = new ImageIcon(this.getClass().getResource("/" + communityCard2 + ".png")).getImage();
			lblNewLabel_2.setIcon(new ImageIcon(imgCard2));
			layeredPane.add(lblNewLabel_2);

			JLabel lblNewLabel_3 = new JLabel("communityCard3");
			lblNewLabel_3.setBounds(389, 187, 75, 98);
			Image imgCard3 = new ImageIcon(this.getClass().getResource("/" + communityCard3 + ".png")).getImage();
			lblNewLabel_3.setIcon(new ImageIcon(imgCard3));
			layeredPane.add(lblNewLabel_3);
		}

		if (communityCards.size() >= 4) {
			JLabel lblNewLabel_4 = new JLabel("communityCard4");
			lblNewLabel_4.setBounds(474, 187, 75, 98);
			Image imgCard4 = new ImageIcon(this.getClass().getResource("/" + communityCard4 + ".png")).getImage();
			lblNewLabel_4.setIcon(new ImageIcon(imgCard4));
			layeredPane.add(lblNewLabel_4);
		}

		if (communityCards.size() == 5) {
			JLabel lblNewLabel_5 = new JLabel("communityCard5");
			lblNewLabel_5.setBounds(559, 187, 75, 98);
			Image imgCard5 = new ImageIcon(this.getClass().getResource("/" + communityCard5 + ".png")).getImage();
			lblNewLabel_5.setIcon(new ImageIcon(imgCard5));
			layeredPane.add(lblNewLabel_5);
		}

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(72, 65, 729, 366);
		Image img = new ImageIcon(this.getClass().getResource("/poker_table.png")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img));
		layeredPane.add(lblNewLabel);

		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.DARK_GRAY));
		lblNewLabel_6.setBounds(72, 471, 307, 190);
		layeredPane.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("Players Name");
		lblNewLabel_7.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_7.setBounds(91, 483, 100, 14);
		layeredPane.add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("Players Money");
		lblNewLabel_8.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_8.setBounds(241, 484, 107, 14);
		layeredPane.add(lblNewLabel_8);

		JLabel lblNewLabel_9 = new JLabel(String.valueOf(playersMoney));
		lblNewLabel_9.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_9.setBounds(251, 509, 82, 14);
		layeredPane.add(lblNewLabel_9);

		JLabel lblNewLabel_10 = new JLabel(playerName);
		lblNewLabel_10.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_10.setBounds(101, 509, 75, 14);
		layeredPane.add(lblNewLabel_10);

		if (playerHandArray.size() == 2) {
			playersFirstCard = pokerGame.PlayerFirstCardtoString();
			playersSecondCard = pokerGame.PlayerSecondCardtoString();

			JLabel lblNewLabel_11 = new JLabel("playersCard1");
			lblNewLabel_11.setBounds(101, 551, 75, 98);
			Image imgCard6 = new ImageIcon(this.getClass().getResource("/" + playersFirstCard + ".png")).getImage();
			lblNewLabel_11.setIcon(new ImageIcon(imgCard6));
			layeredPane.add(lblNewLabel_11);

			JLabel lblNewLabel_12 = new JLabel("playersCard2");
			lblNewLabel_12.setBounds(251, 551, 75, 98);
			Image imgCard7 = new ImageIcon(this.getClass().getResource("/" + playersFirstCard + ".png")).getImage();
			lblNewLabel_12.setIcon(new ImageIcon(imgCard7));
			layeredPane.add(lblNewLabel_12);
		}

		JLabel lblNewLabel_13 = new JLabel("");
		lblNewLabel_13.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.DARK_GRAY));
		lblNewLabel_13.setBounds(494, 471, 307, 190);
		layeredPane.add(lblNewLabel_13);

		JLabel lblNewLabel_14 = new JLabel("Computers Name");
		lblNewLabel_14.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_14.setBounds(511, 484, 123, 14);
		layeredPane.add(lblNewLabel_14);

		JLabel lblNewLabel_15 = new JLabel("Computers Money");
		lblNewLabel_15.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_15.setBounds(655, 484, 129, 14);
		layeredPane.add(lblNewLabel_15);

		JLabel lblNewLabel_16 = new JLabel("Computer");
		lblNewLabel_16.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_16.setBounds(544, 509, 58, 14);
		layeredPane.add(lblNewLabel_16);

		JLabel lblNewLabel_17 = new JLabel(String.valueOf(computersMoney));
		lblNewLabel_17.setBounds(691, 509, 58, 14);
		layeredPane.add(lblNewLabel_17);

		if (computerHandArray.size() != 0) {
			computerFirstCard = pokerGame.ComputerFirstCardtoString();
			computerSecondCard = pokerGame.ComputerSecondCardtoString();
		}

		JLabel lblNewLabel_18 = new JLabel("computersCard1");
		lblNewLabel_18.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lblNewLabel_18.setBounds(529, 551, 75, 98);
		Image imgCard8 = new ImageIcon(this.getClass().getResource("/" + computerFirstCard + ".jpg")).getImage();
		lblNewLabel_18.setIcon(new ImageIcon(imgCard8));
		layeredPane.add(lblNewLabel_18);

		JLabel lblNewLabel_19 = new JLabel("computersCard2");
		lblNewLabel_19.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lblNewLabel_19.setBounds(678, 551, 75, 98);
		Image imgCard9 = new ImageIcon(this.getClass().getResource("/" + computerFirstCard + ".jpg")).getImage();
		lblNewLabel_19.setIcon(new ImageIcon(imgCard9));
		layeredPane.add(lblNewLabel_19);
	}

}
