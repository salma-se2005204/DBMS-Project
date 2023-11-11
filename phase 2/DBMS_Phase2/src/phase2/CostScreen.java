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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.SwingConstants;

public class CostScreen extends JFrame {
	static String query = "";
	static String[] tables = new String[]{"",""};
	static ArrayList<String> deptAttributes = new ArrayList<String>();
	static ArrayList<String> courseAttributes = new ArrayList<String>();
	static ArrayList <String> conditions = new ArrayList<String>();
	static String join ="AND";
	
	static String [] dept = new String[] {"Department Name","Department Code","Department Office","Department Phone","College Name","Instructor ID"};
	static String [] deptValue = new String[] {"D.DName","D.DCode","D.DOffice","D.DPhone","D.CollegeCName","D.InstructorId"};
	static String [] deptType = new String[] {"String","int","String","String","String","int"};
	static String [] courseAt = new String[] {"Course Code","Course Name","Credits","Level","Course Description","Department Name"};
	static String [] courseValue = new String[] {"C.CCode","C.CoName","C.Credits","C.Level","C.CDesc","C.DeptDName"};
	static String [] courseType = new String[] {"int","String","int","int","String","String"};
	
	//for combo box
	public class ComboItem {
	    private String label;
	    private String value;
	    private String type;

	    public ComboItem(String label, String value, String type) {
	        this.label = label;
	        this.value = value;
	        this.type = type;
	    }

	    @Override
	    public String toString() {
	        return label;
	    }

	    public String getValue() {
	        return value;
	    }
	    
	    public String getType() {
	        return type;
	    }
	}


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
	
	public static void updateQuery() {
		if(tables[0].equals("")||tables[1].equals("")) {
			if(courseAttributes.isEmpty() && deptAttributes.isEmpty())
				query = "";
			else if(courseAttributes.isEmpty() && !deptAttributes.isEmpty() && conditions.isEmpty())
				query = "SELECT " + deptAttributes.toString().replace("[","").replace("]", "") +"\nFROM "+tables[0]+tables[1];
			else if(courseAttributes.isEmpty() && !deptAttributes.isEmpty() && !conditions.isEmpty()) {
				if(conditions.size()==1)
					query = "SELECT " + deptAttributes.toString().replace("[","").replace("]", "") +"\nFROM "+tables[0]+tables[1]+"\nWHERE "+conditions.toString().replace("[","").replace("]", "");
				else
					query = "SELECT " + deptAttributes.toString().replace("[","").replace("]", "") +"\nFROM "+tables[0]+tables[1]+"\nWHERE "+conditions.toString().replace("[","").replace("]", "").replace(",", " "+join+" ");
			}
			else if(!courseAttributes.isEmpty() && deptAttributes.isEmpty() && conditions.isEmpty())
				query = "SELECT " + courseAttributes.toString().replace("[","").replace("]", "") +"\nFROM "+tables[0]+tables[1];
			else if(!courseAttributes.isEmpty() && deptAttributes.isEmpty() && !conditions.isEmpty()) {
				if(conditions.size()==1)
					query = "SELECT " + courseAttributes.toString().replace("[","").replace("]", "") +"\nFROM "+tables[0]+tables[1]+"\nWHERE "+conditions.toString().replace("[","").replace("]", "");
				else
					query = "SELECT " + courseAttributes.toString().replace("[","").replace("]", "") +"\nFROM "+tables[0]+tables[1]+"\nWHERE "+conditions.toString().replace("[","").replace("]", "").replace(",", " "+join+" ");
			}
		}
		else {
			if(courseAttributes.isEmpty() && deptAttributes.isEmpty())
				query = "";
			else if(courseAttributes.isEmpty() && !deptAttributes.isEmpty() && conditions.isEmpty())
				query = "SELECT " +  deptAttributes.toString().replace("[","").replace("]", "") +"\nFROM "+tables[0]+" , "+tables[1];
			else if(courseAttributes.isEmpty() && !deptAttributes.isEmpty() && !conditions.isEmpty())
			{
				if(conditions.size()==1)
					query = "SELECT " +  deptAttributes.toString().replace("[","").replace("]", "") +"\nFROM "+tables[0]+" , "+tables[1]+"\nWHERE "+conditions.toString().replace("[","").replace("]", "");
				else
					query = "SELECT " +  deptAttributes.toString().replace("[","").replace("]", "") +"\nFROM "+tables[0]+" , "+tables[1]+"\nWHERE "+conditions.toString().replace("[","").replace("]", "").replace(",", " "+join+" ");
			}
			else if(!courseAttributes.isEmpty() && deptAttributes.isEmpty() && conditions.isEmpty()) {
				if(conditions.size()==1)
					query = "SELECT " +  courseAttributes.toString().replace("[","").replace("]", "") +"\nFROM "+tables[0]+" , "+tables[1]+"\nWHERE "+conditions.toString().replace("[","").replace("]", "");
				else
					query = "SELECT " +  courseAttributes.toString().replace("[","").replace("]", "") +"\nFROM "+tables[0]+" , "+tables[1]+"\nWHERE "+conditions.toString().replace("[","").replace("]", "").replace(",", " "+join+" ");
			}
			else {
				if(conditions.isEmpty()) {
					query = "SELECT " +  deptAttributes.toString().replace("[","").replace("]", "") +" , "+ courseAttributes.toString().replace("[","").replace("]", "")+"\nFROM "+tables[0]+" , "+tables[1]+"\nWHERE C.DeptDName = D.DName";
				}
				else if(conditions.size()==1)
					query = "SELECT " +  deptAttributes.toString().replace("[","").replace("]", "") +" , "+ courseAttributes.toString().replace("[","").replace("]", "")+"\nFROM "+tables[0]+" , "+tables[1]+"\nWHERE C.DeptDName = D.DName AND "+conditions.toString().replace("[","").replace("]", "");
				else
					query = "SELECT " +  deptAttributes.toString().replace("[","").replace("]", "") +" , "+ courseAttributes.toString().replace("[","").replace("]", "")+"\nFROM "+tables[0]+" , "+tables[1]+"\nWHERE C.DeptDName = D.DName AND "+conditions.toString().replace("[","").replace("]", "").replace(",", " "+join+" ");
			}
		}
	}
	
