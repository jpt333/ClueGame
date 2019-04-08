//Authors: Michael Berg and Jennifer Phan
package clueGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class ClueGameGUI extends JPanel{
	private JTextField people;
	private JTextField rooms;
	private JTextField weapons;
	
	public ClueGameGUI() {
		setLayout(new GridLayout(3,1));
		JPanel panel = createBoardAndCards();
		add(panel);
		panel = createTurnAndButtons();
		add(panel);
		panel = createRollAndGuessAndResult();
		add(panel);
	}
	
	private JPanel createBoardAndCards() {
		JPanel content = new JPanel();
		JPanel panel = new JPanel();
		//Grid Layout, 1 row, 2 columns
		panel.setLayout(new GridLayout(1,3));
		JLabel nameBoard = new JLabel("Board");
		panel.add(nameBoard);
		content.add(panel);
		people = new JTextField(20);
		rooms = new JTextField(20);
		weapons = new JTextField(20);
		
		JPanel panel2 = new JPanel();
		panel2.setBorder(new TitledBorder(new EtchedBorder(), "My Cards"));
		panel2.setLayout(new GridLayout(3,0));

		people.setBorder(new CompoundBorder(BorderFactory.createTitledBorder("People"), people.getBorder()));
		rooms.setBorder(new CompoundBorder(BorderFactory.createTitledBorder("Room"), rooms.getBorder()));
		weapons.setBorder(new CompoundBorder(BorderFactory.createTitledBorder("Weapon"), weapons.getBorder()));
		panel2.add(people, BorderLayout.NORTH);
		panel2.add(rooms, BorderLayout.CENTER);
		panel2.add(weapons, BorderLayout.SOUTH);
		content.add(panel2, BorderLayout.EAST);
		return content;
	}
	
	private JPanel createTurnAndButtons() {
		JPanel panel1 = new JPanel();
		//1 row, 3 columns
		panel1.setLayout(new GridLayout(1, 3));
		
		JPanel panel = new JPanel();
		JLabel whoseTurn = new JLabel("Whose turn?");
		panel.add(whoseTurn);
		JTextField cardNamePlayer = new JTextField(20);
		panel.add(cardNamePlayer);
		panel1.add(panel);
		panel = new JPanel();
		JButton nextPlayer = new JButton("Next Player");
		nextPlayer.setPreferredSize(new Dimension(250, 100));
		JButton makeAccusation = new JButton("Make an accusation");
		makeAccusation.setPreferredSize(new Dimension(250, 100));
		panel.add(nextPlayer);
		panel1.add(panel);
		panel = new JPanel();
		panel.add(makeAccusation);
		panel1.add(panel);
		return panel1;
	}
	
	private JPanel createRollAndGuessAndResult() {
		JPanel panel1 = new JPanel();
		JLabel roll = new JLabel("Roll");
		panel1.setLayout(new GridLayout(1, 3));
		
		
		//Die Roll
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Die"));
		panel.add(roll);
		JTextField rollNum = new JTextField(20);
		panel.add(rollNum);
		panel1.add(panel);
		
		//Guess
		panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Guess"));
		JLabel guess = new JLabel("Guess");
		panel.add(guess);
		JTextField guessPersonRoomWeapon = new JTextField(20);
		panel.add(guessPersonRoomWeapon);
		panel1.add(panel);
		
		//Guess Result Response
		panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Guess Result"));
		JLabel response = new JLabel("Response");
		panel.add(response);
		JTextField responseText = new JTextField(20);
		panel.add(responseText);
		panel1.add(panel);
		panel1.setPreferredSize(new Dimension(900, 100));
		return panel1;
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Clue Game GUI");
		frame.setSize(1500, 900);
		frame.setSize(900,800);
		ClueGameGUI gui = new ClueGameGUI();
		frame.add(gui, BorderLayout.SOUTH);
		frame.setVisible(true);
		
	}
	
}
