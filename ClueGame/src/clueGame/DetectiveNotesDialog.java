//Authors: Michael Berg and Jennifer Phan

package clueGame;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class DetectiveNotesDialog extends JDialog{
	JCheckBox peoplee = new JCheckBox();
	JCheckBox rooom = new JCheckBox();
	JCheckBox weapoon = new JCheckBox();
	
	public DetectiveNotesDialog() {
		setTitle("Detective Dialog");
		setSize(500,700);
		setLayout(new GridLayout(3,2));
		JPanel panel = PeoplePanel();
		add(panel);
		panel = PersonGuessPanel();
		add(panel);
		panel = RoomsPanel();
		add(panel);
		panel = RoomGuessPanel();
		add(panel);
		panel = WeaponsPanel();
		add(panel);
		panel = WeaponGuessPanel();
		add(panel);
		setResizable(false);
	}
	
	//People with checkboxes
	private JPanel PeoplePanel() {
		
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBorder(new TitledBorder(new EtchedBorder(), "People"));
		//People
		String[] people = new String[] {"Miss Scarlet", "Colonel Mustard", "Mr. Green", "Mrs. White", "Mrs. Peacock", "Professor Plum"};
		for(String person : people) {
			peoplee = new JCheckBox(person);
			mainPanel.add(peoplee);
			//peoplee.setSelected(true);
			
		}
		
		return mainPanel;
	}
	
	//Rooms with checkboxes
	private JPanel RoomsPanel() {
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Rooms"));
		//Rooms
		String[] rooms = new String[] {"Butterfly Room", "Computer Room", "Kitchen", "Dining Room", "Bedroom", "Parlor","Music Room", "Game Room", "Living Room"};
		for(String room : rooms) {
			rooom = new JCheckBox(room);
			panel.add(rooom);
		}
		return panel;
	}
	
	//Weapons with checkboxes
	private JPanel WeaponsPanel() {
		JPanel panel1 = new JPanel();
		panel1.setBorder(new TitledBorder(new EtchedBorder(), "Weapons"));
		//Weapons
		String[] weapons = new String[] {"Wrench", "Candlestick", "Pipe", "Rope", "Revolver", "Knife"};
		for(String weapon : weapons) {
			weapoon = new JCheckBox(weapon);
			panel1.add(weapoon);
		}
		return panel1;
	}
	
	//Person Guess
	private JPanel PersonGuessPanel() {
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Person Guess"));
		JComboBox<String> personGuess = new JComboBox<String>();
		personGuess.addItem("Unsure");
		personGuess.addItem("Miss Scarlet");
		personGuess.addItem("Colonel Mustard");
		personGuess.addItem("Mr. Green");
		personGuess.addItem("Mrs. White");
		personGuess.addItem("Mrs. Peacock");
		personGuess.addItem("Professor Plum");
		//Size height for JComboBox
		Dimension preferredSize = personGuess.getPreferredSize();
		preferredSize.height = 100;
		preferredSize.width = 120;
		personGuess.setPreferredSize(preferredSize);
		
		panel.add(personGuess);
		return panel;
	}
	
	//Room Guess
	private JPanel RoomGuessPanel() {
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Room Guess"));
		JComboBox<String> roomGuess = new JComboBox<String>();
		String[] rooms = new String[] {"Unsure","Butterfly Room", "Computer Room", "Kitchen", "Dining Room", "Bedroom", "Parlor","Music Room", "Game Room", "Living Room"};
		for(String room : rooms) {
			roomGuess.addItem(room);
		}
		//Size height for JComboBox
		Dimension preferredSize = roomGuess.getPreferredSize();
		preferredSize.height = 100;
		preferredSize.width = 120;
		roomGuess.setPreferredSize(preferredSize);
		
		panel.add(roomGuess);
		return panel;
	}
	
	//Weapon Guess
	private JPanel WeaponGuessPanel() {
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Weapon Guess"));
		JComboBox<String> weaponGuess = new JComboBox<String>();
		String[] weapons = new String[] {"Unsure", "Wrench", "Candlestick", "Pipe", "Rope", "Revolver", "Knife"};
		for(String weapon: weapons) {
			weaponGuess.addItem(weapon);
		}
		//size of JComboBox
		Dimension preferredSize = weaponGuess.getPreferredSize();
		preferredSize.height = 100;
		preferredSize.width = 120;
		weaponGuess.setPreferredSize(preferredSize);
		
		panel.add(weaponGuess);
		return panel;
	}
	
	public static void main(String[] args) {
		DetectiveNotesDialog dialog = new DetectiveNotesDialog();
		dialog.setVisible(true);
	}
}
