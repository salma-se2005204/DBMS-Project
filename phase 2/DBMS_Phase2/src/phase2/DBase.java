package phase2;

import java.sql.Connection;
import java.sql.DriverManager;
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
}