	/**
	 * Create the frame.
	 */
	public CostScreen() {
		JLabel titleLabel;
		JTextField cond1_State;
		JTextField cond2_State;
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("images/icon.jpg"));
		setBounds(100, 100, 830, 651);
		setTitle("Statistics Screen");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JPanel department = new JPanel();
		department.setBounds(35, 133, 214, 256);
		department.setOpaque(false);
		department.setVisible(false);
		panel.add(department);
		department.setLayout(null);
		
		ButtonGroup deptGroup = new ButtonGroup();
		
		JRadioButton deptSpecBTN = new JRadioButton("Specific Attributes");
		deptSpecBTN.setBounds(0, 41, 147, 27);
		department.add(deptSpecBTN);
		deptSpecBTN.setForeground(Color.WHITE);
		deptSpecBTN.setFont(new Font("Tahoma", Font.PLAIN, 15));
		deptSpecBTN.setOpaque(false);
		deptGroup.add(deptSpecBTN);
		
		JRadioButton deptAllBTN = new JRadioButton("All attributes");
		deptAllBTN.setBounds(0, 20, 111, 23);
		department.add(deptAllBTN);
		deptAllBTN.setForeground(Color.WHITE);
		deptAllBTN.setFont(new Font("Tahoma", Font.PLAIN, 15));
		deptAllBTN.setSelected(true);
		deptAllBTN.setOpaque(false);
		deptGroup.add(deptAllBTN);
		
		JLabel deptLabel = new JLabel("Department");
		deptLabel.setBounds(0, 0, 93, 19);
		department.add(deptLabel);
		deptLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		deptLabel.setForeground(Color.WHITE);
		
		JTextArea queryDisplay = new JTextArea("Query will be displayed here");
		queryDisplay.setEditable(false);
		queryDisplay.setOpaque(false);
		queryDisplay.setLineWrap(true);
		queryDisplay.setWrapStyleWord(true);
		
