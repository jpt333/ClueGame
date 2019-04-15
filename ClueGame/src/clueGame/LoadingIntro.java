package clueGame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.JProgressBar;
import java.awt.GridLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import net.miginfocom.swing.MigLayout;
import javax.swing.SpringLayout;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.Label;
import java.awt.Font;

public class LoadingIntro extends JDialog  {

	public final int TIMEDURRATION = 2000;
	
	private JPanel contentPane;
	private JProgressBar progressBar;
	
	public LoadingIntro(Player self) {
		AssetsManager assets = new AssetsManager();
		final String FUNNYLINES[] = new String[9];
		FUNNYLINES[0] = "Scrunching Scrunchies";
		FUNNYLINES[1] = "Removing Stripes from Striped Horses";
		FUNNYLINES[2] = "Optimizing the People";
		FUNNYLINES[3] = "Solving World Hunger";
		FUNNYLINES[4] = "Localizing";
		FUNNYLINES[5] = "Reporting Bugs";
		FUNNYLINES[6] = "Sock-Sock-Shoe-Shoe";
		FUNNYLINES[7] = "Sock-Shoe-Sock-Shoe";
		FUNNYLINES[8] = "Loading Next Loading Joke";
		
		Random rand = new Random();
		int randomNum = rand.nextInt(FUNNYLINES.length);
		
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoadingIntro.class.getResource("/Resources/Miscellaneous/clueGameLogo.png")));
		setBackground(Color.DARK_GRAY);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		progressBar = new JProgressBar(0, TIMEDURRATION);
		progressBar.setBounds(100, 100, 250, 25);
		contentPane.add(progressBar);
		
		Label label = new Label(FUNNYLINES[randomNum]);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Arial", Font.PLAIN, 20));
		label.setAlignment(Label.CENTER);
		label.setBounds(10, 149, 424, 35);
		contentPane.add(label);
		
		setVisible(true);
		update();
		
		JLabel humanPlayerTitle = new JLabel("You are:");
		humanPlayerTitle.setForeground(Color.WHITE);
		humanPlayerTitle.setBackground(Color.WHITE);
		humanPlayerTitle.setBounds(203, 14, 100, 10);
		contentPane.add(humanPlayerTitle);
		
		//the card the person will be
		JLabel humanPlayer = new JLabel("");
		
		assets.setAsset(humanPlayer, self.getPlayerName(), CardType.PERSON);
		
		humanPlayer.setForeground(Color.WHITE);
		humanPlayer.setBackground(Color.WHITE);
		humanPlayer.setBounds(175, 40, 100, 150);
		contentPane.add(humanPlayer);
		
		//button to continue
		JButton accusationButton = new JButton("Ok");
		accusationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		accusationButton.setBounds(188, 215, 75, 40);
		contentPane.add(accusationButton);
		
		setAlwaysOnTop (true);
		contentPane.repaint();
		
	}
	
	private void update() {
		long startTime = System.nanoTime();
		while(TimeUnit.MILLISECONDS.convert(System.nanoTime() - startTime, TimeUnit.NANOSECONDS) < TIMEDURRATION) {
			int time = (int) TimeUnit.MILLISECONDS.convert(System.nanoTime() - startTime, TimeUnit.NANOSECONDS);
			
			progressBar.setValue(time);
			
			progressBar.repaint();
		}
		
		contentPane.removeAll();
		contentPane.repaint();
	}
}
