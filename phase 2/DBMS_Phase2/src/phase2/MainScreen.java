package phase2;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class MainScreen extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainScreen frame = new MainScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainScreen() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("images/icon.jpg"));
		setBounds(100, 100, 830, 651);
		setTitle("Cost Estimator and Statistics");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel nameTitle = new JLabel("Cost Estimator & Statistics");
		nameTitle.setForeground(Color.WHITE);
		nameTitle.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 28));
		nameTitle.setBounds(31, 215, 404, 37);
		panel.add(nameTitle);
		
		JLabel subtitleLabel = new JLabel("A DBMS Project");
		subtitleLabel.setForeground(Color.WHITE);
		subtitleLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		subtitleLabel.setBounds(154, 385, 106, 19);
		panel.add(subtitleLabel);
		
		JButton statisticsBTN = new JButton("View Statistics");
		statisticsBTN.setFont(new Font("Tahoma", Font.PLAIN, 20));
		statisticsBTN.setBackground(new Color(0xfedd76));
		statisticsBTN.setBounds(79, 341, 266, 33);
		statisticsBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StatisticsScreen x = new StatisticsScreen();
//				x.setVisible(true);
				setVisible(false);
				x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});
		panel.add(statisticsBTN);
		
		JButton costButton = new JButton("Cost Estimator");
		costButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		costButton.setBackground(new Color(0xfedd76));
		costButton.setForeground(Color.BLACK);
		costButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CostScreen x = new CostScreen();
				x.setVisible(true);
				setVisible(false);
				x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});

		costButton.setBounds(79, 281, 266, 33);
		panel.add(costButton);
		
		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon("images/mainscreenResized.jpg"));
		background.setBounds(0, 0, 829, 631);
		panel.add(background);
	}
}
