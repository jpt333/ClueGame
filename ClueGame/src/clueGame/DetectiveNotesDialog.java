//Authors: Michael Berg and Jennifer Phan
package clueGame;

import java.awt.GridLayout;

import javax.swing.JCheckBox;
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
		setSize(300,300);
		setLayout(new GridLayout(3,2));
		JPanel panel = PeoplePanel();
		add(panel);
		panel = RoomsPanel();
		add(panel);
		panel = WeaponsPanel();
		add(panel);
		
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
	
	
	
	
	public static void main(String[] args) {
		DetectiveNotesDialog dialog = new DetectiveNotesDialog();
		dialog.setVisible(true);
	}
}
