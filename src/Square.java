
public class Square 
{
	public int alive;
	
	private int _pendingAlive;
	
	public Square(int alive)
	{
		this.alive = alive;
	}
	
	public boolean isAlive()
	{
		return this.alive == 1;
	}
	
	public void markDead()
	{
		this._pendingAlive = 0;
	}
	
	public void markAlive()
	{
		this._pendingAlive = 1;
	}
	
	public void applyChanges()
	{
		if(this._pendingAlive != this.alive)
			this.alive = this._pendingAlive;
	}
}