		JCheckBox deptNameCHK = new JCheckBox("Department Name");
		deptNameCHK.setBounds(10, 73, 165, 27);
		department.add(deptNameCHK);
		deptNameCHK.setOpaque(false);
		deptNameCHK.setForeground(Color.WHITE);
		deptNameCHK.setFont(new Font("Tahoma", Font.PLAIN, 15));
		deptNameCHK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(deptNameCHK.isSelected())
				{
					deptAttributes.add("D.DName");
				}
					
				if(!deptNameCHK.isSelected())
				{
					deptAttributes.remove("D.DName");
				}
				
				updateQuery();
				
				queryDisplay.setText(query);
				
			}
		});
		
		deptNameCHK.setEnabled(false);
		
		ButtonGroup courseGroup = new ButtonGroup();
		
		JCheckBox chckbxDepartmentCode = new JCheckBox("Department Code");
		chckbxDepartmentCode.setBounds(10, 104, 145, 27);
		department.add(chckbxDepartmentCode);
		chckbxDepartmentCode.setOpaque(false);
		chckbxDepartmentCode.setForeground(Color.WHITE);
		chckbxDepartmentCode.setFont(new Font("Tahoma", Font.PLAIN, 15));
		chckbxDepartmentCode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxDepartmentCode.isSelected())
				{
					deptAttributes.add("D.DCode");
				}
					
				if(!chckbxDepartmentCode.isSelected())
				{
					deptAttributes.remove("D.DCode");
				}
				
				updateQuery();
				
				queryDisplay.setText(query);
				
			}
		});
		chckbxDepartmentCode.setEnabled(false);
		
		JCheckBox deptOfficeCHK = new JCheckBox("Department Office");
		deptOfficeCHK.setBounds(10, 135, 149, 27);
		department.add(deptOfficeCHK);
		deptOfficeCHK.setForeground(Color.WHITE);
		deptOfficeCHK.setFont(new Font("Tahoma", Font.PLAIN, 15));
		deptOfficeCHK.setOpaque(false);
		deptOfficeCHK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(deptOfficeCHK.isSelected())
				{
					deptAttributes.add("D.DOffice");
				}
					
				if(!deptOfficeCHK.isSelected())
				{
					deptAttributes.remove("D.DOffice");
				}
				
				updateQuery();
				
				queryDisplay.setText(query);
				
			}
		});
		deptOfficeCHK.setEnabled(false);
		
		JCheckBox dPhoneCHK = new JCheckBox("Department Phone");
		dPhoneCHK.setBounds(10, 166, 165, 27);
		department.add(dPhoneCHK);
		dPhoneCHK.setForeground(Color.WHITE);
		dPhoneCHK.setFont(new Font("Tahoma", Font.PLAIN, 15));
		dPhoneCHK.setOpaque(false);
		dPhoneCHK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dPhoneCHK.isSelected())
				{
					deptAttributes.add("D.DPhone");
				}
					
				if(!dPhoneCHK.isSelected())
				{
					deptAttributes.remove("D.DPhone");
				}
				
				updateQuery();
				
				queryDisplay.setText(query);
				
			}
		});
		dPhoneCHK.setEnabled(false);
		
		JCheckBox collegeCHK = new JCheckBox("College Name");
		collegeCHK.setBounds(10, 199, 145, 27);
		department.add(collegeCHK);
		collegeCHK.setOpaque(false);
		collegeCHK.setForeground(Color.WHITE);
		collegeCHK.setFont(new Font("Tahoma", Font.PLAIN, 15));
		collegeCHK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(collegeCHK.isSelected())
				{
					deptAttributes.add("D.CollegeCName");
				}
					
				if(!collegeCHK.isSelected())
				{
					deptAttributes.remove("D.CollegeCName");
				}
				
				updateQuery();
				
				queryDisplay.setText(query);
				
			}
		});
		collegeCHK.setEnabled(false);
		
		JCheckBox instIDCHK = new JCheckBox("Instructor ID");
		instIDCHK.setBounds(10, 229, 149, 27);
		department.add(instIDCHK);
		instIDCHK.setForeground(Color.WHITE);
		instIDCHK.setFont(new Font("Tahoma", Font.PLAIN, 15));
		instIDCHK.setOpaque(false);
		instIDCHK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(instIDCHK.isSelected())
				{
					deptAttributes.add("D.InstructorId");
				}
					
				if(!instIDCHK.isSelected())
				{
					deptAttributes.remove("D.InstructorId");
				}
				
				updateQuery();
				
				queryDisplay.setText(query);
				
			}
		});
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
				
				updateQuery();
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
				updateQuery();
				
