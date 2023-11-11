package phase2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import phase2.CostScreen.ComboItem;

import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.CardLayout;

public class StatisticsScreen extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StatisticsScreen frame = new StatisticsScreen();
//					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//for combo box
	public class ComboItem {
	    private String label;
	    private String value;

	    public ComboItem(String label, String value) {
	        this.label = label;
	        this.value = value;
	    }

	    @Override
	    public String toString() {
	        return label;
	    }

	    public String getValue() {
	        return value;
	    }
	}
	
	String[]tableStats = new String[4];
	String[] attributeColumns = {"Name", "NDV", "Max Value","Min Value"};
	String[] indexColumns = {"Attribute Name","Uniqueness","Height of Tree","Distinct Values"};
	ArrayList<ArrayList<String>> attributeStats = new ArrayList<>();	
	ArrayList<ArrayList<String>> indexStats = new ArrayList<>();
	private JTable table;

	/**
	 * Create the frame.
	 */
	public StatisticsScreen() {
		JFrame panel = new JFrame();
		DBase db = new DBase();
		
		JTextField rowLen_TXT;
		JTextField tblSize_TXT;
		JTextField records_TXT;
		JTextField blk_TXT;
		
		panel.setIconImage(Toolkit.getDefaultToolkit().getImage("images/icon.jpg"));
		panel.setBounds(100, 100, 830, 651);
		panel.setTitle("Statistics Screen");
		panel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton homeBTN = new JButton("Back to Home Screen");
		homeBTN.setFont(new Font("Tahoma", Font.PLAIN, 15));
		homeBTN.setBounds(285, 519, 227, 27);
		homeBTN.setBackground(new Color(0xfedd76));
		homeBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainScreen x = new MainScreen();
				x.setVisible(true);
				setVisible(false);
				x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				panel.setVisible(false);
			}
		});
		
		panel.getContentPane().add(homeBTN);
		
		JLabel title = new JLabel("View Statistics");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setForeground(Color.WHITE);
		title.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		title.setBounds(0, 0, 816, 39);
		panel.getContentPane().add(title);
		
		JLabel tableName_LBL = new JLabel("Table Name");
		tableName_LBL.setForeground(Color.WHITE);
		tableName_LBL.setFont(new Font("Tahoma", Font.BOLD, 15));
		tableName_LBL.setBounds(20, 34, 121, 27);
		panel.getContentPane().add(tableName_LBL);
		
		JComboBox tblName_Combo = new JComboBox();
		tblName_Combo.setBounds(125, 38, 100, 22);
		tblName_Combo.addItem("Table Name");
		tblName_Combo.addItem(new ComboItem("Department","dept"));
		tblName_Combo.addItem(new ComboItem("Course","Course"));
		panel.getContentPane().add(tblName_Combo);
		
		JLabel tbl_LBL = new JLabel("Table Statistics");
		tbl_LBL.setForeground(Color.WHITE);
		tbl_LBL.setFont(new Font("Tahoma", Font.ITALIC, 15));
		tbl_LBL.setBounds(20, 72, 131, 23);
		panel.getContentPane().add(tbl_LBL);
		
		JLabel rowLen_LBL = new JLabel("Average Row Length");
		rowLen_LBL.setForeground(Color.WHITE);
		rowLen_LBL.setFont(new Font("Tahoma", Font.BOLD, 13));
		rowLen_LBL.setBounds(28, 99, 146, 22);
		panel.getContentPane().add(rowLen_LBL);
		
		rowLen_TXT = new JTextField();
		rowLen_TXT.setBounds(172, 101, 96, 20);
		panel.getContentPane().add(rowLen_TXT);
		rowLen_TXT.setColumns(10);
		rowLen_TXT.setEditable(false);
		
		JLabel tblSize_LBL = new JLabel("Table Size");
		tblSize_LBL.setForeground(Color.WHITE);
		tblSize_LBL.setFont(new Font("Tahoma", Font.BOLD, 13));
		tblSize_LBL.setBounds(28, 132, 86, 22);
		panel.getContentPane().add(tblSize_LBL);
		
		tblSize_TXT = new JTextField();
		tblSize_TXT.setEditable(false);
		tblSize_TXT.setColumns(10);
		tblSize_TXT.setBounds(172, 132, 96, 20);
		panel.getContentPane().add(tblSize_TXT);
		
		JLabel records_LBL = new JLabel("Number of Records");
		records_LBL.setForeground(Color.WHITE);
		records_LBL.setFont(new Font("Tahoma", Font.BOLD, 13));
		records_LBL.setBounds(366, 99, 130, 22);
		panel.getContentPane().add(records_LBL);
		
		records_TXT = new JTextField();
		records_TXT.setEditable(false);
		records_TXT.setColumns(10);
		records_TXT.setBounds(506, 101, 96, 20);
		panel.getContentPane().add(records_TXT);
		
		blk_TXT = new JTextField();
		blk_TXT.setEditable(false);
		blk_TXT.setColumns(10);
		blk_TXT.setBounds(506, 134, 96, 20);
		panel.getContentPane().add(blk_TXT);
		
		JLabel blk_LBL = new JLabel("Number of Blocks");
		blk_LBL.setForeground(Color.WHITE);
		blk_LBL.setFont(new Font("Tahoma", Font.BOLD, 13));
		blk_LBL.setBounds(366, 132, 130, 22);
		panel.getContentPane().add(blk_LBL);
		
		JLabel att_LBL = new JLabel("Attribute Statistics");
		att_LBL.setForeground(Color.WHITE);
		att_LBL.setFont(new Font("Tahoma", Font.ITALIC, 15));
		att_LBL.setBounds(27, 175, 131, 23);
		panel.getContentPane().add(att_LBL);
		
		DefaultTableModel model = new DefaultTableModel(attributeColumns, 0);
		DefaultTableModel model2 = new DefaultTableModel(indexColumns, 0);
		
		JLabel index_LBL = new JLabel("Index Statistics");
		index_LBL.setForeground(Color.WHITE);
		index_LBL.setFont(new Font("Tahoma", Font.ITALIC, 15));
		index_LBL.setBounds(27, 350, 131, 23);
		panel.getContentPane().add(index_LBL);

		JTable table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(100, 200, 600, 140);
		panel.getContentPane().add(scrollPane);
		scrollPane.setVisible(true);
		panel.getContentPane().add(homeBTN);
		
		JTable indexTable = new JTable(model2);
		JScrollPane scrollPane2 = new JScrollPane(indexTable);
		scrollPane2.setBounds(100, 380, 600, 65);
		panel.getContentPane().add(scrollPane2);
		scrollPane2.setVisible(true);
		panel.getContentPane().add(homeBTN);
		
		JLabel background = new JLabel("");
		background.setFont(new Font("Tahoma", Font.BOLD, 12));
		background.setIcon(new ImageIcon("images/backgroundResized.jpg"));
		background.setBounds(0, 0, 829, 631);
		panel.getContentPane().add(background);
		
		tblName_Combo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!tblName_Combo.getSelectedItem().equals("Table Name")) {
					model.setRowCount(0);
					model2.setRowCount(0);
					tableStats = db.tableStatistics(((ComboItem) tblName_Combo.getSelectedItem()).getValue());
					attributeStats = db.attributeStatistics(((ComboItem) tblName_Combo.getSelectedItem()).getValue());
					indexStats = db.indexStatistics(((ComboItem) tblName_Combo.getSelectedItem()).getValue());
					rowLen_TXT.setText(tableStats[0]);
					tblSize_TXT.setText(tableStats[1]);
					records_TXT.setText(tableStats[2]);
					blk_TXT.setText(tableStats[3]);
					
					for (ArrayList<String> row : attributeStats) {
						model.addRow(row.toArray());
					}
					
					for (ArrayList<String> row : indexStats) {
						model2.addRow(row.toArray());
					}
					
				}else {
					model.setRowCount(0);
					model2.setRowCount(0);
					rowLen_TXT.setText("");
					tblSize_TXT.setText("");
					records_TXT.setText("");
					blk_TXT.setText("");
				}
			}
		});
		
		panel.setVisible(true);
		
	}
}
