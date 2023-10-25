package phase2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class CostScreen extends JFrame {
	static String query = "";
	static String[] tables = new String[]{"",""};
	static ArrayList<String> deptAttributes = new ArrayList<String>();
	static ArrayList<String> courseAttributes = new ArrayList<String>();
	
	private JLabel titleLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CostScreen frame = new CostScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	private JTextField queryTXT;
	
	public static void updateQuery() {
		if(tables[0].equals("")||tables[1].equals("")) {
			if(courseAttributes.isEmpty() && deptAttributes.isEmpty())
				query = "";
			else if(courseAttributes.isEmpty() && !deptAttributes.isEmpty())
				query = "SELECT " + deptAttributes.toString().replace("[","").replace("]", "") +" FROM "+tables[0]+tables[1];
			else if(!courseAttributes.isEmpty() && deptAttributes.isEmpty())
				query = "SELECT " + courseAttributes.toString().replace("[","").replace("]", "") +" FROM "+tables[0]+tables[1];
		}
		else {
			if(courseAttributes.isEmpty() && deptAttributes.isEmpty())
				query = "";
			else if(courseAttributes.isEmpty() && !deptAttributes.isEmpty())
				query = "SELECT " +  deptAttributes.toString().replace("[","").replace("]", "") +" FROM "+tables[0]+" , "+tables[1];
			else if(!courseAttributes.isEmpty() && deptAttributes.isEmpty())
				query = "SELECT " + courseAttributes.toString().replace("[","").replace("]", "")+" FROM "+tables[0]+" , "+tables[1];
			else
				query = "SELECT " +  deptAttributes.toString().replace("[","").replace("]", "") +" , "+ courseAttributes.toString().replace("[","").replace("]", "")+" FROM "+tables[0]+" , "+tables[1];
		}
	}
	
	/**
	 * Create the frame.
	 */
	public CostScreen() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("images/icon.jpg"));
		setBounds(100, 100, 830, 651);
		setTitle("Statistics Screen");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton homeBTN = new JButton("Back to Home Screen");
		homeBTN.setFont(new Font("Tahoma", Font.PLAIN, 15));
		homeBTN.setBounds(277, 576, 259, 27);
		homeBTN.setBackground(new Color(0xfedd76));
		homeBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainScreen x = new MainScreen();
				x.setVisible(true);
				setVisible(false);
				x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});
		panel.add(homeBTN);
		
		JLabel queryDisplay = new JLabel(query);
		queryDisplay.setForeground(Color.WHITE);
		queryDisplay.setFont(new Font("Tahoma", Font.BOLD, 15));
		queryDisplay.setBounds(29, 476, 750, 27);
		panel.add(queryDisplay);
		
		JRadioButton choiceBTN = new JRadioButton("Choose Values");
		choiceBTN.setForeground(Color.WHITE);
		choiceBTN.setFont(new Font("Tahoma", Font.PLAIN, 15));
		choiceBTN.setSelected(true);
		choiceBTN.setBounds(29, 47, 167, 23);
		choiceBTN.setOpaque(false);
		choiceBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				queryTXT.setEnabled(false);
			}
		});
		panel.add(choiceBTN);
		
		queryTXT = new JTextField();
		queryTXT.setEnabled(false);
		queryTXT.setBounds(206, 514, 573, 20);
		panel.add(queryTXT);
		queryTXT.setColumns(10);
		
		JRadioButton manBTN = new JRadioButton("Manually Type Query");
		manBTN.setForeground(Color.WHITE);
		manBTN.setFont(new Font("Tahoma", Font.PLAIN, 15));
		manBTN.setBounds(18, 509, 167, 27);
		manBTN.setOpaque(false);
		manBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				queryTXT.setEnabled(true);
			}
		});
		panel.add(manBTN);
		
		JRadioButton deptSpecBTN = new JRadioButton("Specific Attributes");
		deptSpecBTN.setForeground(Color.WHITE);
		deptSpecBTN.setFont(new Font("Tahoma", Font.PLAIN, 15));
		deptSpecBTN.setBounds(29, 254, 147, 27);
		deptSpecBTN.setOpaque(false);
		panel.add(deptSpecBTN);
		
		JRadioButton deptAllBTN = new JRadioButton("All attributes");
		deptAllBTN.setForeground(Color.WHITE);
		deptAllBTN.setFont(new Font("Tahoma", Font.PLAIN, 15));
		deptAllBTN.setBounds(29, 233, 111, 23);
		deptAllBTN.setSelected(true);
		deptAllBTN.setOpaque(false);
		panel.add(deptAllBTN);
		
		JLabel deptLabel = new JLabel("Department");
		deptLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		deptLabel.setForeground(Color.WHITE);
		deptLabel.setBounds(29, 213, 93, 19);
		panel.add(deptLabel);
		
		titleLabel = new JLabel("Estimate Cost of Query");
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		titleLabel.setBounds(310, 0, 248, 25);
		panel.add(titleLabel);
		
		JLabel subtitle = new JLabel("Attributes to display");
		subtitle.setForeground(Color.WHITE);
		subtitle.setFont(new Font("Tahoma", Font.ITALIC, 20));
		subtitle.setBounds(29, 186, 214, 25);
		panel.add(subtitle);
		
		JCheckBox deptNameCHK = new JCheckBox("Department Number");
		deptNameCHK.setOpaque(false);
		deptNameCHK.setForeground(Color.WHITE);
		deptNameCHK.setFont(new Font("Tahoma", Font.PLAIN, 15));
		deptNameCHK.setBounds(39, 286, 165, 27);
		panel.add(deptNameCHK);
		
		JCheckBox chckbxDepartmentCode = new JCheckBox("Department Code");
		chckbxDepartmentCode.setOpaque(false);
		chckbxDepartmentCode.setForeground(Color.WHITE);
		chckbxDepartmentCode.setFont(new Font("Tahoma", Font.PLAIN, 15));
		chckbxDepartmentCode.setBounds(39, 317, 145, 27);
		panel.add(chckbxDepartmentCode);
		
		JCheckBox deptOfficeCHK = new JCheckBox("Department Office");
		deptOfficeCHK.setForeground(Color.WHITE);
		deptOfficeCHK.setFont(new Font("Tahoma", Font.PLAIN, 15));
		deptOfficeCHK.setBounds(39, 348, 149, 27);
		deptOfficeCHK.setOpaque(false);
		panel.add(deptOfficeCHK);
		
		JCheckBox dPhoneCHK = new JCheckBox("Department Phone");
		dPhoneCHK.setForeground(Color.WHITE);
		dPhoneCHK.setFont(new Font("Tahoma", Font.PLAIN, 15));
		dPhoneCHK.setBounds(39, 379, 165, 27);
		dPhoneCHK.setOpaque(false);
		panel.add(dPhoneCHK);
		
		JCheckBox collegeCHK = new JCheckBox("College Name");
		collegeCHK.setOpaque(false);
		collegeCHK.setForeground(Color.WHITE);
		collegeCHK.setFont(new Font("Tahoma", Font.PLAIN, 15));
		collegeCHK.setBounds(39, 412, 145, 27);
		panel.add(collegeCHK);
		
		JCheckBox instIDCHK = new JCheckBox("Instructor ID");
		instIDCHK.setForeground(Color.WHITE);
		instIDCHK.setFont(new Font("Tahoma", Font.PLAIN, 15));
		instIDCHK.setBounds(39, 442, 149, 27);
		instIDCHK.setOpaque(false);
		panel.add(instIDCHK);
		
		JLabel courseLBL = new JLabel("Course");
		courseLBL.setForeground(Color.WHITE);
		courseLBL.setFont(new Font("Tahoma", Font.BOLD, 15));
		courseLBL.setBounds(458, 64, 93, 19);
		panel.add(courseLBL);
		
		JRadioButton courseAllBTN = new JRadioButton("All attributes");
		courseAllBTN.setSelected(true);
		courseAllBTN.setOpaque(false);
		courseAllBTN.setForeground(Color.WHITE);
		courseAllBTN.setFont(new Font("Tahoma", Font.PLAIN, 15));
		courseAllBTN.setBounds(455, 80, 111, 23);
		panel.add(courseAllBTN);
		
		JRadioButton courseSpecBTN = new JRadioButton("Specific Attributes");
		courseSpecBTN.setOpaque(false);
		courseSpecBTN.setForeground(Color.WHITE);
		courseSpecBTN.setFont(new Font("Tahoma", Font.PLAIN, 15));
		courseSpecBTN.setBounds(455, 98, 147, 27);
		panel.add(courseSpecBTN);
		
		JCheckBox codeCHK = new JCheckBox("Course Code");
		codeCHK.setOpaque(false);
		codeCHK.setForeground(Color.WHITE);
		codeCHK.setFont(new Font("Tahoma", Font.PLAIN, 15));
		codeCHK.setEnabled(false);
		codeCHK.setBounds(461, 123, 165, 27);
		panel.add(codeCHK);
		
		JCheckBox coNameCHK = new JCheckBox("Course Name");
		coNameCHK.setOpaque(false);
		coNameCHK.setForeground(Color.WHITE);
		coNameCHK.setFont(new Font("Tahoma", Font.PLAIN, 15));
		coNameCHK.setEnabled(false);
		coNameCHK.setBounds(462, 147, 145, 27);
		panel.add(coNameCHK);
		
		JCheckBox creditsCHK = new JCheckBox("Credits");
		creditsCHK.setOpaque(false);
		creditsCHK.setForeground(Color.WHITE);
		creditsCHK.setFont(new Font("Tahoma", Font.PLAIN, 15));
		creditsCHK.setEnabled(false);
		creditsCHK.setBounds(462, 170, 149, 27);
		panel.add(creditsCHK);
		
		JCheckBox levelCHK = new JCheckBox("Level");
		levelCHK.setOpaque(false);
		levelCHK.setForeground(Color.WHITE);
		levelCHK.setFont(new Font("Tahoma", Font.PLAIN, 15));
		levelCHK.setEnabled(false);
		levelCHK.setBounds(461, 190, 165, 30);
		panel.add(levelCHK);
		
		JCheckBox descCHK = new JCheckBox("Course Description");
		descCHK.setOpaque(false);
		descCHK.setForeground(Color.WHITE);
		descCHK.setFont(new Font("Tahoma", Font.PLAIN, 15));
		descCHK.setEnabled(false);
		descCHK.setBounds(461, 215, 151, 27);
		panel.add(descCHK);
		
		JCheckBox deptCodeCourseCHK = new JCheckBox("Department Code");
		deptCodeCourseCHK.setOpaque(false);
		deptCodeCourseCHK.setForeground(Color.WHITE);
		deptCodeCourseCHK.setFont(new Font("Tahoma", Font.PLAIN, 15));
		deptCodeCourseCHK.setEnabled(false);
		deptCodeCourseCHK.setBounds(461, 268, 151, 27);
		panel.add(deptCodeCourseCHK);
		
		JCheckBox courseDeptNameCHK = new JCheckBox("Department Name");
		courseDeptNameCHK.setOpaque(false);
		courseDeptNameCHK.setForeground(Color.WHITE);
		courseDeptNameCHK.setFont(new Font("Tahoma", Font.PLAIN, 15));
		courseDeptNameCHK.setEnabled(false);
		courseDeptNameCHK.setBounds(462, 240, 149, 27);
		panel.add(courseDeptNameCHK);
		
		JLabel lblNewLabel = new JLabel("Table");
		lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(29, 77, 129, 25);
		panel.add(lblNewLabel);
		
		JCheckBox deptCheck = new JCheckBox("Department Table");
		deptCheck.setForeground(Color.WHITE);
		deptCheck.setFont(new Font("Tahoma", Font.PLAIN, 15));
		deptCheck.setBounds(29, 109, 147, 27);
		deptCheck.setOpaque(false);
		deptCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(deptCheck.isSelected())
				{
					deptAttributes.clear();
					tables[0] = "dept D";
					deptAttributes.add("D.*");
				}
					
				if(!deptCheck.isSelected())
				{
					deptAttributes.clear();
					tables[0] = "";
				}
				
				updateQuery();
				
				queryDisplay.setText(query);
				
			}
		});
		panel.add(deptCheck);
		
		
		JCheckBox courseCheck = new JCheckBox("Course Table");
		courseCheck.setForeground(Color.WHITE);
		courseCheck.setFont(new Font("Tahoma", Font.PLAIN, 15));
		courseCheck.setBounds(29, 137, 117, 27);
		courseCheck.setOpaque(false);
		courseCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(courseCheck.isSelected()) 
				{
					courseAttributes.clear();
					tables[1] = "Course C";
					courseAttributes.add("C.*");
				}
				
				if(!courseCheck.isSelected())
				{
					tables[1] = "";
					courseAttributes.clear();
				}
					
				updateQuery();
				
				queryDisplay.setText(query);
				
			}
		});
		panel.add(courseCheck);
		
		ButtonGroup option = new ButtonGroup();
		option.add(manBTN);
		option.add(choiceBTN);
		
		deptNameCHK.setEnabled(false);
		chckbxDepartmentCode.setEnabled(false);
		deptOfficeCHK.setEnabled(false);
		dPhoneCHK.setEnabled(false);
		collegeCHK.setEnabled(false);
		instIDCHK.setEnabled(false);
		
		deptAllBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deptNameCHK.setEnabled(false);
				chckbxDepartmentCode.setEnabled(false);
				deptOfficeCHK.setEnabled(false);
				dPhoneCHK.setEnabled(false);
				collegeCHK.setEnabled(false);
				instIDCHK.setEnabled(false);
				
				deptAttributes.clear();
				deptAttributes.add("D.*");
				if(tables[0].equals("")||tables[1].equals(""))
					query = "SELECT " + deptAttributes.get(0) +" FROM "+tables[0]+tables[1];
				else
					query = "SELECT " + deptAttributes.get(0) +" FROM "+tables[0]+" , "+tables[1];
				
				queryDisplay.setText(query);
			}
		});
		
		deptSpecBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deptNameCHK.setEnabled(true);
				chckbxDepartmentCode.setEnabled(true);
				deptOfficeCHK.setEnabled(true);
				dPhoneCHK.setEnabled(true);
				collegeCHK.setEnabled(true);
				instIDCHK.setEnabled(true);
				
				deptAttributes.clear();
				
				if(tables[0].equals("")||tables[1].equals(""))
					query = "SELECT ? FROM "+tables[0]+tables[1];
				else
					query = "SELECT ? FROM "+tables[0]+" , "+tables[1];
				
				queryDisplay.setText(query);
			}
		});
		
		courseAllBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				courseDeptNameCHK.setEnabled(false);
				deptCodeCourseCHK.setEnabled(false);
				descCHK.setEnabled(false);
				levelCHK.setEnabled(false);
				creditsCHK.setEnabled(false);
				coNameCHK.setEnabled(false);
				codeCHK.setEnabled(false);
				
				courseAttributes.clear();
				courseAttributes.add("C.*");
				if(tables[0].equals("")||tables[1].equals(""))
					query = "SELECT " + courseAttributes.get(0) +" FROM "+tables[0]+tables[1];
				else
					query = "SELECT " + courseAttributes.get(0) +" FROM "+tables[0]+" , "+tables[1];
				
				queryDisplay.setText(query);
			}
		});
		
		courseSpecBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				courseDeptNameCHK.setEnabled(true);
				deptCodeCourseCHK.setEnabled(true);
				descCHK.setEnabled(true);
				levelCHK.setEnabled(true);
				creditsCHK.setEnabled(true);
				coNameCHK.setEnabled(true);
				codeCHK.setEnabled(true);
				
				courseAttributes.clear();
				
				if(tables[0].equals("")||tables[1].equals(""))
					query = "SELECT ? FROM "+tables[0]+tables[1];
				else
					query = "SELECT ? FROM "+tables[0]+" , "+tables[1];
				
				queryDisplay.setText(query);
			}
		});
		
		ButtonGroup deptAttributes = new ButtonGroup();
		deptAttributes.add(deptAllBTN);
		deptAttributes.add(deptSpecBTN);
		
		ButtonGroup courseAttributes = new ButtonGroup();
		courseAttributes.add(courseAllBTN);
		courseAttributes.add(courseSpecBTN);
		
		JButton generateBTN = new JButton("Generate Cost Estimation");
		generateBTN.setFont(new Font("Tahoma", Font.PLAIN, 15));
		generateBTN.setBounds(277, 542, 258, 23);
		generateBTN.setBackground(new Color(0xfedd76));
		generateBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(query);
			}
		});
		panel.add(generateBTN);
		
		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon("images/backgroundResized.jpg"));
		background.setBounds(0, 0, 829, 631);
		panel.add(background);
	}
}