//				if(tables[0].equals("")||tables[1].equals(""))
//					query = "SELECT ? FROM "+tables[0]+tables[1];
//				else
//					query = "SELECT ? FROM "+tables[0]+" , "+tables[1];
				
				queryDisplay.setText(query);
			}
		});
		
		JButton homeBTN = new JButton("Back to Home Screen");
		homeBTN.setFont(new Font("Tahoma", Font.PLAIN, 15));
		homeBTN.setBounds(269, 540, 259, 27);
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
		
		queryDisplay.setForeground(Color.WHITE);
		queryDisplay.setFont(new Font("Tahoma", Font.BOLD, 15));
		queryDisplay.setBounds(491, 70, 283, 153);
		panel.add(queryDisplay);
		
		JTextArea costDisplay = new JTextArea("Cost: to be Generated");
		costDisplay.setWrapStyleWord(true);
		costDisplay.setOpaque(false);
		costDisplay.setLineWrap(true);
		costDisplay.setForeground(Color.WHITE);
		costDisplay.setFont(new Font("Tahoma", Font.BOLD, 13));
		costDisplay.setEditable(false);
		costDisplay.setBounds(491, 234, 283, 178);
		panel.add(costDisplay);
		
		titleLabel = new JLabel("Estimate Cost of Query");
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		titleLabel.setBounds(310, 0, 248, 25);
		panel.add(titleLabel);
		
		JLabel subtitle = new JLabel("Attributes to display");
		subtitle.setForeground(Color.WHITE);
		subtitle.setFont(new Font("Tahoma", Font.ITALIC, 20));
		subtitle.setBounds(35, 106, 214, 25);
		panel.add(subtitle);
		
		JPanel course = new JPanel();
		course.setBounds(292, 133, 171, 203);
		course.setVisible(false);
		course.setOpaque(false);
		panel.add(course);
		course.setLayout(null);
		
		JLabel courseLBL = new JLabel("Course");
		courseLBL.setBounds(3, 0, 93, 19);
		course.add(courseLBL);
		courseLBL.setForeground(Color.WHITE);
		courseLBL.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JRadioButton courseAllBTN = new JRadioButton("All attributes");
		courseAllBTN.setBounds(0, 16, 111, 23);
		course.add(courseAllBTN);
		courseAllBTN.setSelected(true);
		courseAllBTN.setOpaque(false);
		courseAllBTN.setForeground(Color.WHITE);
		courseAllBTN.setFont(new Font("Tahoma", Font.PLAIN, 15));
		courseGroup.add(courseAllBTN);
		
		JRadioButton courseSpecBTN = new JRadioButton("Specific Attributes");
		courseSpecBTN.setBounds(0, 34, 147, 27);
		course.add(courseSpecBTN);
		courseSpecBTN.setOpaque(false);
		courseSpecBTN.setForeground(Color.WHITE);
		courseSpecBTN.setFont(new Font("Tahoma", Font.PLAIN, 15));
		courseGroup.add(courseSpecBTN);
		
		JCheckBox codeCHK = new JCheckBox("Course Code");
		codeCHK.setBounds(6, 59, 165, 27);
		course.add(codeCHK);
		codeCHK.setOpaque(false);
		codeCHK.setForeground(Color.WHITE);
		codeCHK.setFont(new Font("Tahoma", Font.PLAIN, 15));
		codeCHK.setEnabled(false);
		
		JCheckBox coNameCHK = new JCheckBox("Course Name");
		coNameCHK.setBounds(7, 83, 145, 27);
		course.add(coNameCHK);
		coNameCHK.setOpaque(false);
		coNameCHK.setForeground(Color.WHITE);
		coNameCHK.setFont(new Font("Tahoma", Font.PLAIN, 15));
		coNameCHK.setEnabled(false);
		
		JCheckBox creditsCHK = new JCheckBox("Credits");
		creditsCHK.setBounds(7, 106, 149, 27);
		course.add(creditsCHK);
		creditsCHK.setOpaque(false);
		creditsCHK.setForeground(Color.WHITE);
		creditsCHK.setFont(new Font("Tahoma", Font.PLAIN, 15));
		creditsCHK.setEnabled(false);
		
		JCheckBox levelCHK = new JCheckBox("Level");
		levelCHK.setBounds(6, 126, 165, 30);
		course.add(levelCHK);
		levelCHK.setOpaque(false);
		levelCHK.setForeground(Color.WHITE);
		levelCHK.setFont(new Font("Tahoma", Font.PLAIN, 15));
		levelCHK.setEnabled(false);
		
		JCheckBox descCHK = new JCheckBox("Course Description");
		descCHK.setBounds(6, 151, 151, 27);
		course.add(descCHK);
		descCHK.setOpaque(false);
		descCHK.setForeground(Color.WHITE);
		descCHK.setFont(new Font("Tahoma", Font.PLAIN, 15));
		descCHK.setEnabled(false);
		
		JCheckBox courseDeptNameCHK = new JCheckBox("Department Name");
		courseDeptNameCHK.setBounds(7, 176, 149, 27);
		course.add(courseDeptNameCHK);
		courseDeptNameCHK.setOpaque(false);
		courseDeptNameCHK.setForeground(Color.WHITE);
		courseDeptNameCHK.setFont(new Font("Tahoma", Font.PLAIN, 15));
		courseDeptNameCHK.setEnabled(false);
		courseDeptNameCHK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(courseDeptNameCHK.isSelected())
				{
					courseAttributes.add("C.DeptDName");
				}
					
				if(!courseDeptNameCHK.isSelected())
				{
					courseAttributes.remove("C.DeptDName");
				}
				
				updateQuery();
				
				queryDisplay.setText(query);
				
			}
		});
		descCHK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(descCHK.isSelected())
				{
					courseAttributes.add("C.CDesc");
				}
					
				if(!descCHK.isSelected())
				{
					courseAttributes.remove("C.CDesc");
				}
				
				updateQuery();
				
				queryDisplay.setText(query);
				
			}
		});
		levelCHK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(levelCHK.isSelected())
				{
					courseAttributes.add("C.Level");
				}
					
				if(!levelCHK.isSelected())
				{
					courseAttributes.remove("C.Level");
				}
				
				updateQuery();
				
				queryDisplay.setText(query);
				
			}
		});
		creditsCHK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(creditsCHK.isSelected())
				{
					courseAttributes.add("C.Credits");
				}
					
				if(!creditsCHK.isSelected())
				{
					courseAttributes.remove("C.Credits");
				}
				
				updateQuery();
				
				queryDisplay.setText(query);
				
			}
		});
		coNameCHK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(coNameCHK.isSelected())
				{
					courseAttributes.add("C.CoName");
				}
					
				if(!coNameCHK.isSelected())
				{
					courseAttributes.remove("C.CoName");
				}
				
				updateQuery();
				
				queryDisplay.setText(query);
				
			}
		});
		codeCHK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(codeCHK.isSelected())
				{
					courseAttributes.add("C.CCode");
				}
					
				if(!codeCHK.isSelected())
				{
					courseAttributes.remove("C.CCode");
				}
				
				updateQuery();
				
				queryDisplay.setText(query);
				
			}
		});
		
		courseSpecBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				courseDeptNameCHK.setEnabled(true);
				descCHK.setEnabled(true);
				levelCHK.setEnabled(true);
				creditsCHK.setEnabled(true);
				coNameCHK.setEnabled(true);
				codeCHK.setEnabled(true);
				
				courseAttributes.clear();
				updateQuery();
