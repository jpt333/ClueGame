package clueGame;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class ClueGameGUI extends JPanel{
	private JTextField people;
	private JTextField rooms;
	private JTextField weapons;
	
	public ClueGameGUI() {
		setLayout(new GridLayout(3,0));
		JPanel panel = createBoardAndCards();
		add(panel);
		panel = createTurnAndButtons();
		add(panel);
		panel = createRollAndGuessAndResult();
		add(panel);
	}
	
	private JPanel createBoardAndCards() {
		Border compound;
		JPanel content = new JPanel();
		JPanel panel = new JPanel();
		//Grid Layout, 1 row, 2 columns
		panel.setLayout(new GridLayout(1,2));
		JLabel nameBoard = new JLabel("Board");
		panel.add(nameBoard);
		content.add(panel);
		people = new JTextField(20);
		rooms = new JTextField(20);
		weapons = new JTextField(20);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(), "My Cards"));
		compound = BorderFactory.createTitledBorder("People");
		//Don't know how to add border inside border
		panel.add(people);
		panel.add(rooms);
		panel.add(weapons);
		content.add(panel);
		return content;
	}
}
