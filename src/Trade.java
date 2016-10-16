/*Author: Zhechen DU
 Created: Oct 1, 2016
 This the the trade matching algorithm, where a trade request from user will be matched with trade request
 from database using h2*/
 
//USD ID: 1
//CAD ID: 2
//RMB ID: 3
import java.sql.SQLException;
import java.util.*;

public class Trade {
	public static Stack<Integer> ac = new Stack<Integer>();
	public static void stacktransfer(Stack<Integer> strans)
	{
		//check stack
		ac=strans;
		
	}
	public static void match (traderinfo buyerinfo)
	{
		//get buyerinfo
		int currencyid=buyerinfo.getcid();
		double buyerID= buyerinfo.getID();
		double amountleft=buyerinfo.getamount()*buyerinfo.getrate();
		
		double buyeramount=amountleft;
		//swith to seller exchang rate
		double buyerrate=1/buyerinfo.getrate();
		double buyerextotamount=0;		
		System.out.println("ID: " + buyerinfo.getID() + " rate: " + buyerinfo.getrate() + " amount: "
		+ amountleft + " time: " + buyerinfo.gettime());
		
		int exratebig=0;
		traderinfo sellerinfo=new traderinfo(0,0,0,0,0);
		while (amountleft>0)
		{
			try {
				sellerinfo=H2currenyPool.poolQuary(currencyid, buyeramount);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("poolQuary not found");
			}
			double selleramount=sellerinfo.getamount();
			//int sellererID= sellerinfo.getID();
			//double sellerrate=sellerinfo.getrate();
			System.out.println("ID: " + sellerinfo.getID() + " C_ID: " + sellerinfo.getcid() 
			+ " rate: " + sellerinfo.getrate() + " amount: "
			+ selleramount + " time: " + sellerinfo.gettime());
			System.out.println("amountleft:"+amountleft);

			if (selleramount==0)
			{
				break;
			}
			else if (Math.round(amountleft*10000)==Math.round(selleramount*10000))
			{
				amountleft=0;
				stackpush(sellerinfo);
				
			}
			else if (amountleft>selleramount)
			{
				amountleft=amountleft-selleramount;
				stackpush(sellerinfo);
			}
			else
			{
				System.out.println("match error");
				break;
			}


		}
		
		//output the updated table
		try {
			H2currenyPool.readtable();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(buyerextotamount);
		//small seller exchange=good 
	}
	public static void stackpush( traderinfo sellinfo) {
		int id = sellinfo.getID();
		int rate = (int) Math.round(sellinfo.getrate() * 10000);
		int amount = (int) Math.round(sellinfo.getamount() * 10000);
		int time = sellinfo.gettime();
		ac.push(new Integer(id));
		ac.push(new Integer(rate));
		ac.push(new Integer(amount));
		ac.push(new Integer(time));
	}

	public static traderinfo stackpop() {
		// int rate1=int(Math.round(rate*10000));
		// traderinfo info= new traderinfo();
		traderinfo sellinfo = new traderinfo(0 ,0,0,0,0);
		sellinfo.settime((Integer) ac.pop());
		double amount = (double) ((Integer) ac.pop() / 10000);
		sellinfo.setamount(amount);
		double rate = (double) ((Integer) ac.pop() / 10000);
		sellinfo.setrate(rate);
		sellinfo.setID((Integer) ac.pop());
		//add exception
		

		// System.out.println(rate);
		return sellinfo;

	}
}