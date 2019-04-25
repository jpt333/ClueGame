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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Acusation dialog = new Acusation();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Acusation() {
		setTitle("Accusation");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.DARK_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel roomLabel = new JLabel("Room");
		roomLabel.setForeground(Color.WHITE);
		roomLabel.setBounds(10, 11, 139, 37);
		contentPanel.add(roomLabel);
		
		JLabel personLabel = new JLabel("Person");
		personLabel.setForeground(Color.WHITE);
		personLabel.setBounds(10, 96, 94, 37);
		contentPanel.add(personLabel);
		
		JLabel weaponLabel = new JLabel("Weapon");
		weaponLabel.setForeground(Color.WHITE);
		weaponLabel.setBounds(10, 180, 139, 37);
		contentPanel.add(weaponLabel);
		
		JComboBox roomBox = new JComboBox();
		roomBox.setBounds(224, 19, 150, 30);
		contentPanel.add(roomBox);
		
		JComboBox personBox = new JComboBox();
		personBox.setBounds(224, 99, 150, 30);
		contentPanel.add(personBox);
		
		JComboBox weaponBox = new JComboBox();
		weaponBox.setBounds(224, 180, 150, 30);
		contentPanel.add(weaponBox);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.DARK_GRAY);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			JButton cancelButton = new JButton("Cancel");
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				}
			});
			cancelButton.setActionCommand("OK");
			buttonPane.add(cancelButton);
			{
				JButton okButton = new JButton("Submit");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
