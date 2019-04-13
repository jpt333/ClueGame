package clueGame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Toolkit;
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
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.Label;
import java.awt.Font;

public class LoadingIntro extends JFrame {

	public final int TIMEDURRATION = 2000;
	
	private JPanel contentPane;
	private JProgressBar progressBar;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		LoadingIntro frame = new LoadingIntro();
	}

	/**
	 * Create the frame.
	 */
	public LoadingIntro() {
		
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
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoadingIntro.class.getResource("/Resources/clueGameLogo.png")));
		setBackground(Color.DARK_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		dispose();
	}
	
	public void update() {
		long startTime = System.nanoTime();
		while(TimeUnit.MILLISECONDS.convert(System.nanoTime() - startTime, TimeUnit.NANOSECONDS) < TIMEDURRATION) {
			int time = (int) TimeUnit.MILLISECONDS.convert(System.nanoTime() - startTime, TimeUnit.NANOSECONDS);
			
			progressBar.setValue(time);
			
			progressBar.repaint();
		}
	}
}
