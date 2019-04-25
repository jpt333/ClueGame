package clueGame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Choice;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.List;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Acusation extends JDialog {

	private final JPanel contentPanel = new JPanel();
	String selectedRoom;
	String selectedPerson;
	String selectedWeapon;
	
	JComboBox weaponBox;
	JComboBox personBox;
	JComboBox roomBox;
	JPanel buttonPane;
	
	Board board = Board.getInstance();
	
	
	public String getSelectedRoom() {
		return selectedRoom;
	}


	public String getSelectedPerson() {
		return selectedPerson;
	}


	public String getSelectedWeapon() {
		return selectedWeapon;
	}

	public Acusation() {
		statiicElements();
		accusationElements();
	}
	
	public Acusation(String currentRoom) {
		statiicElements();
		suggestionElements(currentRoom);
	}
	
	private void suggestionElements(String currentRoomString) {
		JLabel roomLabel = new JLabel("Your Room");
		roomLabel.setForeground(Color.WHITE);
		roomLabel.setBounds(10, 11, 139, 37);
		contentPanel.add(roomLabel);
		
		JLabel currentRoom = new JLabel(currentRoomString);
		currentRoom.setForeground(Color.WHITE);
		currentRoom.setBounds(137, 14, 150, 30);
		contentPanel.add(currentRoom);
		
		JButton submitButton = new JButton("Submit");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//System.out.println("Here: " + (String) personBox.getSelectedItem());
				selectedRoom = currentRoomString;
				selectedPerson = (String) personBox.getSelectedItem();
				selectedWeapon = (String) weaponBox.getSelectedItem();
				dispose();
				
				Solution suggestion = new Solution();
				suggestion.person = new Card(getSelectedPerson(), CardType.PERSON);
				suggestion.room = new Card(getSelectedRoom(), CardType.ROOM);
				suggestion.weapon = new Card(getSelectedWeapon(), CardType.WEAPON);
				
				System.out.println(suggestion.person);
				System.out.println(suggestion.room);
				System.out.println(suggestion.weapon);
				
				if(board.checkAccusation(suggestion) == false) {
					board.incorrectAccusation(suggestion);
				}else {
					board.correctAccusation(suggestion);
				}
			}
		});
		buttonPane.add(submitButton);
		getRootPane().setDefaultButton(submitButton);
	}
	
	private void accusationElements() {
		JLabel roomLabel = new JLabel("Room");
		roomLabel.setForeground(Color.WHITE);
		roomLabel.setBounds(10, 11, 139, 37);
		contentPanel.add(roomLabel);
		
		String[] rooms = {"Butterfly Room", "Computer Room", "Kitchen", "Dining Room", "Bedroom", "Parlor", "Music Room", "Game Room", "Living Room"};
		roomBox = new JComboBox(rooms);
		roomBox.setBounds(137, 14, 150, 30);
		contentPanel.add(roomBox);
		
		JButton submitButton = new JButton("Submit");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selectedRoom = (String) roomBox.getSelectedItem();
				selectedPerson = (String) personBox.getSelectedItem();
				selectedWeapon = (String) weaponBox.getSelectedItem();
				dispose();
				
				Solution suggestion = new Solution();
				suggestion.person = new Card(getSelectedPerson(), CardType.PERSON);
				suggestion.room = new Card(getSelectedRoom(), CardType.ROOM);
				suggestion.weapon = new Card(getSelectedWeapon(), CardType.WEAPON);
				System.out.print(suggestion.person);
				System.out.print(suggestion.room);
				System.out.print(suggestion.weapon);
				
				if(board.checkAccusation(suggestion) == false) {
					board.incorrectAccusation(suggestion);
				}else {
					board.correctAccusation(suggestion);
				}
			}
		});
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selectedRoom = null;
				selectedPerson = null;
				selectedWeapon = null;
				dispose();
			}
		});
		buttonPane.add(cancelButton);
		buttonPane.add(submitButton);
		getRootPane().setDefaultButton(submitButton);
	}
	
	private void statiicElements() {
		setTitle("Accusation");
		setBounds(100, 100, 313, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.DARK_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel personLabel = new JLabel("Person");
		personLabel.setForeground(Color.WHITE);
		personLabel.setBounds(10, 96, 94, 37);
		contentPanel.add(personLabel);
		
		JLabel weaponLabel = new JLabel("Weapon");
		weaponLabel.setForeground(Color.WHITE);
		weaponLabel.setBounds(10, 180, 139, 37);
		contentPanel.add(weaponLabel);
		
		String[] people = {"Colonel Mustard", "Miss Scarlet", "Mr. Green", "Mrs. Peacock", "Mrs. White", "Professor Plum"};
		personBox = new JComboBox(people);
		personBox.setBounds(137, 99, 150, 30);
		contentPanel.add(personBox);
		
		String[] weapons = {"Wrench", "Candlestick", "Pipe", "Rope", "Revolver", "Knife"};
		weaponBox = new JComboBox(weapons);
		weaponBox.setBounds(137, 183, 150, 30);
		contentPanel.add(weaponBox);
		
		buttonPane = new JPanel();
		buttonPane.setBackground(Color.DARK_GRAY);
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		
		
		//
		
	}
	
	//testing
		public static void main(String[] args) {
			try {
				Acusation dialog = new Acusation("Room");
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
}
