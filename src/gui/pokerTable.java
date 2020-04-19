package gui;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JInternalFrame;
import java.awt.event.ActionListener;
import java.beans.Beans;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import poker.Card;
import poker.ComputerPlayer;
import poker.Game;
import poker.Hand;
import poker.Player;

public class pokerTable {

	private static JFrame frame;
	private static Game pokerGame;

	static ArrayList<Card> communityCards = new ArrayList<Card>();
	static ArrayList<Card> playerHandArray = new ArrayList<Card>();
	static ArrayList<Card> computerHandArray = new ArrayList<Card>();

	static int playersMoney = 0;
	static int computersMoney = 0;

	/**
	 * Launch the application.
	 * 
	 * @wbp.parser.entryPoint
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
					pokerTable window = new pokerTable(playerName);
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
						pokerTable window = new pokerTable(playerName);
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
							pokerTable window = new pokerTable(playerName);
							window.frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});

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
							pokerTable window = new pokerTable(playerName);
							window.frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});

				// call playBetting Round in TURN
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
							pokerTable window = new pokerTable(playerName);
							window.frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});

				// call plyBetting Round in RIVER
				pokerGame.playBettingRound(pokerGame.isPreflop());

				playersMoney = player.getMoney();
				computersMoney = computer.getMoney();

			}

			if (!pokerGame.isWinner()) {
				// Play ShowDown
				displayMessage("Round: SHOWDOWN!");
				computerHandArray = pokerGame.getComputerHandArray();
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							pokerTable window = new pokerTable(playerName);
							window.frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
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
	public pokerTable(String playerName) {
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
		frame.setBounds(100, 100, 957, 660);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.WEST);
		panel.setLayout(new GridLayout(0, 1, 0, 0));

		JButton callButton = new JButton("Call");
		callButton.setFocusable(false);
		callButton.setBackground(Color.GRAY);
		callButton.setForeground(Color.DARK_GRAY);
		callButton.setFont(new Font("Times New Roman", Font.BOLD, 17));
		panel.add(callButton);

		JButton raiseButton = new JButton("Raise");
		raiseButton.setFocusable(false);
		raiseButton.setBackground(Color.GRAY);
		raiseButton.setForeground(Color.DARK_GRAY);
		raiseButton.setFont(new Font("Times New Roman", Font.BOLD, 17));
		panel.add(raiseButton);

		JButton foldButton = new JButton("Fold");
		foldButton.setFocusable(false);
		foldButton.setBackground(Color.GRAY);
		foldButton.setForeground(Color.DARK_GRAY);
		foldButton.setFont(new Font("Times New Roman", Font.BOLD, 17));
		panel.add(foldButton);

		JButton exitButton = new JButton("Exit");
		exitButton.setFocusable(false);
		exitButton.setBackground(Color.GRAY);
		exitButton.setForeground(Color.DARK_GRAY);
		exitButton.setFont(new Font("Times New Roman", Font.BOLD, 17));
		panel.add(exitButton);

		JLayeredPane layeredPane = new JLayeredPane();
		frame.getContentPane().add(layeredPane, BorderLayout.CENTER);

		JLabel lblNewLabel_222 = new JLabel("");
		lblNewLabel_222.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.DARK_GRAY));
		lblNewLabel_222.setBounds(51, 443, 289, 167);
		layeredPane.add(lblNewLabel_222);

		JLabel playerMoneyLabel = new JLabel("Player Money");
		playerMoneyLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		playerMoneyLabel.setBounds(212, 455, 95, 14);
		layeredPane.add(playerMoneyLabel);

		JLabel playerMoneyValue = new JLabel(String.valueOf(playersMoney));
		playerMoneyValue.setBounds(232, 486, 46, 14);
		layeredPane.add(playerMoneyValue);

		JLabel lblNewLabel_32 = new JLabel("Player Name");
		lblNewLabel_32.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_32.setBounds(76, 455, 88, 14);
		layeredPane.add(lblNewLabel_32);

		JLabel lblNewLabel_4 = new JLabel(playerName);
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(76, 486, 75, 14);
		layeredPane.add(lblNewLabel_4);

		// display card box
		JLabel lblNewLabel_2222 = new JLabel("");
		lblNewLabel_2222.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.DARK_GRAY));
		lblNewLabel_2222.setBounds(501, 443, 289, 167);
		layeredPane.add(lblNewLabel_2222);

		JLabel lblNewLabel_1222 = new JLabel("Computer Money");
		lblNewLabel_1222.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_1222.setBounds(650, 455, 125, 14);
		layeredPane.add(lblNewLabel_1222);

		JLabel lblNewLabel_1322 = new JLabel(String.valueOf(computersMoney));
		lblNewLabel_1322.setBounds(692, 486, 46, 14);
		layeredPane.add(lblNewLabel_1322);

		JLabel lblNewLabel_322 = new JLabel("Computer Name");
		lblNewLabel_322.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_322.setBounds(526, 455, 110, 14);
		layeredPane.add(lblNewLabel_322);

		JLabel lblNewLabel_42 = new JLabel("Computer");
		lblNewLabel_42.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_42.setBounds(536, 486, 75, 14);
		layeredPane.add(lblNewLabel_42);

		if (computerHandArray.size() == 0) {
			JLabel computerCard1Label = new JLabel("ComputerCard1");
			computerCard1Label.setBounds(526, 511, 75, 98);
			computerCard1Label.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.DARK_GRAY));
			Image imgCard46 = new ImageIcon(this.getClass().getResource("/" + computerFirstCard + ".jpg")).getImage();
			computerCard1Label.setIcon(new ImageIcon(imgCard46));
			layeredPane.add(computerCard1Label);

			JLabel computerCard2Label = new JLabel("ComputerCard2");
			computerCard2Label.setBounds(670, 511, 75, 98);
			computerCard2Label.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.DARK_GRAY));
			Image imgCard56 = new ImageIcon(this.getClass().getResource("/" + computerSecondCard + ".jpg")).getImage();
			computerCard2Label.setIcon(new ImageIcon(imgCard56));
			layeredPane.add(computerCard2Label);
		}

		if (computerHandArray.size() == 2) {
			computerFirstCard = pokerGame.ComputerFirstCardtoString();
			computerSecondCard = pokerGame.ComputerSecondCardtoString();

			JLabel computerCard1Label = new JLabel("ComputerCard1");
			computerCard1Label.setBounds(526, 511, 75, 98);
			Image imgCard46 = new ImageIcon(this.getClass().getResource("/" + computerFirstCard + ".png")).getImage();
			computerCard1Label.setIcon(new ImageIcon(imgCard46));
			layeredPane.add(computerCard1Label);
			
			JLabel computerCard2Label = new JLabel("ComputerCard2");
			computerCard2Label.setBounds(670, 511, 75, 98);
			Image imgCard56 = new ImageIcon(this.getClass().getResource("/" + computerSecondCard + ".png")).getImage();
			computerCard2Label.setIcon(new ImageIcon(imgCard56));
			layeredPane.add(computerCard2Label);
		}

		if (playerHandArray.size() == 2) {
			playersFirstCard = pokerGame.PlayerFirstCardtoString();
			playersSecondCard = pokerGame.PlayerSecondCardtoString();

			JLabel playerCard1Label = new JLabel("PlayerCard1");
			playerCard1Label.setBounds(75, 511, 75, 98);
			Image imgCard4 = new ImageIcon(this.getClass().getResource("/" + playersFirstCard + ".png")).getImage();
			playerCard1Label.setIcon(new ImageIcon(imgCard4));
			layeredPane.add(playerCard1Label);

			JLabel playerCard2Label = new JLabel("PlayerCard2");
			playerCard2Label.setBounds(212, 511, 75, 98);
			Image imgCard5 = new ImageIcon(this.getClass().getResource("/" + playersSecondCard + ".png")).getImage();
			playerCard2Label.setIcon(new ImageIcon(imgCard5));
			layeredPane.add(playerCard2Label);
		}

		if (communityCards.size() != 0) {

			communityCard1 = pokerGame.firstCommunityCardToString();
			communityCard2 = pokerGame.secondCommunityCardToString();
			communityCard3 = pokerGame.thirdCommunityCardToString();

			JLabel lblNewLabel_3 = new JLabel("CommunityCard1");
			lblNewLabel_3.setBounds(197, 226, 75, 98);
			Image imgCard3 = new ImageIcon(this.getClass().getResource("/" + communityCard1 + ".png")).getImage();
			lblNewLabel_3.setIcon(new ImageIcon(imgCard3));
			layeredPane.add(lblNewLabel_3);

			JLabel lblNewLabel_2 = new JLabel("CommunityCard2");
			lblNewLabel_2.setBounds(292, 226, 75, 98);
			Image imgCard2 = new ImageIcon(this.getClass().getResource("/" + communityCard2 + ".png")).getImage();
			lblNewLabel_2.setIcon(new ImageIcon(imgCard2));
			layeredPane.add(lblNewLabel_2);

			JLabel lblNewLabel_1 = new JLabel("CommunityCard3");
			lblNewLabel_1.setBounds(385, 226, 75, 98);
			Image imgCard1 = new ImageIcon(this.getClass().getResource("/" + communityCard3 + ".png")).getImage();
			lblNewLabel_1.setIcon(new ImageIcon(imgCard1));
			layeredPane.add(lblNewLabel_1);
		}

		if (communityCards.size() >= 4) {

			communityCard4 = pokerGame.fourthCommunityCardToString();

			JLabel lblNewLabel_9 = new JLabel("");
			lblNewLabel_9.setBounds(481, 226, 75, 98);
			Image imgCard6 = new ImageIcon(this.getClass().getResource("/" + communityCard4 + ".png")).getImage();
			lblNewLabel_9.setIcon(new ImageIcon(imgCard6));
			layeredPane.add(lblNewLabel_9);
		}

		if (communityCards.size() == 5) {

			communityCard5 = pokerGame.fifthCommunityCardToString();
			JLabel lblNewLabel_10 = new JLabel("");
			lblNewLabel_10.setBounds(584, 226, 75, 98);
			Image imgCard7 = new ImageIcon(this.getClass().getResource("/" + communityCard5 + ".png")).getImage();
			lblNewLabel_10.setIcon(new ImageIcon(imgCard7));
			layeredPane.add(lblNewLabel_10);

		}

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(51, 107, 731, 352);
		Image img = new ImageIcon(this.getClass().getResource("/poker_table.png")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img));
		layeredPane.add(lblNewLabel);

	}
}