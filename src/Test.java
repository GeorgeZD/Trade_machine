/*Author: Zhechen DU
 Created: Oct 1, 2016
 This class is used to test trade algorithm*/

import java.util.*;
//import h2db.*;w

public class Test {

	public static void main(String[] args) throws Exception {
        System.out.println("This is debug test");
        System.out.println("Do you want to initialize lists?");
        System.out.println("enter 1 to initialize");
        int i=0;
/*        Scanner scan = new Scanner(System.in);
        while (i != 1)
        {
        i=scan.nextInt();
        H2currenyPool.initialize();
        		}
        scan.close();*/
        
        H2currenyPool.initialize();

        //if i=1, initial
        if (i ==1)
        {
        	
        }
        
        //Testtrade.input();
        //Testtrade.stacktransfer();
        Testtrade.trade();
        traderinfo sellerinfo=seller_Stack.stackpop();
        while(sellerinfo.getID() !=0)
        {
        	System.out.println("seller info");
    		System.out.println("ID: " + sellerinfo.getID() + "CID: " + sellerinfo.getcid() +" rate: " + sellerinfo.getrate() + " amount: "
    				+ sellerinfo.getamount() + " time: " + sellerinfo.gettime());
    		sellerinfo=seller_Stack.stackpop();
        }
        
    }

}

class Testtrade {
	public static Stack<Integer> ac = new Stack<Integer>();
	public static void stacktransfer()
	{
		Trade.stacktransfer(ac);
	}
	public static void trade()
	{
		//trading 5000 RMB for USD 
		traderinfo buyinfo = new traderinfo(1,12,0.167,6000,7);
		Trade.match(buyinfo);
		
	}
	public static void input() {
		traderinfo sellerinfo = new traderinfo(1,12, 6, 1000, 7);

		// create stack USD to RMB
		
		// rate and amount should have accuracy of 4 decimal places.
		// multiple by four for calculation
		// push id, rate, amount, time on list
		//stackpush( sellerinfo);
		//sellerinfo.setall(2, 5, 3000, 3);
		//stackpush( sellerinfo);
//		sellerinfo = stackpop();
//		System.out.println("seller info");
//		System.out.println("ID: " + sellerinfo.getID() + " rate: " + sellerinfo.getrate() + " amount: "
//				+ sellerinfo.getamount() + " time: " + sellerinfo.gettime());
//
//		sellerinfo = stackpop();
//		System.out.println("seller info");
//		System.out.println("ID: " + sellerinfo.getID() + " rate: " + sellerinfo.getrate() + " amount: "
//				+ sellerinfo.getamount() + " time: " + sellerinfo.gettime());
		
	}

	public static void stackpush( traderinfo sellinfo) {
		int id = sellinfo.getID();
		int cid = sellinfo.getcid();
		int rate = (int) Math.round(sellinfo.getrate() * 10000);
		int amount = (int) Math.round(sellinfo.getamount() * 10000);
		int time = sellinfo.gettime();
		ac.push(new Integer(id));
		ac.push(new Integer(cid));
		ac.push(new Integer(rate));
		ac.push(new Integer(amount));
		ac.push(new Integer(time));
	}

	public static traderinfo stackpop() {
		// int rate1=int(Math.round(rate*10000));
		// traderinfo info= new traderinfo();
		traderinfo sellinfo = new traderinfo(0 ,0,0,0,0);
		if (ac.empty()==true)				
		{
			return sellinfo;
		}
		else
		{
			sellinfo.settime((Integer) ac.pop());
			double amount = (double) ((Integer) ac.pop() / 10000);
			sellinfo.setamount(amount);
			double rate = (double) ((Integer) ac.pop() / 10000);
			sellinfo.setrate(rate);
			sellinfo.setcid((Integer) ac.pop());
			sellinfo.setID((Integer) ac.pop());
	
			// System.out.println(rate);
			return sellinfo;
		}
	}
}