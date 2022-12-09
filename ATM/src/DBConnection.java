import java.sql.Connection;
import java.sql.SQLException;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;

public class DBConnection {
	
public static Connection getDbaConection()
{
	Connection con = null;
	try {
	MysqlConnectionPoolDataSource ds = new MysqlConnectionPoolDataSource();
	 	ds.setUrl("jdbc:mysql://localhost:3306/demo");
	 	ds.setUser("root");
	 	ds.setPassword("Honey@123");
	 	con = ds.getConnection();
	}catch(SQLException e)
	{
		System.out.println(e);
	}
	return con;
}
}
