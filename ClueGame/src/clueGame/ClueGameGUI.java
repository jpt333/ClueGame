//Authors: Michael Berg and Jennifer Phan
package clueGame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.util.Set;
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
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.AbstractAction;
import javax.swing.Action;

public class ClueGameGUI extends JFrame{

	private JFrame clueWindow;
	private AssetsManager assets;
	
	private JLabel diceIcon;
	private JLabel playTurnCard;
	private JLabel guessResultCard;
	private JLabel weaponcard;
	private JLabel personCard;
	private JLabel roomCard;
	
	private Board gameBoard;
	private Player self;
	
	
	boolean moved = false;
	//generated using window builder
	
	public static void main(String[] args) {
			ClueGameGUI window = new ClueGameGUI();
	}

	
	ClueGameGUI() {
		//everything concerning board set up and the first turn goes here
		
		assets = new AssetsManager();
		//set layout and colors
		clueWindow = new JFrame();
		clueWindow.setResizable(false);
		clueWindow.getContentPane().setEnabled(false);
		clueWindow.setForeground(Color.WHITE);
		clueWindow.setIconImage(Toolkit.getDefaultToolkit().getImage(ClueGameGUI.class.getResource("/Resources/Miscellaneous/clueGameLogo.png")));
		clueWindow.setTitle("Clue Game");
		clueWindow.getContentPane().setBackground(Color.DARK_GRAY);
		clueWindow.getContentPane().setLayout(null);
		
		addButtons();
		addElements();
		//loads the game board
		
		gameBoard = Board.getInstance();
		gameBoard.setConfigFiles("ClueBoard.csv", "rooms.txt");
		gameBoard.setCardFiles("weapons.txt", "Person.txt");
		gameBoard.initialize();
		gameBoard.dealCards();
		gameBoard.setBounds(0, 25, 702, 676);
		clueWindow.getContentPane().add(gameBoard);
		
		Random rand = new Random();
		//6 because the max dice number is 6
		int diceRoll = rand.nextInt(6) + 1;
		
		self = gameBoard.getHumanPlayer();
		gameBoard.calcTargets(self.getCurrentLocation().getLocation().y, self.getCurrentLocation().getLocation().x, diceRoll);
		
		Set<Card> cards = self.getCards();
		Card cardArray[] = new Card[cards.size()];
		cards.toArray(cardArray);
		
		//which players turn it is
		
		playTurnCard = new JLabel("");
		
		assets.setAsset(playTurnCard, self.getPlayerName(), CardType.PERSON);
		
		playTurnCard.setForeground(Color.WHITE);
		playTurnCard.setBackground(Color.WHITE);
		playTurnCard.setBounds(507, 722, 100, 150);
		clueWindow.getContentPane().add(playTurnCard);
		
		//cards that were dealt to the player
		
		JLabel card1 = new JLabel("");
		
		assets.setAsset(card1, cardArray[0].getCardName() , cardArray[0].getCardType());
		
		card1.setForeground(Color.WHITE);
		card1.setBackground(Color.WHITE);
		card1.setBounds(705, 96, 100, 150);
		clueWindow.getContentPane().add(card1);
		
		JLabel card2 = new JLabel("");
		
		assets.setAsset(card2, cardArray[1].getCardName() , cardArray[1].getCardType());
		
		card2.setForeground(Color.WHITE);
		card2.setBackground(Color.WHITE);
		card2.setBounds(705, 271, 100, 150);
		clueWindow.getContentPane().add(card2);
		
		JLabel card3 = new JLabel("");
		
		assets.setAsset(card3, cardArray[2].getCardName() , cardArray[2].getCardType());
		
		card3.setForeground(Color.WHITE);
		card3.setBackground(Color.WHITE);
		card3.setBounds(705, 447, 100, 150);
		clueWindow.getContentPane().add(card3);
	
		//shows the dice elements
		
		JTextArea diceText = new JTextArea();
		diceText.setText("Dice Roll");
		diceText.setForeground(Color.WHITE);
		diceText.setBackground(Color.DARK_GRAY);
		diceText.setBounds(728, 621, 51, 16);
		clueWindow.getContentPane().add(diceText);
		
		diceIcon = new JLabel("");
		
		//any number outside 1-6 will display a question mark
		assets.setAsset(diceIcon, diceRoll);
		
		diceIcon.setBounds(719, 660, 75, 75);
		clueWindow.getContentPane().add(diceIcon);
		clueWindow.setBounds(100, 100, 848, 920);
		clueWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		LoadingIntro loadingWindow = new LoadingIntro(self);
		
		clueWindow.setVisible(true);
		
		boardMovment();
	}
	

