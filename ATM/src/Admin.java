import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Admin {
	DBConnection db = new DBConnection();
	Connection con = db.getDbaConection();
	PreparedStatement pstm= null;
public int checkBalance(int id) 
{
	int balance=0;
	if(con==null)
	{
		System.out.println("Connection Error!");
	}
	else 
	{
		try {
		String query ="select amount from customer where cid=?";
		pstm = con.prepareStatement(query);
		pstm.setInt(1, id);
		ResultSet rs = pstm.executeQuery();
		if(rs.next())
		{
			if(rs!=null)
			{
				balance = rs.getInt(1);
			}
		}
		}catch(SQLException e) {
			System.out.println(e);
		}
	}	
	return balance;
}
public void sendMoney(int cid)
{
	Scanner sc = new Scanner(System.in);
	if(con==null)
	{
		System.out.println("Connection Error!");
	}
	else
	{
		System.out.println("Enter Reciver id : ");
		int id = sc.nextInt();
		System.out.println("Enter Amount to Tranfer ");
		int amount =sc.nextInt();
		if(checkBalance(id)<amount)
		{
			System.out.println("You don't have sufficient Balance to Transfer!");
		}
		else
		{
			try {
				con.setAutoCommit(false);
		String query ="update customer set amount=amount-? where cid =?"; 
		pstm = con.prepareStatement(query);
		pstm.setInt(1, amount);
		pstm.setInt(2, cid);
		pstm.executeUpdate();
		String query1 ="update customer set amount=amount+? where cid=?";
		pstm = con.prepareStatement(query1);
		pstm.setInt(1, amount);
		pstm.setInt(2, id);
		int count = pstm.executeUpdate();
		System.out.println("Are you Sure ? YES/NO");
		String value = sc.next();
		if(value.compareToIgnoreCase("yes")==0)
		{
			con.commit();
			System.out.println("Money Transfer Successfull!");
		}
		else
		{
			con.rollback();
			System.out.println("Transaction Cancel!");
		}
		if(count == 1)
		{
			
		}
		
			}catch(SQLException e)
			{
				System.out.println(e);
			}
		}
			
	}
}
   public String validateUser(int id,String pass)
   {
	   String name = null;
	   if(con==null)
	   {
		   System.out.println("Connection Error!");
	   }
	   else
	   {
		   try {
	     String query ="select cname from customer where cid=? and password=?";
	     pstm = con.prepareStatement(query);
	     pstm.setInt(1,id);
	     pstm.setString(2, pass);
	     ResultSet rs = pstm.executeQuery();
	     if(rs.next())
	     {
	    	 if(rs!=null)
	    	 {
	    		 name = rs.getString(1);
	    	 }
	     }
		   }catch(SQLException e)
		   {
		   System.out.println(e);
		   }
		   
	   }
	 
	   return name;
   }
   public void cashWithdrawel(int id)
   { 
	   Scanner sc =new Scanner(System.in);
	   System.out.println("Enter Amount To Withdrawal: ");
	   int amount = sc.nextInt();
	   int balance = checkBalance(id);
	   if(balance>=amount)
	   {
	String query ="update customer set amount=amount-? where cid=?";
	try {
		pstm = con.prepareStatement(query);
		pstm.setInt(1, amount);
		pstm.setInt(2, id);
		int val = pstm.executeUpdate();
		if(val==1)
		{
			System.out.println("Collect Your Cash!!!");
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	   }
	   else
	   {
		   System.out.println("Low Balance!!!");
	   }
   }
   
}
