import java.util.Scanner;

public class ATMMeachine {
public static void main(String[] args)
{
Scanner sc =new  Scanner(System.in);
Admin ad = new Admin();
System.out.println("--------------------------------------------");
System.out.println("Welcome You Are Using Harshit ATM Machine");
System.out.println("---------------------------------------------");
System.out.println("Enter your Acccount Number :");
int cid= sc.nextInt();
System.out.println("Enter Your Password: ");
String pass =sc.next();
String validate = ad.validateUser(cid, pass);
if(validate==null)
{
	System.out.println("Invalid Account No/Password");
	return;
}
else
{
	System.out.println("________________________________");
	System.out.println("Welcome "+validate);
}
System.out.println("--------------------------------");
System.out.println("Choose Any Option");
System.out.println("--------------------------------");
System.out.println("1.CheckBalance");
System.out.println("2.MoneyTransfer");
System.out.println("3.Cash Withdrawal");
System.out.println("4.Exit");
int option = sc.nextInt();
switch(option)
{
case 1:
{
	int amount = ad.checkBalance(cid);
	System.out.println("Your Balance: "+amount);
	break;
}
case 2:
{
	ad.sendMoney(cid);
	break;
}
case 3:
{
	ad.cashWithdrawel(cid);
	break;
}
default:
{
	System.out.println("Thanks For Using Our ATM");
	break;
}
}

{
}
sc.close();
}

}
	
