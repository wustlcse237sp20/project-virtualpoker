package GUI;

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
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;

import poker.Card;
import poker.ComputerPlayer;
import poker.Game;
import poker.Hand;
import poker.Player;

public class pokerTable {

	private static JFrame frame;
	private static Game pokerGame;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		String playerName = (String) JOptionPane.showInputDialog(
				frame,
				"Input Player Name:",
				"Input Name",
				JOptionPane.PLAIN_MESSAGE,
				null,
				null,
				null);
		
		Player player = new Player(playerName, 100);
		ComputerPlayer computer = new ComputerPlayer("Computer", 100);
		
		pokerGame = new Game(player, computer);
		
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
	}

	/**
	 * Create the application.
	 */
	public pokerTable(String playerName) {
//		pokerGame.startGame();
		
		initialize(playerName);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String playerName) {
		
		int smallBlind = 5;
		int startingMoney = 100;
		
		Player player = new Player("Player", startingMoney);
		ComputerPlayer computer = new ComputerPlayer("Computer", startingMoney);
		ArrayList<Player> players = new ArrayList<Player>();
		
		Hand playerHand = player.getHand();
		Hand computerHand = computer.getHand();

		boolean isPlayerTurn = true;
		ArrayList<Card> communityCards = new ArrayList<Card>();
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		ArrayList<Card> communityCards = new ArrayList<Card>();
		
		String playersMoney = "";
		String computersMoney = "";
		
		String playersFirstCard  = "";
		String playersSecondCard = "";

		String communityCard1 = "";
		String communityCard2 = "";
		String communityCard3 = "";
		String communityCard4 = "";
		String communityCard5 = "";
		
		 playersFirstCard  = "card=TEN of CLUBS";
		 playersSecondCard = "card=FIVE of CLUBS";
		 
		 communityCard1 = "card=FIVE of CLUBS";
		 communityCard2 = "card=TWO of DIAMONDS";
		 communityCard3 = "card=QUEEN of CLUBS";
		 communityCard4 = "card=KING of CLUBS";
		 communityCard5 = "card=JACK of CLUBS";
	
		

				
		
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
		frame = new JFrame();
		frame.setBounds(100, 100, 957, 660);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.WEST);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnNewButton = new JButton("Call");
		btnNewButton.setFocusable(false);
		btnNewButton.setBackground(Color.GRAY);
		btnNewButton.setForeground(Color.DARK_GRAY);
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 17));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Raise");
		btnNewButton_1.setFocusable(false);
		btnNewButton_1.setBackground(Color.GRAY);
		btnNewButton_1.setForeground(Color.DARK_GRAY);
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 17));
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Fold");
		btnNewButton_2.setFocusable(false);
		btnNewButton_2.setBackground(Color.GRAY);
		btnNewButton_2.setForeground(Color.DARK_GRAY);
		btnNewButton_2.setFont(new Font("Times New Roman", Font.BOLD, 17));
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Exit");
		btnNewButton_3.setFocusable(false);
		btnNewButton_3.setBackground(Color.GRAY);
		btnNewButton_3.setForeground(Color.DARK_GRAY);
		btnNewButton_3.setFont(new Font("Times New Roman", Font.BOLD, 17));
		panel.add(btnNewButton_3);
		
		JLayeredPane layeredPane = new JLayeredPane();
		frame.getContentPane().add(layeredPane, BorderLayout.CENTER);
		
		JLabel lblNewLabel_6 = new JLabel("PlayerCard1");
		lblNewLabel_6.setBounds(292, 511, 75, 98);
		Image imgCard4 = new ImageIcon(this.getClass().getResource("/"+ playersFirstCard +".png")).getImage();
		lblNewLabel_6.setIcon(new ImageIcon(imgCard4));
		layeredPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("PlayerCard2");
		lblNewLabel_7.setBounds(396, 511, 75, 98);
		Image imgCard5 = new ImageIcon(this.getClass().getResource("/"+ playersSecondCard +".png")).getImage();
		lblNewLabel_7.setIcon(new ImageIcon(imgCard5));
		layeredPane.add(lblNewLabel_7);
		
		
		
		JLabel lblNewLabel_3 = new JLabel("CommunityCard1");
		lblNewLabel_3.setBounds(197, 226, 75, 98);
		Image imgCard3 = new ImageIcon(this.getClass().getResource("/"+communityCard1+".png")).getImage();
		lblNewLabel_3.setIcon(new ImageIcon(imgCard3));
		layeredPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_2 = new JLabel("CommunityCard2");
		lblNewLabel_2.setOpaque(true);
		lblNewLabel_2.setBounds(292, 226, 75, 98);		
		Image imgCard2 = new ImageIcon(this.getClass().getResource("/"+communityCard2+".png")).getImage();
		lblNewLabel_2.setIcon(new ImageIcon(imgCard2));
		layeredPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_1 = new JLabel("CommunityCard3");
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setBounds(385, 226, 75, 98);
		Image imgCard1 = new ImageIcon(this.getClass().getResource("/"+communityCard3+".png")).getImage();
		lblNewLabel_1.setIcon(new ImageIcon(imgCard1));
		layeredPane.add(lblNewLabel_1);
		
		
		if(communityCard4!="") {
		JLabel lblNewLabel_9 = new JLabel("");
		lblNewLabel_9.setBounds(481, 226, 75, 98);
		Image imgCard6 = new ImageIcon(this.getClass().getResource("/"+communityCard4+".png")).getImage();
		lblNewLabel_9.setIcon(new ImageIcon(imgCard6));
		layeredPane.add(lblNewLabel_9);
		}
		
		if(communityCard5 != "") {
		JLabel lblNewLabel_10 = new JLabel("");
		lblNewLabel_10.setBounds(584, 226, 75, 98);
		Image imgCard7 = new ImageIcon(this.getClass().getResource("/"+communityCard5+".png")).getImage();
		lblNewLabel_10.setIcon(new ImageIcon(imgCard7));
		layeredPane.add(lblNewLabel_10);
		}
		
	
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(51, 107, 731, 352);
		Image img = new ImageIcon(this.getClass().getResource("/poker_table.png")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img));
		layeredPane.add(lblNewLabel);
		
		JLabel lblNewLabel_4 = new JLabel(playerName);
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(350, 486, 75, 14);
		layeredPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel(playersMoney);
		lblNewLabel_5.setBounds(520, 486, 88, 14);
		layeredPane.add(lblNewLabel_5);
		
	
		
		JLabel lblNewLabel_8 = new JLabel("POT");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8.setBounds(365, 43, 70, 14);
		layeredPane.add(lblNewLabel_8);
		
		JLabel lblNewLabel_11 = new JLabel("Computer");
		lblNewLabel_11.setBounds(712, 486, 46, 14);
		layeredPane.add(lblNewLabel_11);
		
		JLabel lblNewLabel_12 = new JLabel(computersMoney);
		lblNewLabel_12.setBounds(712, 534, 46, 14);
		layeredPane.add(lblNewLabel_12);
		
	
		
	
		
	}
}
