import java.util.*;

public class Test{

    public static void main(String[] args) {
        System.out.println("This is debug test");
        System.out.println("Do you want to initialize lists?");
        System.out.println("enter 1 to initialize");
        Scanner scan = new Scanner(System.in);
        int i=0;
        while (i != 1)
        		{
        i=scan.nextInt();
        		}
        scan.close();
        		
        //if i=1, initial
        if (i ==1)
        		{
        	
        		}
        
        Teststack.input();
        
    }
    
}
class Teststack 
{

	public static void input()
	{
    sellertradeamount sellerinfo=new sellertradeamount(1,6.12345,1000, 7);
    
    //create stack USD to RMB
    Stack<Integer> ac= new Stack<Integer>();
    //rate and amount should have accuracy of 4 decimal places. 
    //multiple by four for calculation
    //push id, rate, amount, time on list
    stackpush(ac,sellerinfo);
    sellerinfo.setall(2,5,3000,3);
    stackpush(ac,sellerinfo);
    sellerinfo=stackpop(ac,sellerinfo);
    System.out.println("seller info");
    System.out.println("ID: "+sellerinfo.getID()+" rate: "+sellerinfo.getrate()+
    " amount: "+sellerinfo.getamount() +" time: "+sellerinfo.gettime());

    sellerinfo=stackpop(ac,sellerinfo);
    System.out.println("seller info");
    System.out.println("ID: "+sellerinfo.getID()+" rate: "+sellerinfo.getrate()+
    " amount: "+sellerinfo.getamount() +" time: "+sellerinfo.gettime());
	}
    public static void stackpush(Stack<Integer> ac, sellertradeamount sellinfo)
    {
    	int id=sellinfo.getID();
    	int rate=(int)Math.round(sellinfo.getrate()*10000);
    	int amount=(int)Math.round(sellinfo.getamount()*10000);
    	int time=sellinfo.gettime();
    	ac.push(new Integer(id));
    	ac.push(new Integer(rate));
    	ac.push(new Integer(amount));
    	ac.push(new Integer(time));
    	
    	
    }
    public static sellertradeamount stackpop(Stack<Integer> ac, sellertradeamount sellinfo)
    {
    	//int rate1=int(Math.round(rate*10000));
    	//sellertradeamount info= new sellertradeamount();
    	sellinfo.settime((Integer)ac.pop());
    	double amount=(double)((Integer)ac.pop()/10000);
    	sellinfo.setamount(amount);
    	double rate=(double)((Integer)ac.pop()/10000);
    	sellinfo.setrate(rate);
    	sellinfo.setID((Integer)ac.pop());

    	//System.out.println(rate);
    	return sellinfo;
    	
    	
    }
}

