package GUI;

import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import poker.Player.PlayerChoice;
import poker.Player;

import java.awt.*;
import java.awt.event.*;

public class ButtonPress implements ActionListener{
	
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	
	JButton btnNewButton = new JButton("Call");
	JButton btnNewButton_1 = new JButton("Raise");
	JButton btnNewButton_2 = new JButton("Fold");
	JButton btnNewButton_3 = new JButton("Exit");
	
	
	ButtonPress(){
		prepareGUI();
	}
	
	public void prepareGUI() {
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setBounds(100, 100, 957, 660);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(panel, BorderLayout.WEST);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
	}
	
	public void buttonProperties() {
		btnNewButton.setFocusable(false);
		btnNewButton.setBackground(Color.GRAY);
		btnNewButton.setForeground(Color.DARK_GRAY);
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 17));
		panel.add(btnNewButton);
		btnNewButton.addActionListener(this);
		
	}
		
	public void buttonProperties_1() {
		btnNewButton_1.setFocusable(false);
		btnNewButton_1.setBackground(Color.GRAY);
		btnNewButton_1.setForeground(Color.DARK_GRAY);
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 17));
		panel.add(btnNewButton_1);
		btnNewButton_1.addActionListener(this);
	}
	
	public void buttonProperties_2() {
		btnNewButton_2.setFocusable(false);
		btnNewButton_2.setBackground(Color.GRAY);
		btnNewButton_2.setForeground(Color.DARK_GRAY);
		btnNewButton_2.setFont(new Font("Times New Roman", Font.BOLD, 17));
		panel.add(btnNewButton_2);
		btnNewButton_2.addActionListener(this);
	}
	public void buttonProperties_3() {
		btnNewButton_3.setFocusable(false);
		btnNewButton_3.setBackground(Color.GRAY);
		btnNewButton_3.setForeground(Color.DARK_GRAY);
		btnNewButton_3.setFont(new Font("Times New Roman", Font.BOLD, 17));
		panel.add(btnNewButton_3);
		btnNewButton_3.addActionListener(this);
	}
	
	public PlayerChoice choice(ActionEvent e) {
		if(e.getSource() == btnNewButton) {
		return PlayerChoice.CALL;
		 }
		else {
			if(e.getSource() == btnNewButton_1) {
				return PlayerChoice.RAISE;
			}
			else {
		if(e.getSource() == btnNewButton_2) {
				return PlayerChoice.FOLD;
		 }
		else {
			return PlayerChoice.NULL;
		}
			}
			}
		}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
	}
	
	
	



