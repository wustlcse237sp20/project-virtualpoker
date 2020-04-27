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

public class PokerTable {

	private static JFrame frame;
	private static Game pokerGame;

	static ArrayList<Card> communityCards = new ArrayList<Card>();
	static ArrayList<Card> playerHandArray = new ArrayList<Card>();
	static ArrayList<Card> computerHandArray = new ArrayList<Card>();

	static int playersMoney = 0;
	static int computersMoney = 0;
	
	static boolean isShowdown = false;

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
					PokerTable window = new PokerTable(playerName);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		pokerGame.startGame(playerName);

		while (!pokerGame.checkForWinner()) {
			
			isShowdown = false;
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
						PokerTable window = new PokerTable(playerName);
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
							PokerTable window = new PokerTable(playerName);
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
							PokerTable window = new PokerTable(playerName);
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
							PokerTable window = new PokerTable(playerName);
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
				//play showdown
				isShowdown = true;
				displayMessage("Round: SHOWDOWN!");
				computerHandArray = computer.getHand().getHand();
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							PokerTable window = new PokerTable(playerName);
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
	public PokerTable(String playerName) {
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
		frame.setBounds(100, 100, 957, 730);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.WEST);
		panel.setLayout(new GridLayout(3, 1, 0, 0));

		JButton callButton = new JButton("Call");
		panel.add(callButton);

		JButton raiseButton = new JButton("Raise");
		panel.add(raiseButton);

		JButton foldButton = new JButton("Fold");
		panel.add(foldButton);

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
		JLabel cardBox = new JLabel("");
		cardBox.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.DARK_GRAY));
		cardBox.setBounds(501, 443, 289, 167);
		layeredPane.add(cardBox);

		JLabel computerMoneyLabel = new JLabel("Computer Money");
		computerMoneyLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		computerMoneyLabel.setBounds(650, 455, 125, 14);
		layeredPane.add(computerMoneyLabel);

		JLabel computerMoneyValue = new JLabel(String.valueOf(computersMoney));
		computerMoneyValue.setBounds(692, 486, 46, 14);
		layeredPane.add(computerMoneyValue);

		JLabel computerNameLabel = new JLabel("Computer Name");
		computerNameLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		computerNameLabel.setBounds(526, 455, 110, 14);
		layeredPane.add(computerNameLabel);

		JLabel computerName = new JLabel("Computer");
		computerName.setHorizontalAlignment(SwingConstants.CENTER);
		computerName.setBounds(536, 486, 75, 14);
		layeredPane.add(computerName);

		if (!isShowdown) {
			JLabel computerFirstCardLabel = new JLabel("ComputerCard1");
			computerFirstCardLabel.setBounds(526, 511, 75, 98);
			computerFirstCardLabel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.DARK_GRAY));
			Image computerFirstCardImage = new ImageIcon(this.getClass().getResource("/" + computerFirstCard + ".jpg")).getImage();
			computerFirstCardLabel.setIcon(new ImageIcon(computerFirstCardImage));
			layeredPane.add(computerFirstCardLabel);

			JLabel computerSecondCardLabel = new JLabel("ComputerCard2");
			computerSecondCardLabel.setBounds(670, 511, 75, 98);
			computerSecondCardLabel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.DARK_GRAY));
			Image computerSecondCardImage = new ImageIcon(this.getClass().getResource("/" + computerSecondCard + ".jpg")).getImage();
			computerSecondCardLabel.setIcon(new ImageIcon(computerSecondCardImage));
			layeredPane.add(computerSecondCardLabel);
		}

		if (isShowdown) {
			computerFirstCard = computerHandArray.get(0).imageToString();
			computerSecondCard = computerHandArray.get(1).imageToString();

			JLabel computerFirstCardLabel = new JLabel("ComputerCard1");
			computerFirstCardLabel.setBounds(526, 511, 75, 98);
			Image imgCard46 = new ImageIcon(this.getClass().getResource("/" + computerFirstCard + ".png")).getImage();
			computerFirstCardLabel.setIcon(new ImageIcon(imgCard46));
			layeredPane.add(computerFirstCardLabel);
			
			JLabel computerSecondCardLabel = new JLabel("ComputerCard2");
			computerSecondCardLabel.setBounds(670, 511, 75, 98);
			Image imgCard56 = new ImageIcon(this.getClass().getResource("/" + computerSecondCard + ".png")).getImage();
			computerSecondCardLabel.setIcon(new ImageIcon(imgCard56));
			layeredPane.add(computerSecondCardLabel);
		}

		if (playerHandArray.size() == 2) {
			playersFirstCard = playerHandArray.get(0).imageToString();
			playersSecondCard = playerHandArray.get(1).imageToString();

			JLabel playerFirstCardLabel = new JLabel("PlayerCard1");
			playerFirstCardLabel.setBounds(75, 511, 75, 98);
			Image imgCard4 = new ImageIcon(this.getClass().getResource("/" + playersFirstCard + ".png")).getImage();
			playerFirstCardLabel.setIcon(new ImageIcon(imgCard4));
			layeredPane.add(playerFirstCardLabel);

			JLabel playerSecondCardLabel = new JLabel("PlayerCard2");
			playerSecondCardLabel.setBounds(212, 511, 75, 98);
			Image imgCard5 = new ImageIcon(this.getClass().getResource("/" + playersSecondCard + ".png")).getImage();
			playerSecondCardLabel.setIcon(new ImageIcon(imgCard5));
			layeredPane.add(playerSecondCardLabel);
		}

		if (communityCards.size() != 0) {

			communityCard1 = communityCards.get(0).imageToString();
			communityCard2 = communityCards.get(1).imageToString();
			communityCard3 = communityCards.get(2).imageToString();
			
			JLabel communityFirstCardLabel = new JLabel("CommunityCard1");
			communityFirstCardLabel.setBounds(197, 226, 75, 98);
			Image communityFirstCardImage = new ImageIcon(this.getClass().getResource("/" + communityCard1 + ".png")).getImage();
			communityFirstCardLabel.setIcon(new ImageIcon(communityFirstCardImage));
			layeredPane.add(communityFirstCardLabel);

			JLabel communitySecondCardLabel = new JLabel("CommunityCard2");
			communitySecondCardLabel.setBounds(292, 226, 75, 98);
			Image communitySecondCardImage = new ImageIcon(this.getClass().getResource("/" + communityCard2 + ".png")).getImage();
			communitySecondCardLabel.setIcon(new ImageIcon(communitySecondCardImage));
			layeredPane.add(communitySecondCardLabel);

			JLabel communityThirdCardLabel = new JLabel("CommunityCard3");
			communityThirdCardLabel.setBounds(385, 226, 75, 98);
			Image communityThirdCardImage = new ImageIcon(this.getClass().getResource("/" + communityCard3 + ".png")).getImage();
			communityThirdCardLabel.setIcon(new ImageIcon(communityThirdCardImage));
			layeredPane.add(communityThirdCardLabel);
		}

		if (communityCards.size() >= 4) {

			communityCard4 = communityCards.get(3).imageToString();

			JLabel communityFourthCardLabel = new JLabel("");
			communityFourthCardLabel.setBounds(481, 226, 75, 98);
			Image communityFourthCardImage = new ImageIcon(this.getClass().getResource("/" + communityCard4 + ".png")).getImage();
			communityFourthCardLabel.setIcon(new ImageIcon(communityFourthCardImage));
			layeredPane.add(communityFourthCardLabel);
		}

		if (communityCards.size() == 5) {

			communityCard5 = communityCards.get(4).imageToString();
			
			JLabel communityFifthCardLabel = new JLabel("");
			communityFifthCardLabel.setBounds(584, 226, 75, 98);
			Image communityFifthCardImage = new ImageIcon(this.getClass().getResource("/" + communityCard5 + ".png")).getImage();
			communityFifthCardLabel.setIcon(new ImageIcon(communityFifthCardImage));
			layeredPane.add(communityFifthCardLabel);

		}

		JLabel pokerTableLabel = new JLabel("");
		pokerTableLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pokerTableLabel.setBounds(51, 107, 731, 352);
		Image pokerTableImage = new ImageIcon(this.getClass().getResource("/poker_table.png")).getImage();
		pokerTableLabel.setIcon(new ImageIcon(pokerTableImage));
		layeredPane.add(pokerTableLabel);

	}
}
