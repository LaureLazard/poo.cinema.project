/**
 * 
 */
package project.cinema.poo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Laurent
 *
 */
public class DbConn {
	Connection con = null;
	
	public Connection getLocalConnection()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/movies", "root", "");
		}
		catch(ClassNotFoundException e)
		{
			System.err.println("ClassNotFoundException in getConnection, " + e.getMessage());
		}
		catch(SQLException e)
		{
			System.err.println("SQLException in getConnection, " + e.getMessage());
		}
		return con;
	}
	public void setConnectionClose() throws SQLException
	{
		con.close();
	}

}
