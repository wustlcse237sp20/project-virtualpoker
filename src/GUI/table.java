package GUI;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLayeredPane;

public class table {

	private JFrame frame;
	private JTextField txtBet;
	private JTextField txtPot;
	private JTextField txtPlayerName;
	private JTextField txtPlayersMoney;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					table window = new table();
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
	public table() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1017, 770);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(189, 142, 738, 394);
		Image img = new ImageIcon(this.getClass().getResource("/poker_table.png")).getImage();
		frame.getContentPane().setLayout(null);
		lblNewLabel.setIcon(new ImageIcon(img));
		frame.getContentPane().add(lblNewLabel);
		
		txtBet = new JTextField();
		txtBet.setBackground(Color.WHITE);
		txtBet.setBounds(47, 266, 78, 33);
		txtBet.setText("Bet");
		txtBet.setColumns(10);
		frame.getContentPane().add(txtBet);
		
		txtPot = new JTextField();
		txtPot.setText("Pot Value");
		txtPot.setBounds(47, 357, 86, 20);
		frame.getContentPane().add(txtPot);
		txtPot.setColumns(10);
		
		txtPlayerName = new JTextField();
		txtPlayerName.setBounds(431, 547, 86, 20);
		txtPlayerName.setText("Player Name");
		frame.getContentPane().add(txtPlayerName);
		txtPlayerName.setColumns(10);
		
		txtPlayersMoney = new JTextField();
		txtPlayersMoney.setBounds(572, 547, 86, 20);
		txtPlayersMoney.setText("Players Money");
		frame.getContentPane().add(txtPlayersMoney);
		txtPlayersMoney.setColumns(10);
		
		lblNewLabel_1 = new JLabel("");
		Image imgCard1 = new ImageIcon(this.getClass().getResource("/22.png")).getImage();
		lblNewLabel_1.setIcon(new ImageIcon(imgCard1));
		lblNewLabel_1.setBounds(445, 578, 72, 103);
		frame.getContentPane().add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("");
		Image imgCard2 = new ImageIcon(this.getClass().getResource("/12.png")).getImage();
		lblNewLabel_2.setIcon(new ImageIcon(imgCard2));
		lblNewLabel_2.setBounds(572, 578, 72, 103);
		frame.getContentPane().add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("");
		Image imgCard3 = new ImageIcon(this.getClass().getResource("/33.png")).getImage();
		lblNewLabel_3.setIcon(new ImageIcon(imgCard3));
		lblNewLabel_3.setBounds(266, 33, 72, 98);
		frame.getContentPane().add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("");
		Image imgCard4 = new ImageIcon(this.getClass().getResource("/42.png")).getImage();
		lblNewLabel_4.setIcon(new ImageIcon(imgCard4));
		lblNewLabel_4.setBounds(382, 33, 72, 98);
		frame.getContentPane().add(lblNewLabel_4);
		
		JButton btnNewButton = new JButton("Fold");
		btnNewButton.setBounds(854, 584, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Call");
		btnNewButton_1.setBounds(854, 618, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Raise");
		btnNewButton_2.setBounds(854, 658, 89, 23);
		frame.getContentPane().add(btnNewButton_2);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(741, 362, -400, -135);
		frame.getContentPane().add(layeredPane);
		
		lblNewLabel_5 = new JLabel("");
		Image imgCard5 = new ImageIcon(this.getClass().getResource("/11.png")).getImage();
		lblNewLabel_5.setIcon(new ImageIcon(imgCard5));
		lblNewLabel_5.setBounds(524, 33, 72, 98);
		frame.getContentPane().add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("");
		Image imgCard6 = new ImageIcon(this.getClass().getResource("/27.png")).getImage();
		lblNewLabel_6.setIcon(new ImageIcon(imgCard6));
		lblNewLabel_6.setBounds(653, 33, 72, 98);
		frame.getContentPane().add(lblNewLabel_6);
		Image pot = new ImageIcon(this.getClass().getResource("/pot.jpg")).getImage();
	}
}
