package phase2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.sql.*;

public class DBase {
	Connection conn;
	String user = "phase2";
	String pwd = "phase2";
	
	public DBase()
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project",user, pwd);
		} 
		catch (SQLException | ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void close()
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
	
	//for testing connection
	public String test()
	{
		String info ="";			

		try 
		{
			Statement stmt2 = conn.createStatement();
			ResultSet rs = stmt2.executeQuery("SELECT ColumnName FROM metadata WHERE ColumnName = \"DName\"");

			while(rs.next())
			{
				info= rs.getString(1);
			}
		}

		catch (SQLException e) 
		{
			e.printStackTrace();
		}

		return info;
	}
	
	public String[]tableStatistics(String tableName) {
		String[] stats = new String[4];
		
		try 
		{
			Statement stmt2 = conn.createStatement();
			ResultSet rs = stmt2.executeQuery("SELECT AvrgRowLength, TableSize, NumberOfRecords,NumberOfBlocks FROM table_metadata WHERE TableName = \""+tableName+"\";");

			while(rs.next())
			{
				for(int i=0;i<stats.length;++i) {
					stats[i] = rs.getString(i+1);
				}
			}
		}

		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return stats;
	}
	
	public ArrayList<ArrayList<String>> attributeStatistics(String tableName){
		ArrayList<ArrayList<String>> stats = new ArrayList<>();
		
		try 
		{
			Statement stmt2 = conn.createStatement();
			ResultSet rs = stmt2.executeQuery("SELECT AttributeName, DistinctValues, maxiValue, minValue FROM attribute_metadata WHERE TableName = \""+tableName+"\";");

			while(rs.next())
			{
				ArrayList<String> row = new ArrayList<>();

			    for (int i = 1; i <= rs.getMetaData().getColumnCount(); ++i) {
			        row.add(rs.getString(i));
			    }

			    stats.add(row);
			}
		}

		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return stats;
	}
	
	public ArrayList<ArrayList<String>> indexStatistics(String tableName){
		ArrayList<ArrayList<String>> stats = new ArrayList<>();
		
		try 
		{
			Statement stmt2 = conn.createStatement();
			ResultSet rs = stmt2.executeQuery("SELECT FieldName,IsUnique,HeightOfTree,DistinctValues FROM index_metadata WHERE TableName = \""+tableName+"\";");

			while(rs.next())
			{
				ArrayList<String> row = new ArrayList<>();

			    for (int i = 1; i <= rs.getMetaData().getColumnCount(); ++i) {
			        row.add(rs.getString(i));
			    }

			    stats.add(row);
			}
		}

		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return stats;
	}
}
