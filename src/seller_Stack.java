import java.util.Stack;

public class seller_Stack {

	public static Stack<Integer> ac = new Stack<Integer>();
	public static void stackpush( traderinfo sellinfo) {
		int id = sellinfo.getID();
		int rate = (int) Math.round(sellinfo.getrate() * 10000);
		int amount = (int) Math.round(sellinfo.getamount() * 10000);
		int time = sellinfo.gettime();
		int cid = sellinfo.getcid();
		ac.push(new Integer(id));
		ac.push(new Integer(cid));
		ac.push(new Integer(rate));
		ac.push(new Integer(amount));
		ac.push(new Integer(time));
	}

	public  traderinfo stackpop() {
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
			//add exception
			
	
			// System.out.println(rate);
			return sellinfo;
		}
	}
}
