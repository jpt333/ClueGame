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
	private JTextField name;
	
	public ClueGameGUI() {
		setLayout(new GridLayout(2,0));
		JPanel panel = createNamePanel();
		add(panel);
		panel = createButtonPanel();
		add(panel);
	}
}
