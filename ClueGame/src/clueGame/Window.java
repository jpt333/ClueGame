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
import javax.swing.AbstractAction;
import javax.swing.Action;

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
		frmClue.setIconImage(Toolkit.getDefaultToolkit().getImage(Window.class.getResource("/Resources/clueGameLogo.png")));
		frmClue.setTitle("Clue Game");
		frmClue.getContentPane().setBackground(Color.DARK_GRAY);
		frmClue.getContentPane().setLayout(null);
		
		JButton accusationButton = new JButton("Make an Accusation");
		accusationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		accusationButton.setBounds(774, 595, 158, 38);
		frmClue.getContentPane().add(accusationButton);
		
		JButton nextPlayer = new JButton("Next Player");
		nextPlayer.setBounds(774, 525, 158, 38);
		frmClue.getContentPane().add(nextPlayer);
		
		JTextArea txtrGuessResult = new JTextArea();
		txtrGuessResult.setForeground(Color.WHITE);
		txtrGuessResult.setBackground(Color.DARK_GRAY);
		txtrGuessResult.setText("Guess Result");
		txtrGuessResult.setBounds(22, 480, 76, 16);
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
		txtrWhoseTurn.setBounds(528, 480, 76, 16);
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
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DetectiveNotesDialog dialog = new DetectiveNotesDialog();
				dialog.setVisible(true);
			}
		});
		mnFile.add(mntmNewMenuItem);
		
		JMenuItem mntmQuit = new JMenuItem("Quit");
		mntmQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmClue.dispose();
			}
		});
		mnFile.add(mntmQuit);

		JLabel guessResultCard = new JLabel("");
		guessResultCard.setIcon(new ImageIcon(Window.class.getResource("/Resources/Proffesoor Plum.png")));
		guessResultCard.setForeground(SystemColor.text);
		guessResultCard.setBackground(SystemColor.window);
		guessResultCard.setBounds(12, 498, 100, 150);
		frmClue.getContentPane().add(guessResultCard);
		
		JLabel weaponcard = new JLabel("");
		weaponcard.setIcon(new ImageIcon(Window.class.getResource("/Resources/Knife.png")));
		weaponcard.setForeground(Color.WHITE);
		weaponcard.setBackground(Color.WHITE);
		weaponcard.setBounds(143, 498, 100, 150);
		frmClue.getContentPane().add(weaponcard);
		
		JLabel personCard = new JLabel("");
		personCard.setIcon(new ImageIcon(Window.class.getResource("/Resources/Mr. Green.png")));
		personCard.setForeground(Color.WHITE);
		personCard.setBackground(Color.WHITE);
		personCard.setBounds(253, 498, 100, 150);
		frmClue.getContentPane().add(personCard);
		
		JLabel roomCard = new JLabel("");
		roomCard.setIcon(new ImageIcon(Window.class.getResource("/Resources/PlaceHolder.png")));
		roomCard.setForeground(Color.WHITE);
		roomCard.setBackground(Color.WHITE);
		roomCard.setBounds(370, 499, 100, 150);
		frmClue.getContentPane().add(roomCard);
		
		JLabel playTurnCard = new JLabel("");
		playTurnCard.setIcon(new ImageIcon(Window.class.getResource("/Resources/Mrs White.png")));
		playTurnCard.setForeground(Color.WHITE);
		playTurnCard.setBackground(Color.WHITE);
		playTurnCard.setBounds(513, 498, 100, 150);
		frmClue.getContentPane().add(playTurnCard);
		
		JLabel card1 = new JLabel("");
		card1.setIcon(new ImageIcon(Window.class.getResource("/Resources/Mrs Scarlet.png")));
		card1.setForeground(Color.WHITE);
		card1.setBackground(Color.WHITE);
		card1.setBounds(797, 99, 100, 150);
		frmClue.getContentPane().add(card1);
		
		JLabel card2 = new JLabel("");
		card2.setIcon(new ImageIcon(Window.class.getResource("/Resources/Pipe.png")));
		card2.setForeground(Color.WHITE);
		card2.setBackground(Color.WHITE);
		card2.setBounds(742, 283, 100, 150);
		frmClue.getContentPane().add(card2);
		
		JLabel card3 = new JLabel("");
		card3.setIcon(new ImageIcon(Window.class.getResource("/Resources/Mrs Peacock.png")));
		card3.setForeground(Color.WHITE);
		card3.setBackground(Color.WHITE);
		card3.setBounds(854, 283, 100, 150);
		frmClue.getContentPane().add(card3);
		
		JPanel gameBoard = new JPanel();
		gameBoard.setBounds(0, 25, 725, 445);
		frmClue.getContentPane().add(gameBoard);
		
		JTextArea txtrDiceRoll = new JTextArea();
		txtrDiceRoll.setText("Dice Roll");
		txtrDiceRoll.setForeground(Color.WHITE);
		txtrDiceRoll.setBackground(Color.DARK_GRAY);
		txtrDiceRoll.setBounds(674, 525, 51, 16);
		frmClue.getContentPane().add(txtrDiceRoll);
		
		JLabel diceIcon = new JLabel("");
		diceIcon.setIcon(new ImageIcon(Window.class.getResource("/Resources/6.png")));
		diceIcon.setBounds(660, 555, 75, 75);
		frmClue.getContentPane().add(diceIcon);
		frmClue.setBounds(100, 100, 1000, 700);
		frmClue.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	

}
