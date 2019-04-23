//Authors: Michael Berg and Jennifer Phan
package clueGame;

import java.awt.Dimension;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class MakeAGuessDialog extends JDialog{

	public MakeAGuessDialog() {
		setTitle("Make a Guess");
		setSize(400,400);
		setLayout(new GridLayout(4,2));
		JPanel panel = new JPanel();
		
		JLabel yourRoom = new JLabel("Your room");
		JLabel person = new JLabel("Person");
		JLabel weapon = new JLabel("Weapon");
		JButton submitBtn = new JButton("Submit");
		JButton cancelBtn = new JButton("Cancel");
		JLabel roomLabel = new JLabel("");         //stores the actual room player is in
		JComboBox<String> personGuess = new JComboBox<String>();
		personGuess.addItem("Miss Scarlet");
		personGuess.addItem("Colonel Mustard");
		personGuess.addItem("Mr. Green");
		personGuess.addItem("Mrs. White");
		personGuess.addItem("Mrs. Peacock");
		personGuess.addItem("Professor Plum");
		
		JComboBox<String> weaponGuess = new JComboBox<String>();
		weaponGuess.addItem("Candlestick");
		weaponGuess.addItem("Wrench");;
		weaponGuess.addItem("Pipe");
		weaponGuess.addItem("Rope");;
		weaponGuess.addItem("Revolver");
		weaponGuess.addItem("Knife");
		
		add(yourRoom);
		add(roomLabel);     //NEED TO UPDATE WHERE HUMAN PLAYER IS IN ROOM
		add(person);
		add(personGuess);
		add(weapon);
		add(weaponGuess);
		add(submitBtn);
		add(cancelBtn);

	}
	
	public static void main(String[] args) {
		MakeAGuessDialog dialog = new MakeAGuessDialog();
		dialog.setVisible(true);
	}
	
}
