//Authors: Michael Berg and Jennifer Phan
package clueGame;

import java.awt.GridLayout;

import javax.swing.JCheckBox;
import javax.swing.JDialog;

public class DetectiveNotesDialog extends JDialog{
	JCheckBox peoplee = new JCheckBox();
	
	public DetectiveNotesDialog() {
		setTitle("Detective Dialog");
		setSize(300,300);
		setLayout(new GridLayout(3,2));
		//People
		String[] people = new String[] {"Miss Scarlet", "Colonel Mustard", "Mr. Green", "Mrs. White", "Mrs. Peacock", "Professor Plum"};
		for(String person : people) {
			peoplee = new JCheckBox(person);
			add(peoplee);
		}
		//Rooms
		String[] rooms = new String[] {""};
		
	}
	
	public static void main(String[] args) {
		DetectiveNotesDialog dialog = new DetectiveNotesDialog();
		dialog.setVisible(true);
	}
}
