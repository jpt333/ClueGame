package clueGame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import javax.swing.BoxLayout;
import net.miginfocom.swing.MigLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JEditorPane;
import javax.swing.JTextArea;
import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.Rectangle;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Component;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class Window {

	private JFrame frmClue;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					window.frmClue.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Window() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmClue = new JFrame();
		frmClue.setResizable(false);
		frmClue.getContentPane().setForeground(Color.WHITE);
		frmClue.getContentPane().setEnabled(false);
		frmClue.setForeground(Color.WHITE);
		frmClue.setAlwaysOnTop(true);
		frmClue.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\urber\\git\\ClueGame\\ClueGame\\clueAppIcon.png"));
		frmClue.setTitle("Clue Game");
		frmClue.getContentPane().setBackground(Color.DARK_GRAY);
		frmClue.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Make an Accusation");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(774, 595, 158, 38);
		frmClue.getContentPane().add(btnNewButton);
		
		JButton btnNextPlayer = new JButton("Next Player");
		btnNextPlayer.setBounds(774, 531, 158, 38);
		frmClue.getContentPane().add(btnNextPlayer);
		
		JTextArea txtrGuessResult = new JTextArea();
		txtrGuessResult.setForeground(Color.WHITE);
		txtrGuessResult.setBackground(Color.DARK_GRAY);
		txtrGuessResult.setText("Guess Result");
		txtrGuessResult.setBounds(17, 480, 76, 16);
		frmClue.getContentPane().add(txtrGuessResult);
		
		JTextArea txtrGuess = new JTextArea();
		txtrGuess.setText("Guess");
		txtrGuess.setForeground(Color.WHITE);
		txtrGuess.setBackground(Color.DARK_GRAY);
		txtrGuess.setBounds(282, 480, 37, 16);
		frmClue.getContentPane().add(txtrGuess);
		
		JTextArea txtrWhoseTurn = new JTextArea();
		txtrWhoseTurn.setText("Whose turn?");
		txtrWhoseTurn.setForeground(Color.WHITE);
		txtrWhoseTurn.setBackground(Color.DARK_GRAY);
		txtrWhoseTurn.setBounds(565, 480, 76, 16);
		frmClue.getContentPane().add(txtrWhoseTurn);
		
		JTextArea txtrMyCards = new JTextArea();
		txtrMyCards.setText("My Cards");
		txtrMyCards.setForeground(Color.WHITE);
		txtrMyCards.setBackground(Color.DARK_GRAY);
		txtrMyCards.setBounds(819, 58, 51, 16);
		frmClue.getContentPane().add(txtrMyCards);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 994, 25);
		frmClue.getContentPane().add(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Detective Notes");
		mnFile.add(mntmNewMenuItem);
		
		JMenuItem mntmQuit = new JMenuItem("Quit");
		mnFile.add(mntmQuit);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Window.class.getResource("/Resources/Proffesoor Plum.png")));
		label.setForeground(SystemColor.text);
		label.setBackground(SystemColor.window);
		label.setBounds(12, 498, 100, 150);
		frmClue.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(Window.class.getResource("/Resources/Knife.png")));
		label_1.setForeground(Color.WHITE);
		label_1.setBackground(Color.WHITE);
		label_1.setBounds(143, 498, 100, 150);
		frmClue.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(Window.class.getResource("/Resources/Mr. Green.png")));
		label_2.setForeground(Color.WHITE);
		label_2.setBackground(Color.WHITE);
		label_2.setBounds(253, 498, 100, 150);
		frmClue.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("");
		label_3.setIcon(new ImageIcon(Window.class.getResource("/Resources/PlaceHolder.png")));
		label_3.setForeground(Color.WHITE);
		label_3.setBackground(Color.WHITE);
		label_3.setBounds(370, 499, 100, 150);
		frmClue.getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("");
		label_4.setIcon(new ImageIcon(Window.class.getResource("/Resources/Mrs White.png")));
		label_4.setForeground(Color.WHITE);
		label_4.setBackground(Color.WHITE);
		label_4.setBounds(552, 499, 100, 150);
		frmClue.getContentPane().add(label_4);
		
		JLabel label_5 = new JLabel("");
		label_5.setIcon(new ImageIcon(Window.class.getResource("/Resources/Mrs Scarlet.png")));
		label_5.setForeground(Color.WHITE);
		label_5.setBackground(Color.WHITE);
		label_5.setBounds(797, 99, 100, 150);
		frmClue.getContentPane().add(label_5);
		
		JLabel label_6 = new JLabel("");
		label_6.setIcon(new ImageIcon(Window.class.getResource("/Resources/Pipe.png")));
		label_6.setForeground(Color.WHITE);
		label_6.setBackground(Color.WHITE);
		label_6.setBounds(742, 283, 100, 150);
		frmClue.getContentPane().add(label_6);
		
		JLabel label_7 = new JLabel("");
		label_7.setIcon(new ImageIcon(Window.class.getResource("/Resources/Mrs Peacock.png")));
		label_7.setForeground(Color.WHITE);
		label_7.setBackground(Color.WHITE);
		label_7.setBounds(854, 283, 100, 150);
		frmClue.getContentPane().add(label_7);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 25, 725, 445);
		frmClue.getContentPane().add(panel);
		frmClue.setBounds(100, 100, 1000, 700);
		frmClue.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
