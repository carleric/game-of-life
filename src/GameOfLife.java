import java.io.BufferedReader;
import java.io.InputStreamReader;

public class GameOfLife 
{
	private int iterations;
	private int width;
	private int height;
	private Square squareGrid[][];
	
	public GameOfLife(int iterations, int width, int height, Square squareGrid[][])
	{
		this.iterations = iterations;
		this.width = width;
		this.height = height;
		this.squareGrid = squareGrid;
	}
	
	public void run()
	{
		for(int x = 0; x < this.iterations; x++)
		{
			for(int i = 0; i < this.width; i++)
			{
				for(int j = 0; j < this.height; j++)
				{
					Square s = this.squareGrid[i][j];
					int liveAroundMe = this.getCountOfLiveNeighbors(i, j);
					
					//is dead
					if(!s.isAlive())
					{
						//rule 1: are there 3 live squares around me? if yes, go live
						if(liveAroundMe == 3)
						{
							//s.alive = 1;
							s.markAlive();
						}else{
							//do nothing, already dead
						}
					}
					else//is alive
					{
						//rule #2: A live square "dies" if it has less than two live neighbors, or more than three live neighbors
						if(liveAroundMe < 2 || liveAroundMe > 3)
						{
							//s.alive = 0;
							s.markDead();
						}
					}
				}
			}
			
			//apply changes only after iterating through matrix
			this.applyChanges();
			
			//uncomment here to view intermediate states
			//this.writeOutState("State at iteration:" + x);
		}
		
		//for testing 
		//this.writeOutState("Final State:");
		this.writeOutState(null);
	}
	
	private void applyChanges()
	{
		for(int i=0; i<this.width; i++)
		{
			for(int j=0; j<this.height; j++)
			{
				this.squareGrid[i][j].applyChanges();
			}
		}
	}
	
	private void writeOutState(String message)
	{
		if(message != null)
			System.out.println(message);
		
		//write to stdout
		for(int i=0; i<this.width; i++)
		{
			for(int j=0; j<this.height; j++)
			{
				System.out.print(this.squareGrid[i][j].alive);
				System.out.print(' ');
			}
			System.out.println();
		}
		System.out.println();
	}
	
	private int getCountOfLiveNeighbors(int i, int j)
	{
		//count of live
		int liveAroundMe = 0;
		
		//left/west (-1, 0)
		if(i > 0){
			liveAroundMe += this.squareGrid[i-1][j].alive;
		}else{
			liveAroundMe += this.squareGrid[this.width-1][j].alive;
		}
		
		//left-up / northwest (-1, -1)
		if(i > 0 && j > 0){
			liveAroundMe += this.squareGrid[i-1][j-1].alive;
		}else if(i > 0 && j == 0){
			liveAroundMe += this.squareGrid[i-1][this.height -1].alive;
		}else if(i == 0 && j > 0){
			liveAroundMe += this.squareGrid[this.width - 1][j-1].alive;
		}else if(i == 0 && j == 0){
			liveAroundMe += this.squareGrid[this.width - 1][this.height - 1].alive;
		}
		
		//up / north (0, -1)
		if(j == 0){
			liveAroundMe += this.squareGrid[i][this.height-1].alive;
		}else{
			liveAroundMe += this.squareGrid[i][j -1].alive;
		}
		
		//right-up / northeast (+1, -1)
		if(i == (this.width - 1) && j > 0){
			liveAroundMe += this.squareGrid[0][j-1].alive;
		}else if(i == (this.width - 1) && j == 0){
			liveAroundMe += this.squareGrid[0][this.height -1].alive;
		}else if(i < (this.width - 1) && j == 0){
			liveAroundMe += this.squareGrid[i+1][this.height -1].alive;
		}else if(i < (this.width-1) && j > 0){
			liveAroundMe += this.squareGrid[i+1][j-1].alive;
		}
		
		//right (+1, 0)
		if(i == (this.width-1)){
			liveAroundMe += this.squareGrid[0][j].alive;
		}else{
			liveAroundMe += this.squareGrid[i+1][j].alive;
		}
		
		//right-down / southeast (+1, +1)
		if(i == (this.width - 1) && j == (this.height-1)){
			liveAroundMe += this.squareGrid[0][0].alive;
		}else if(i == (this.width - 1) && j < (this.height-1)){
			liveAroundMe += this.squareGrid[0][j+1].alive;
		}else if(i < (this.width -1) && j == (this.height-1)){
			liveAroundMe += this.squareGrid[i+1][0].alive;
		}else {
			liveAroundMe += this.squareGrid[i+1][j+1].alive;
		}
		
		//down / south (0, 1)
		if(j == (this.height-1)){
			liveAroundMe += this.squareGrid[i][0].alive;
		}else{
			liveAroundMe += this.squareGrid[i][j+1].alive;
		}
		
		//down-left / southwest (-1, +1)
		if(i == 0 && j == (this.height-1)){
			liveAroundMe += this.squareGrid[this.width-1][0].alive;
		}else if(i > 0 && j == (this.height-1)){
			liveAroundMe += this.squareGrid[i-1][0].alive;
		}else if(i == 0 && j < (this.height-1)){
			liveAroundMe += this.squareGrid[this.width-1][j+1].alive;
		}else {
			liveAroundMe += this.squareGrid[i-1][j+1].alive;
		}
		
		return liveAroundMe;
	}
	
	public static void main(String[] args) 
	{ 
		//		n
		//		w h
		//		<line>
		//		<line>
		//		<EOF>
		
		int iterations = 0, width = 0, height = 0;
		//System.out.print("Enter Game of Life configuration: \n");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		try 
		{
			iterations = Integer.parseInt(br.readLine());
		} catch (Exception ioe) {
		     System.out.println("error trying to read number of iterations.");
		     System.exit(1);
		}
		
		try 
		{
			String [] widthHeight = br.readLine().split(" ");
			if(widthHeight.length != 2)
				throw new Exception("expecting two values in second line representing width and height separated by a space");
			
			width = Integer.parseInt(widthHeight[0]);
			height = Integer.parseInt(widthHeight[1]);
			
		} catch (Exception ioe) {
		     System.out.println("error trying to read number of iterations.");
		     System.exit(1);
		}
		
		//ArrayList<Square> squares = new ArrayList<Square>();
		Square squareGrid[][] = new Square[width][height];
		for(int i = 0; i < height; i++)
		{

			String[] squaresStrings = null;
			try {
				squaresStrings = br.readLine().split(" ");
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(squaresStrings != null)
			{
				for(int j = 0; j < width; j++)
				{
					Square square = new Square(Integer.parseInt(squaresStrings[j]));
					squareGrid[i][j] = square;
				}
			}
		}
		
		GameOfLife game = new GameOfLife(iterations, width, height, squareGrid);
		game.run();
	} 
}
