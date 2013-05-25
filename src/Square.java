
public class Square 
{
	public int alive;
	private int liveAroundMe;
	
//	public Square neighbor1;
//	public Square neighbor2;
//	public Square neighbor3;
//	public Square neighbor4;
//	public Square neighbor5;
//	public Square neighbor6;
//	public Square neighbor7;
//	public Square neighbor8;
	
	public Square(int alive)
	{
		this.alive = alive;
		this.liveAroundMe = 0;
	}
	
	public boolean isAlive()
	{
		return this.alive == 1;
	}
	
//	public boolean incrementLiveAroundMe()
//	{
//		this.liveAroundMe ++;
//		return this.liveAroundMe >= 3;
//	}
}
