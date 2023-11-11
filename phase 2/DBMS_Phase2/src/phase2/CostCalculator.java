package phase2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.ArrayList;

public class CostCalculator {

	Connection conn;
	String user = "phase2";
	String pwd = "phase2";
	public static String costResult = "";
	public String query;
	public String[] tables;
	public ArrayList<String> deptAttributes;
	public ArrayList<String> courseAttributes;
	public ArrayList<String> conditions;
	public String isSorted = "";
	public String type1="";
	public String type2="";
	public String Or_And="";
	public CostCalculator(String query, String[] tables, ArrayList<String> deptAttributes,
			ArrayList<String> courseAttributes, ArrayList<String> conditions,String type1,String type2,String Or_And) {
		this.query = query;
		this.tables = tables;
		this.deptAttributes = deptAttributes;
		this.courseAttributes = courseAttributes;
		this.conditions = conditions;
		this.type1=type1;
		this.type2=type2;
		this.Or_And=Or_And;
	}

	public String calulcateCost() {
		startConnection();
		costResult="";
		costResult = costResult /*+ "\nCost to " + query*/;
		Double cost = 0.0;
		if (tables[0].contains("Course C")) {
			tables[0] = "Course";
		} else if (tables[0].contains("dept D")) {
			tables[0] = "dept";
		}
		if (tables[1].contains("Course C")) {
			tables[1] = "Course";
		} else if (tables[1].contains("dept D")) {
			tables[1] = "dept";
		}
		if (query.contains("C.DeptDName = D.DName")) { // Calculate JOIN
			double J1 = -1.0;
			double J2SI = -1.0;
			double J2PI = -1.0;
			double J3 = -1.0;
			double J4 = -1.0;
			String attributeLeft = "DeptDName";
			String attributeRight = "DName";
			J1 = this.J1(attributeLeft, attributeRight, tables[1], tables[0]);
			cost = J1;
			String indexType = this.findIndexType(attributeRight);
			costResult += "\nNested-loop join :" + J1;
			if (indexType.contains("SI")) {
				J2SI = this.J2SI(attributeLeft, attributeRight, tables[1], tables[0]);
				costResult += "\nSecondary index based join :" + J2SI;
			} else if (indexType.contains("PI")) {
				J2PI = this.J2PI(attributeLeft, attributeRight, tables[1], tables[0]);
				costResult += "\nPrimary index based join :" + J2PI;
			}
			if (this.checkIfSorted(attributeRight).contains("yes")
					&& this.checkIfSorted(attributeLeft).contains("yes")) { // both tables are sorted on join attributes
				J3 = this.J3(attributeLeft, attributeRight, tables[1], tables[0]);
				costResult += "\nSort Merge join :" + J3;
			}
			J4 = this.J4(attributeLeft, attributeRight, tables[1], tables[0]);
			costResult += "\nPartion hash join :" + J4;
			cost = J1;
			if (cost > J2SI && J2SI != -1.0)
				cost = J2SI;
			if (cost > J2PI && J2PI != -1.0)
				cost = J2PI;
			if (cost > J3 && J3 != -1.0)
				cost = J3;
			if (cost > J4 && J4 != -1.0)
				cost = J4;
		}else if(conditions.size()==0 && !query.contains("C.DeptDName = D.DName")){
			if(tables[1].equals("")) {
				 cost=(double) this.findBlocks(tables[0]);
			  costResult+="\nLinear search "+cost;
			 
			}
			else 
			{
				 cost=(double) this.findBlocks(tables[1]);
				 costResult+="\nLinear search "+cost;
			}
			
			
		}
		else if (!query.contains("C.DeptDName = D.DName")) {
			// cost to calculate select
			String[] parts = conditions.get(0).split(" "); // condition received in String format example: DName =
															// 'Mathematic'
			String[] parts2 = new String[2];
			if (conditions.size() == 2) {
				parts2 = conditions.get(1).split(" ");
				if (parts2[0].startsWith("D.")) {
					parts2[0] = parts2[0].replace("D.", "");
				}
				if (parts2[0].startsWith("C.")) {
					parts2[0] = parts2[0].replace("C.", "");
				}
			}
			if (parts[0].startsWith("D.")) {
				parts[0] = parts[0].replace("D.", "");
			}
			if (parts[0].startsWith("C.")) {
				parts[0] = parts[0].replace("C.", "");
			}

			if (conditions.size() == 1 && tables[0].contains("dept") && tables[1].contains("")) // only one condition
																								// for select on table
																								// dept
				cost = selectCost(query, tables[0], parts[0], parts[2], checkIndexType(parts[0]),
						(parts[1]),type1);
			if (conditions.size() == 1 && tables[0].contains("") && tables[1].contains("Course"))// only one condition
																									// for select on
																									// table course
				cost = selectCost(query, tables[1], parts[0], parts[2], checkIndexType(parts[0]),
						(parts[1]),type2);
			if (conditions.size() == 2 && tables[0].contains("dept") && tables[1].contains("")) { // two conditions for
																									// select on table
																									// dept
				cost = selectCost(query, tables[0], parts[0], parts[2], checkIndexType(parts[0]),
						(parts[1]),type1);
				double secondCost = selectCost(query, tables[0], parts2[0],parts2[2],
						checkIndexType(parts2[0]), parts2[1],type2);
				if (cost > secondCost && Or_And.contains("AND"))
					cost = secondCost;
				else if(Or_And.contains("OR")) 
					cost= cost+secondCost;
			}
			if (conditions.size() == 2 && tables[0].contains("") && tables[1].contains("Course")) { // two conditions
																									// for select on
																									// table course
				cost = selectCost(query, tables[1], parts[0], parts[2], checkIndexType(parts[0]),
						(parts[1]),type1);
				double secondCost = selectCost(query, tables[1], parts2[0], parts2[2],
						checkIndexType(parts2[0]), parts2[1],type2);
				if (cost > secondCost && Or_And.contains("AND"))
					cost = secondCost;
				else if(Or_And.equalsIgnoreCase("OR")) {
					cost= cost+secondCost;
				}
				
			}
		} else {

		}
		costResult="Minimum Cost is "+cost+costResult;
	
		return costResult;

	}
	public  void close()
	{
		try 
		{
			conn.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	public double selectCost(String query, String table, String attribute, String value, String keyType, String operator,String conditionType) {
		double cost = 0.0;
		double s1Cost = -1.0;
		double s2Cost = -1.0;
		double s3aCost = -1.0;
		double s4Cost = -1.0;
		double s5Cost = -1.0;
		double s6Cost = -1.0;
		// code for calculating cost of select
		if (keyType.equals("PI") && operator.equals("=")) {
			s1Cost = S1LinearSearch(table, "=");
			if (this.checkIfSorted(attribute) == "yes")
				s2Cost = S2BinarySearch(table, attribute, operator, value,conditionType);
			s3aCost = S3aPrimaryKeySingleRecord(attribute);
		
		} else if (keyType.equals("PI") && !operator.equals("=")) {
			s1Cost = S1LinearSearch(table, "+");
			if (this.checkIfSorted(attribute) == "yes") {
				s2Cost = S2BinarySearch(table, attribute, operator, value,conditionType);
				s4Cost = S4OrderingIndexMultipleRecords(table, attribute);
			}
		} else if (keyType.equals("CI") && operator.equals("=")) {
			s1Cost = S1LinearSearch(table, "=");
			if (this.checkIfSorted(attribute) == "yes") {
				s2Cost = S2BinarySearch(table, attribute, operator, value,conditionType);
				s5Cost = S5ClusteringIndex(table, attribute, operator, value,conditionType);
			}
		} else if (keyType.equals("CI") && !operator.equals("=")) {
			s1Cost = S1LinearSearch(table, "+");
			if (this.checkIfSorted(attribute) == "yes") {
				s2Cost = S2BinarySearch(table, attribute, operator, value,conditionType);
				s4Cost = S4OrderingIndexMultipleRecords(table, attribute);
			}
			s5Cost = S5ClusteringIndex(table, attribute, operator, value,conditionType);

		} else if (keyType.equals("SI") && operator.equals("=")) {
			s1Cost = S1LinearSearch(table, operator);
			if (this.checkIfSorted(attribute) == "yes")
				s2Cost = S2BinarySearch(table, attribute, operator, value,conditionType);
			s6Cost = S6SecondaryIndex(table, attribute, operator, value,conditionType);

		} else if (keyType.equals("SI") && !operator.equals("=")) {
			s1Cost = S1LinearSearch(table, operator);
			if (this.checkIfSorted(attribute) == "yes") {
				s2Cost = S2BinarySearch(table, attribute, operator, value,conditionType);
				s4Cost = S4OrderingIndexMultipleRecords(table, attribute);
			}
			s6Cost = S6SecondaryIndex(table, attribute, operator, value,conditionType);

		} else {
			s1Cost = S1LinearSearch(table, operator);
			if (this.checkIfSorted(attribute) == "yes")
				s2Cost = S2BinarySearch(table, attribute, operator, value,conditionType);
		}
		costResult = costResult + "\n\nCost to select attritbute " + attribute + "\nS1Linear search:" + s1Cost;
		if (s2Cost != -1)
			costResult = costResult.concat("\nS2Binary Search :" + s2Cost);
		if (s3aCost != -1)
			costResult = costResult.concat("\nS3aPrimaryKeySingleRecord Search :" + s3aCost);
		if (s4Cost != -1)
			costResult = costResult.concat("\nS4OrderingIndexMultipleRecords Search :" + s4Cost);
		if (s5Cost != -1)
			costResult = costResult.concat("\nS5ClusteringIndex Search :" + s5Cost);
		if (s6Cost != -1)
			costResult = costResult.concat("\nS6SecondaryIndex Search :" + s6Cost);
		cost = s1Cost;
		if (cost > s2Cost && s2Cost != -1.0)
			cost = s2Cost;
		if (cost > s3aCost && s3aCost != -1.0)
			cost = s3aCost;
		if (cost > s4Cost && s4Cost != -1.0)
			cost = s4Cost;
		if (cost > s5Cost && s5Cost != -1.0)
			cost = s5Cost;
		if (cost > s6Cost && s6Cost != -1.0)
			cost = s6Cost;
		return cost;
	}

	public void startConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "phase2", "phase2");
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public String checkIndexType(String attribute) {
		String info = "";
		try {
			Statement stmt2 = conn.createStatement();
			ResultSet rs = stmt2.executeQuery(
					"SELECT TypeOfIndex FROM attribute_metadata WHERE attributeName = '" + attribute + "';");

			while (rs.next()) {
				info = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return info;
	}

	public String checkIfSorted(String attribute) {
		String info = "";
		try {
			Statement stmt2 = conn.createStatement();
			ResultSet rs = stmt2
					.executeQuery("SELECT sorted FROM attribute_metadata WHERE attributeName = '" + attribute + "';");
			while (rs.next()) {
				info = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return info;
	}

	public double S1LinearSearch(String table, String operator) {
		int b = this.findBlocks(table);
		if (operator == "=")
			return b / 2.0;
		else
			return b;
	}

	public double S2BinarySearch(String table, String attribute, String operator, String condition, String conditionType) {
		int selectivity = this.findSelectivity(attribute, table, operator, condition,conditionType);
		int blockingFactor = this.findBlockingFactor(table);
		String isUnique = this.isUnique(attribute);
		int b = this.findBlocks(table);
		if (isUnique == "unique")
			return Math.log(b) / Math.log(2);
		else
			return (Math.log(b) / Math.log(2)) + Math.ceil(selectivity / blockingFactor) - 1;
	}

	public double S3aPrimaryKeySingleRecord(String attribute) {
		int heightOfTree = this.findHeightOfTree(attribute);
		return heightOfTree + 1;
	}

	public double S4OrderingIndexMultipleRecords(String table, String attribute) {
		String isSorted = this.checkIfSorted(attribute);
		Integer heightOfTree = this.findHeightOfTree(attribute);
		Integer blocks = this.findBlocks(table);
		return heightOfTree + (blocks / 2.0);
	}

	public double S5ClusteringIndex(String table, String attribute, String operator, String condition, String conditionType) {
		int heightOfTree = this.findHeightOfTree(attribute);
		int blockingFactor = this.findBlockingFactor(table);
		int selectivity = this.findSelectivity(attribute, table, operator, condition,conditionType);
		return heightOfTree + Math.ceil(selectivity / blockingFactor);
	}

	public double S6SecondaryIndex(String table, String attribute, String operator, String condition, String conditionType) {
		Integer selectivity = this.findSelectivity(attribute, table, operator, condition,conditionType);
		Integer records = this.findNoOfRecords(table);
		Integer firstLevelBlocks = -1;
		try {
			Statement stmt2 = conn.createStatement();
			ResultSet rs = stmt2
					.executeQuery("SELECT firstLevelIndex FROM index_metadata WHERE fieldName = '" + attribute + "';");

			while (rs.next()) {
				firstLevelBlocks = Integer.parseInt(rs.getString(1));
			}
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		Integer heightOfTree = this.findHeightOfTree(attribute);
		String isUnique = this.isUnique(attribute);
		if (table == "dept")
			table = "Dept";

		if (isUnique.contentEquals("unique") && operator.contains("=")) {
			return heightOfTree + 1;
		} else if (isUnique.contentEquals("no") && operator.contains("=")) {
			return heightOfTree + selectivity + 1;
		} else {
			return heightOfTree + (firstLevelBlocks / 2.0) + (records / 2.0);
		}
	}

	public String isUnique(String attribute) {
		String isUnique = "";
		try {
			Statement stmt2 = conn.createStatement();
			ResultSet rs = stmt2
					.executeQuery("SELECT isUnique FROM attribute_metadata WHERE attributeName = '" + attribute + "';");

			while (rs.next()) {
				isUnique = rs.getString(1);
			}
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		return isUnique;
	}

	public int findBlocks(String table) {
		if (table.contains("dept"))
			table = "Dept";
		int blocks = 0;
		try {
			Statement stmt2 = conn.createStatement();
			ResultSet rs = stmt2
					.executeQuery("SELECT numberOfBlocks FROM table_metadata WHERE tablename = '" + table + "';");

			while (rs.next()) {
				blocks = Integer.parseInt(rs.getString(1));
			}
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		return blocks;

	}

	public String findIndexType(String attribute) {
		String index = "";
		try {
			Statement stmt2 = conn.createStatement();
			ResultSet rs = stmt2.executeQuery(
					"SELECT typeOfIndex FROM attribute_metadata WHERE attributeName = '" + attribute + "';");

			while (rs.next()) {
				index = rs.getString(1);
			}
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		return index;

	}

	public int findNoOfRecords(String table) {
		int records = 0;
		try {
			Statement stmt2 = conn.createStatement();
			ResultSet rs = stmt2
					.executeQuery("SELECT numberOfRecords FROM table_metadata WHERE tableName = '" + table + "';");

			while (rs.next()) {
				records = Integer.parseInt(rs.getString(1));
			}
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		return records;
	}

	public int findBlockingFactor(String table) {
		int blockingFactor = 0;
		if (table.contentEquals("dept"))
			table = "Dept";
		try {
			Statement stmt2 = conn.createStatement();
			ResultSet rs = stmt2
					.executeQuery("SELECT blockingfactor FROM table_metadata WHERE tablename = '" + table + "';");

			while (rs.next()) {
				blockingFactor = Integer.parseInt(rs.getString(1));
			}
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		return blockingFactor;

	}

	public double findWriteCost(String leftTable, String rightTable, double joinSelectivity) {
		// assuming blocking factor for the resulting file is is 6
		int recordsLeft = this.findNoOfRecords(leftTable);
		int recordsRight = this.findNoOfRecords(rightTable);
		return (((joinSelectivity * recordsLeft * recordsRight) / 6.0));
	}
	public static boolean isNumeric(String str) {
		  ParsePosition pos = new ParsePosition(0);
		  NumberFormat.getInstance().parse(str, pos);
		  return str.length() == pos.getIndex();
		}
	public int findSelectivity(String attribute, String table, String operator, String condition,String conditionType) {
		int records = this.findNoOfRecords(table);
		int NDV = this.findNDV(attribute);
		int s = 0;
		if(conditionType.contains("int")) {
			int min = this.findMinOrMax("minValue", attribute);
			int max = this.findMinOrMax("maxValue", attribute);
			int value=Integer.parseInt(condition);
			if( value>max || value<min) {
				s=0;
			}else {
			if (operator.equals("=")) {
				s = (int) Math.round(records / (NDV));
			} else if (operator.equals("<") || operator.equals("<=")) {
				s = (int) Math.round((value - min) / (records / NDV));
			}
			if (operator.equals(">") || operator.equals(">=")) {
				s = (int) Math.ceil((max - value) / (records / NDV));
			}
			}
		}else{
			
			s=Math.round(records / (NDV));
		}
		return s;
	}

	public int findMinOrMax(String minMax, String attribute) {
		int m = 0;
		try {
			Statement stmt2 = conn.createStatement();
			ResultSet rs = stmt2.executeQuery(
					"SELECT " + minMax + " FROM attribute_metadata WHERE attributeName = '" + attribute + "';");

			while (rs.next()) {
				m = Integer.parseInt(rs.getString(1));
			}
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		return m;
	}

	public double findJoinSelectivity(String attribute1, String attribute2) {
		int ndv1 = this.findNDV(attribute1);
		int ndv2 = this.findNDV(attribute2);
		return (1.0 / Math.max(ndv1, ndv2));
	}

	public int findNDV(String attribute) {
		int NDV = 0;
		try {
			Statement stmt2 = conn.createStatement();
			ResultSet rs = stmt2
					.executeQuery("SELECT distinctValues FROM attribute_metadata WHERE attributeName = '" + attribute + "';");

			while (rs.next()) {
				NDV = Integer.parseInt(rs.getString(1));
			}
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		return NDV;
	}

	public int findHeightOfTree(String attribute) {
		int heightOfTree = -1;
		try {
			Statement stmt2 = conn.createStatement();
			ResultSet rs = stmt2
					.executeQuery("SELECT heightOfTree FROM index_metadata WHERE fieldName = '" + attribute + "';");

			while (rs.next()) {
				heightOfTree = Integer.parseInt(rs.getString(1));
			}
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		return heightOfTree;
	}

	public double J1(String attributeLeft, String attributeRight, String leftTable, String rightTable) {
		int bLeft = this.findBlocks(leftTable);
		int bRight = this.findBlocks(rightTable);
		int recordsLeft = this.findNoOfRecords(leftTable);
		int recordsRight = this.findNoOfRecords(leftTable);
		double joinSelectivity = this.findJoinSelectivity(attributeLeft, attributeRight);
		return bLeft + (bRight * bLeft) + this.findWriteCost(leftTable, rightTable, joinSelectivity);
	}

	public double J2SI(String attributeLeft, String attributeRight, String leftTable, String rightTable) {
		int bLeft = this.findBlocks(leftTable);
		int recordsLeft = this.findNoOfRecords(leftTable);
		int recordsRight = this.findNoOfRecords(rightTable);
		int heightSecondaryIndexRight = this.findHeightOfTree(attributeRight);
		int cardinalityRight = this.findNDV(attributeRight);
		double joinSelectivity = this.findJoinSelectivity(attributeLeft, attributeRight);
		return bLeft + (recordsLeft * (heightSecondaryIndexRight + 1 + 1.0 * recordsRight / cardinalityRight))
				+ this.findWriteCost(leftTable, rightTable, joinSelectivity);
	}

	// public double J2CLI(int bLeft, int recordsLeft,int
	// heightClusteringIndexRight,int cardinalityRight, int recordsRight,int
	// joinSelection, int bfRight , int bfBoth) {
	// return
	// bLeft+(recordsLeft*(heightClusteringIndexRight+(cardinalityRight/bfRight))+((joinSelection*recordsLeft*recordsRight)/bfBoth));
	// }
	public double J2PI(String attributeLeft, String attributeRight, String leftTable, String rightTable) {
		int bLeft = this.findBlocks(leftTable);
		int recordsLeft = this.findNoOfRecords(leftTable);
		int heightPrimaryIndexRight = this.findHeightOfTree(attributeRight);
		double joinSelectivity = this.findJoinSelectivity(attributeLeft, attributeRight);
		return bLeft + (recordsLeft * (heightPrimaryIndexRight + 1))
				+ this.findWriteCost(leftTable, rightTable, joinSelectivity);
	}

	public double J3(String attributeLeft, String attributeRight, String leftTable, String rightTable) {
		int bLeft = this.findBlocks(leftTable);
		int bRight = this.findBlocks(rightTable);
		double joinSelectivity = this.findJoinSelectivity(attributeLeft, attributeRight);
		return bLeft + bRight + this.findWriteCost(leftTable, rightTable, joinSelectivity);
	}

	public double J4(String attributeLeft, String attributeRight, String leftTable, String rightTable) {
		int bLeft = this.findBlocks(leftTable);
		int bRight = this.findBlocks(rightTable);
		double joinSelectivity = this.findJoinSelectivity(attributeLeft, attributeRight);
		return 3 * (bLeft + bRight) + this.findWriteCost(leftTable, rightTable, joinSelectivity);
	}

}
