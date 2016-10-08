/*Author: Zhechen DU
 Created: Oct 1, 2016
 This the the trade matching algorithm, where a trade request from user will be matched with trade request
 from database using h2*/
 
//USD ID: a
//CAD ID: b
//RMB ID: c
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
		double amountleft=buyerinfo.getamount();
		double buyeramount=amountleft;
		double buyerID= buyerinfo.getID();
		//swith to seller exchang rate
		double buyerrate=1/buyerinfo.getrate();
		double buyerextotamount=0;
		
		System.out.println("ID: " + buyerinfo.getID() + " rate: " + buyerinfo.getrate() + " amount: "
		+ buyerinfo.getamount() + " time: " + buyerinfo.gettime());
		
		int exratebig=0;
		traderinfo sellerinfo=new traderinfo(0,0,0,0);
		while ((amountleft>0)||(exratebig!=1))
		{
			sellerinfo=stackpop();
			double selleramount=sellerinfo.getamount();
			int sellererID= sellerinfo.getID();
			double sellerrate=sellerinfo.getrate();
			
			System.out.println("ID: " + sellerinfo.getID() + " rate: " + sellerinfo.getrate() + " amount: "
			+ sellerinfo.getamount() + " time: " + sellerinfo.gettime());
			
			
			//trade if seller exchange rate 
			if (sellerrate<buyerrate)
			{
				// when seller is selling more then buyer's request
				double sellerexamount=Math.round(selleramount*sellerrate*10000)/10000;
				if (sellerexamount>amountleft)
				{
					//sellerinfo.setamount(sellerexamount-amountleft);
					//stackpush(sellerinfo);
					buyerextotamount=Math.round(amountleft/sellerrate*10000)/10000+buyerextotamount;
					amountleft=0; //terminate while loop
					//update database
				}
				//if buyer request equal to seller request
				else if (sellerexamount==amountleft)
				{
					buyerextotamount=Math.round(amountleft/sellerrate*10000)/10000+buyerextotamount;
					//sellerinfo.setamount(0);
					amountleft=0;
					//update database
				}
				//if buyer request bigger than seller request
				else
				{
					//sellerinfo.setamount(0);
					amountleft=amountleft-sellerexamount;
					buyerextotamount=selleramount+buyerextotamount;
					//update database
				}
			}
			else
			{
				exratebig=1;
			}
			System.out.println(buyerextotamount);
		}
		System.out.println(buyerextotamount);
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
		traderinfo sellinfo = new traderinfo(0 ,0,0,0);
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