//				if(tables[0].equals("")||tables[1].equals(""))
//					query = "SELECT ? FROM "+tables[0]+tables[1];
//				else
//					query = "SELECT ? FROM "+tables[0]+" , "+tables[1];
				
				queryDisplay.setText(query);
			}
		});
		
		courseAllBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				courseDeptNameCHK.setEnabled(false);
				descCHK.setEnabled(false);
				levelCHK.setEnabled(false);
				creditsCHK.setEnabled(false);
				coNameCHK.setEnabled(false);
				codeCHK.setEnabled(false);
				
				courseAttributes.clear();
				courseAttributes.add("C.*");
				updateQuery();
				
				queryDisplay.setText(query);
			}
		});
		
		JLabel lblNewLabel = new JLabel("Table");
		lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(35, 36, 129, 25);
		panel.add(lblNewLabel);
		
		JCheckBox deptCheck = new JCheckBox("Department Table");
		deptCheck.setForeground(Color.WHITE);
		deptCheck.setFont(new Font("Tahoma", Font.PLAIN, 15));
		deptCheck.setBounds(35, 68, 147, 27);
		deptCheck.setOpaque(false);
		
		JLabel cond_LBL = new JLabel("Condition(s)");
		cond_LBL.setFont(new Font("Tahoma", Font.ITALIC, 20));
		cond_LBL.setForeground(Color.WHITE);
		cond_LBL.setBounds(35, 400, 129, 27);
		panel.add(cond_LBL);
		panel.add(deptCheck);
		
		
		JCheckBox courseCheck = new JCheckBox("Course Table");
		courseCheck.setForeground(Color.WHITE);
		courseCheck.setFont(new Font("Tahoma", Font.PLAIN, 15));
		courseCheck.setBounds(212, 68, 117, 27);
		courseCheck.setOpaque(false);

		panel.add(courseCheck);
		
		ButtonGroup option = new ButtonGroup();
		
		JButton generateBTN = new JButton("Generate Cost Estimation");
		generateBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		generateBTN.setFont(new Font("Tahoma", Font.PLAIN, 15));
		generateBTN.setBounds(270, 506, 258, 23);
		generateBTN.setBackground(new Color(0xfedd76));
		panel.add(generateBTN);
		
		JPanel condition = new JPanel();
		condition.setBounds(35, 437, 739, 23);
		panel.add(condition);
		condition.setLayout(null);
		condition.setVisible(false);
		condition.setOpaque(false);
		
		cond2_State = new JTextField();
		cond2_State.setBounds(643, 1, 96, 20);
		condition.add(cond2_State);
		cond2_State.setColumns(10);
		
		cond1_State = new JTextField(" ");
		cond1_State.setBounds(234, 2, 96, 20);
		condition.add(cond1_State);
		cond1_State.setColumns(10);
		
		JComboBox cond1_Combo = new JComboBox();
		cond1_Combo.setBounds(0, 1, 147, 22);
		condition.add(cond1_Combo);
		cond1_Combo.addItem("None");
		
		JComboBox cond2_Combo = new JComboBox();
		cond2_Combo.setBounds(409, 0, 147, 22);
		condition.add(cond2_Combo);
		cond2_Combo.addItem("None");
		
		JComboBox cond1Eq = new JComboBox();
		cond1Eq.setBounds(153, 1, 71, 22);
		condition.add(cond1Eq);
		cond1Eq.addItem("=");
		cond1Eq.addItem(">");
		cond1Eq.addItem("<");
		cond1Eq.addItem(">=");
		cond1Eq.addItem("<=");
		
		JComboBox cond2Eq = new JComboBox();
		cond2Eq.setBounds(562, 0, 71, 22);
		condition.add(cond2Eq);
		cond2Eq.addItem("=");
		cond2Eq.addItem(">");
		cond2Eq.addItem("<");
		cond2Eq.addItem(">=");
		cond2Eq.addItem("<=");
		
		JComboBox conn_Box = new JComboBox();
		conn_Box.setBounds(340, 0, 59, 22);
		condition.add(conn_Box);
		conn_Box.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				join = conn_Box.getSelectedItem().toString();
				
				updateQuery();
				
				queryDisplay.setText(query);
			}
		});
		conn_Box.addItem("AND");
		conn_Box.addItem("OR");
		
		JLabel info_LBL = new JLabel("Condition(s) for this scenario is unavailable for Now >_<");
		info_LBL.setHorizontalAlignment(SwingConstants.CENTER);
		info_LBL.setBounds(35, 438, 739, 32);
		panel.add(info_LBL);
		info_LBL.setForeground(Color.WHITE);
		info_LBL.setFont(new Font("Tahoma", Font.BOLD, 17));
		conn_Box.setVisible(false);
		cond2Eq.setVisible(false);
		
		cond2Eq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(conditions.size()==2) {
					conditions.remove(1);
					
					String cond = ((ComboItem) cond2_Combo.getSelectedItem()).getValue();
					String eq = cond2Eq.getSelectedItem().toString();
					String value = cond2_State.getText();
					conditions.add(1,cond+" "+eq+" "+value); 
					
					updateQuery();
					
					queryDisplay.setText(query);
				}
			}
		});
		
		cond1Eq.setVisible(false);
		
		cond1Eq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!conditions.isEmpty()) {
					conditions.remove(0);
					
					String cond = ((ComboItem) cond1_Combo.getSelectedItem()).getValue();
					String eq = cond1Eq.getSelectedItem().toString();
					String value = cond1_State.getText();
					conditions.add(0,cond+" "+eq+" "+value); 
					
					updateQuery();
					
					queryDisplay.setText(query);
				}
			}
		});
		
		cond2_Combo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!cond2_Combo.getSelectedItem().toString().equals("None")) {
					conn_Box.setVisible(true);
					cond2Eq.setVisible(true);
					cond2_State.setVisible(true);
					
					if(conditions.size()==2) {
						conditions.remove(1);
						
						String cond = ((ComboItem) cond2_Combo.getSelectedItem()).getValue();
						String eq = cond2Eq.getSelectedItem().toString();
						String value = cond2_State.getText();
						conditions.add(1,cond+eq+value);
						
						updateQuery();
						
						queryDisplay.setText(query);
					}
				}
					
				else
				{
					conn_Box.setVisible(false);
					cond2Eq.setVisible(false);
					cond2_State.setVisible(false);
					
					if(conditions.size()==2) {
						conditions.remove(1);
						
						updateQuery();
						
						queryDisplay.setText(query);
					}
				}
			}
		});
		
		cond1_Combo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!cond1_Combo.getSelectedItem().toString().equals("None")) {
					cond1Eq.setVisible(true);
					cond1_State.setVisible(true);
					
					if(!conditions.isEmpty()) {
						conditions.remove(0);
						
						String cond = ((ComboItem) cond1_Combo.getSelectedItem()).getValue();
						String eq = cond1Eq.getSelectedItem().toString();
						String value = cond1_State.getText();
						conditions.add(0,cond+eq+value);
						
						updateQuery();
						
						queryDisplay.setText(query);
					}
				}
					
				else
				{
					cond1Eq.setVisible(false);
					cond1_State.setVisible(false);
					
					if(!conditions.isEmpty()) {
						conditions.remove(0);
						
						updateQuery();
						
						queryDisplay.setText(query);
					}
				}
			}
		});
		cond1_State.setVisible(false);
		
		cond1_State.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(!conditions.isEmpty())
					conditions.remove(0);
				
				String cond = ((ComboItem) cond1_Combo.getSelectedItem()).getValue();
				String eq = cond1Eq.getSelectedItem().toString();
				String value = cond1_State.getText();
				conditions.add(0,cond+" "+eq+" "+value); 
				
				updateQuery();
				
				queryDisplay.setText(query);
			}
		});
		cond2_State.setVisible(false);
		
		cond2_State.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(conditions.size()==2)
					conditions.remove(1);
				
				String cond = ((ComboItem) cond2_Combo.getSelectedItem()).getValue();
				String eq = cond2Eq.getSelectedItem().toString();
				String value = cond2_State.getText();
				conditions.add(1,cond+" "+eq+" "+value); 
				
				updateQuery();
				
				queryDisplay.setText(query);
			}
		});
		
		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon("images/backgroundResized.jpg"));
		background.setBounds(0, 0, 829, 631);
		panel.add(background);
		
		deptCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				department.setVisible(!department.isVisible());
				if(deptCheck.isSelected())
				{
					deptAttributes.clear();
					tables[0] = "dept D";
					deptAttributes.add("D.*");
					for(int i=0;i<dept.length;++i) {
						cond1_Combo.addItem(new ComboItem(dept[i],deptValue[i],deptType[i]));
						cond2_Combo.addItem(new ComboItem(dept[i],deptValue[i],deptType[i]));
					}
					if(deptCheck.isSelected() && courseCheck.isSelected()) {
						info_LBL.setVisible(true);
						condition.setVisible(false);
					}else if(!deptCheck.isSelected() && !courseCheck.isSelected()) {
						info_LBL.setVisible(true);
						condition.setVisible(false);
					}else {
						info_LBL.setVisible(false);
						condition.setVisible(true);
					}
				}
					
				if(!deptCheck.isSelected())
				{
					deptAttributes.clear();
					tables[0] = "";
					for(int i=0;i<dept.length;++i) {
						cond1_Combo.removeItem(new ComboItem(dept[i],deptValue[i],deptType[i]));
						cond2_Combo.removeItem(new ComboItem(dept[i],deptValue[i],deptType[i]));
					}
					if(deptCheck.isSelected() && courseCheck.isSelected()) {
						info_LBL.setVisible(true);
						condition.setVisible(false);
					}else if(!deptCheck.isSelected() && !courseCheck.isSelected()) {
						info_LBL.setVisible(true);
						condition.setVisible(false);
					}else {
						info_LBL.setVisible(false);
						condition.setVisible(true);
					}
				}
				
				updateQuery();
				
				queryDisplay.setText(query);
				
			}
		});
		
		courseCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(courseCheck.isSelected()) 
				{
					course.setVisible(true);
					courseAttributes.clear();
					tables[1] = "Course C";
					courseAttributes.add("C.*");
					for(int i=0;i<courseAt.length;++i) {
						cond1_Combo.addItem(new ComboItem(courseAt[i],courseValue[i],courseType[i]));
						cond2_Combo.addItem(new ComboItem(courseAt[i],courseValue[i],courseType[i]));
					}
					if(deptCheck.isSelected() && courseCheck.isSelected()) {
						info_LBL.setVisible(true);
						condition.setVisible(false);
					}else if(!deptCheck.isSelected() && !courseCheck.isSelected()) {
						info_LBL.setVisible(true);
						condition.setVisible(false);
					}else {
						info_LBL.setVisible(false);
						condition.setVisible(true);
					}
				}
				
				if(!courseCheck.isSelected())
				{
					course.setVisible(false);
					tables[1] = "";
					courseAttributes.clear();
					for(int i=0;i<courseAt.length;++i) {
						cond1_Combo.removeItem(new ComboItem(courseAt[i],courseValue[i],courseType[i]));
						cond2_Combo.removeItem(new ComboItem(courseAt[i],courseValue[i],courseType[i]));
					}
					if(deptCheck.isSelected() && courseCheck.isSelected()) {
						info_LBL.setVisible(true);
						condition.setVisible(false);
					}else if(!deptCheck.isSelected() && !courseCheck.isSelected()) {
						info_LBL.setVisible(true);
						condition.setVisible(false);
					}else {
						info_LBL.setVisible(false);
						condition.setVisible(true);
					}
				}
					
				updateQuery();
				
				queryDisplay.setText(query);
				
			}
		});
		
		generateBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!query.isBlank()) {
					String type1="";
					String type2="";
					String Or_And="no";
					if(conditions.size()==2) {
						 type1 =((ComboItem) cond1_Combo.getSelectedItem()).getType();
						 type2 =((ComboItem) cond2_Combo.getSelectedItem()).getType();
						 Or_And = join; 
					}else if(conditions.size()==1){
						 type1 =((ComboItem) cond1_Combo.getSelectedItem()).getType();
					}
					
			
					CostCalculator cost= new CostCalculator(query, tables, deptAttributes,
							courseAttributes, conditions,type1,type2,Or_And);
					
					costDisplay.setText(cost.calulcateCost());
				}else {
					queryDisplay.setText("Please select at least one table");
				}
			}
		});
	}
}
