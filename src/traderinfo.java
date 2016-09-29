class traderinfo{
	private int ID;  //user ID
	private double amount;  //seller selling amount
	private double rate;    //seller selling rate
	private int time;    //time stamp to track number of days in the selling list
	public traderinfo(int id1, double rate1, double amount1, int time1)
	{
		this.ID=id1;
		this.amount=amount1;
		this.rate=rate1;
		this.time=time1;
	}
	public int getID()
	{
		return this.ID;
	}
	public double getamount()
	{
		return this.amount;
	}
	public double getrate()
	{
		return this.rate;
	}
	public int gettime()
	{
		return this.time;
	}
	public void setID(int id)
	{
		this.ID=id;
	}
	public void setamount(double amount1)
	{
		this.amount=amount1;
	}
	public void setrate(double rate1)
	{
		this.rate=rate1;
	}
	public void settime(int time1)
	{
		this.time=time1;
	}
	public void setall(int id, double amount1, double rate1, int time1)
	{
		this.ID=id;
		this.amount=amount1;
		this.rate=rate1;
		this.time=time1;
				
	}
}