	private void addButtons() {
		JButton accusationButton = new JButton("Make an Accusation");
		
		accusationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Acusation accusation = new Acusation();
				accusation.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				accusation.setVisible(true);
			}
		});
		
		accusationButton.setBounds(673, 822, 158, 38);
		clueWindow.getContentPane().add(accusationButton);
		
		//Next Player button
		JButton nextPlayer = new JButton("Next Player");
		nextPlayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//what happens when next player is pressed
				ComputerPlayer currentPlayer = gameBoard.nextPlayer();
				if(moved) {
					Random rand = new Random();
					int diceRoll = rand.nextInt(6) + 1;
					if(currentPlayer == null) {
						//human players turn
						moved = false;
						gameBoard.calcTargets(self.getCurrentLocation().getLocation().y, self.getCurrentLocation().getLocation().x, diceRoll);
						assets.setAsset(diceIcon, diceRoll);
						assets.setAsset(playTurnCard, self.getPlayerName(), CardType.PERSON);
						//check if in room 
						redraw();
					}else {
						//computer players turn
						assets.setAsset(diceIcon, diceRoll);
						assets.setAsset(playTurnCard, currentPlayer.getPlayerName(), CardType.PERSON);
						gameBoard.calcTargets(currentPlayer.getCurrentLocation().getLocation().y, currentPlayer.getCurrentLocation().getLocation().x, diceRoll);
						currentPlayer.setCurrentLocation(currentPlayer.pickLocation(gameBoard.getTargets()));
						gameBoard.deleteTargets();
						//check if in room 
						if(currentPlayer.getCurrentLocation().isRoom()) {
							//make a suggestion
							
							//have the board handle the suggestion
						}
						redraw();
					}
				}
			}
		});
		nextPlayer.setBounds(673, 758, 158, 38);
		clueWindow.getContentPane().add(nextPlayer);
	}
	
	private void addElements() {
		JTextArea guessResult = new JTextArea();
		guessResult.setForeground(Color.WHITE);
		guessResult.setBackground(Color.DARK_GRAY);
		guessResult.setText("Guess Result");
		guessResult.setBounds(23, 681, 76, 16);
		clueWindow.getContentPane().add(guessResult);
		
		JTextArea guess = new JTextArea();
		guess.setText("Guess");
		guess.setForeground(Color.WHITE);
		guess.setBackground(Color.DARK_GRAY);
		guess.setBounds(283, 681, 37, 16);
		clueWindow.getContentPane().add(guess);
		
		JTextArea turnText = new JTextArea();
		turnText.setText("Whose turn?");
		turnText.setForeground(Color.WHITE);
		turnText.setBackground(Color.DARK_GRAY);
		turnText.setBounds(523, 681, 76, 16);
		clueWindow.getContentPane().add(turnText);
		
		JTextArea myCards = new JTextArea();
		myCards.setText("My Cards");
		myCards.setForeground(Color.WHITE);
		myCards.setBackground(Color.DARK_GRAY);
		myCards.setBounds(728, 58, 51, 16);
		clueWindow.getContentPane().add(myCards);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 994, 25);
		clueWindow.getContentPane().add(menuBar);
		
		JMenu dropMenu = new JMenu("File");
		menuBar.add(dropMenu);
		//add detectivenotes to dropmenu in File
		JMenuItem detectiveNotes = new JMenuItem("Detective Notes");
		detectiveNotes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DetectiveNotesDialog dialog = new DetectiveNotesDialog();
				dialog.setVisible(true);
			}
		});
		
		dropMenu.add(detectiveNotes);
		//add quit to dropmenu
		JMenuItem quit = new JMenuItem("Quit");
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clueWindow.dispose();
			}
		});
		dropMenu.add(quit);
		
		//this is the result of the guess
		guessResultCard = new JLabel("");
		
		assets.setAsset(guessResultCard, "Unknown", CardType.PERSON);
		
		guessResultCard.setForeground(SystemColor.text);
		guessResultCard.setBackground(SystemColor.window);
		guessResultCard.setBounds(12, 722, 100, 150);
		clueWindow.getContentPane().add(guessResultCard);
		
		
		//these three are the guess cards
		weaponcard = new JLabel("");
		
		assets.setAsset(weaponcard, "Unknown", CardType.PERSON);
		
		weaponcard.setForeground(Color.WHITE);
		weaponcard.setBackground(Color.WHITE);
		weaponcard.setBounds(143, 722, 100, 150);
		clueWindow.getContentPane().add(weaponcard);
		
		personCard = new JLabel("");
		
		assets.setAsset(personCard, "Unknown", CardType.PERSON);
		
		personCard.setForeground(Color.WHITE);
		personCard.setBackground(Color.WHITE);
		personCard.setBounds(255, 722, 100, 150);
		clueWindow.getContentPane().add(personCard);
		
		roomCard = new JLabel("");
		
		assets.setAsset(roomCard, "Unknown", CardType.PERSON);
		
		roomCard.setForeground(Color.WHITE);
		roomCard.setBackground(Color.WHITE);
		roomCard.setBounds(367, 722, 100, 150);
		clueWindow.getContentPane().add(roomCard);
	}
	
	void boardMovment(){
		gameBoard.addMouseListener(new MouseAdapter() {
			@Override
            public void mouseReleased(MouseEvent e) {
				//compare this point to the location 
				BoardCell chosenBox = null;
				//check if chosen target from the targets
				for(int i = 0; i < gameBoard.getNumRows(); i++) {
					for(int j = 0; j < gameBoard.getNumColumns(); j++) {
						if(gameBoard.getCellAt(i,j).hasTarget(e.getX(), e.getY())) {
							chosenBox = gameBoard.getCellAt(i,j);
							
							repaint();
							break;
						}
					}
				}
				//if cell exists and is a target, repaint
				
				if(chosenBox == null) {
					//do nothing
				}else if(gameBoard.getTargets().contains(chosenBox) && !moved){
					self.setCurrentLocation(chosenBox);
					gameBoard.deleteTargets();
					moved = true;
					redraw();
					if(chosenBox.isRoom()) {
						//make a suggestion
						Acusation accusation = new Acusation();
						if(chosenBox.getInitial() == 'B') {
							accusation = new Acusation("Butterfly Room");
						}else if(chosenBox.getInitial() == 'C') {
							accusation = new Acusation("Computer Room");
						}else if(chosenBox.getInitial() == 'K') {
							accusation = new Acusation("Kitchen");
						}else if(chosenBox.getInitial() == 'D') {
							accusation = new Acusation("Dining Room");
						}else if(chosenBox.getInitial() == 'A') {
							accusation = new Acusation("Bedroom");
						}else if(chosenBox.getInitial() == 'P') {
							accusation = new Acusation("Parlor");
						}else if(chosenBox.getInitial() == 'M') {
							accusation = new Acusation("Music Room");
						}else if(chosenBox.getInitial() == 'G') {
							accusation = new Acusation("Game Room");
						}else if(chosenBox.getInitial() == 'L') {
							accusation = new Acusation("Living Room");
						}
						accusation.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						accusation.setVisible(true);
						//have the board handle the suggestion
						Solution suggestion = new Solution();
						
						//bad solution but it might just work
						suggestion.person = new Card(accusation.getSelectedPerson(), CardType.PERSON);
						suggestion.room = new Card(accusation.getSelectedRoom(), CardType.ROOM);
						suggestion.weapon = new Card(accusation.getSelectedWeapon(), CardType.WEAPON);
						
						Card guessResult = gameBoard.handleSuggestionTech(suggestion);
						
						assets.setAsset(personCard, accusation.getSelectedPerson(), CardType.PERSON);
						assets.setAsset(weaponcard, accusation.getSelectedRoom(), CardType.WEAPON);
						assets.setAsset(roomCard, accusation.getSelectedWeapon(), CardType.ROOM);
						assets.setAsset(guessResultCard, guessResult.getCardName(), guessResult.getCardType());
						
					}
					redraw();
				}else if(!gameBoard.getTargets().contains(chosenBox) && !moved) {
					JOptionPane.showMessageDialog(null, "That is not target.", "Message", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
	}
	
	private void redraw() {
		gameBoard.revalidate();
		gameBoard.repaint();
		clueWindow.revalidate();
		clueWindow.repaint();
	}
	
}
