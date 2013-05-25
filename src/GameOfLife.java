import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class GameOfLife 
{
	private int iterations;
	private int width;
	private int height;
	//private ArrayList <Square>squares;
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
					
					//is dead
					if(!s.isAlive())
					{
						//rule 1: are there 3 live squares around me? if yes, go live
						
						//count of live
						int liveAroundMe = 0;
						
						//left/west
						if(i > 0){
							liveAroundMe += this.squareGrid[i-1][j].alive;
						}else{
							liveAroundMe += this.squareGrid[this.width-1][j].alive;
						}
						
						//left-up / northwest
						if(i > 0 && j > 0){
							liveAroundMe += this.squareGrid[i-1][j-1].alive;
						}else if(i > 0 && j == 0){
							liveAroundMe += this.squareGrid[i-1][this.height -1].alive;
						}else if(i == 0 && j > 0){
							liveAroundMe += this.squareGrid[this.width - 1][j-1].alive;
						}else if(i == 0 && j == 0){
							liveAroundMe += this.squareGrid[this.width - 1][this.height - 1].alive;
						}
						
						//up / north
						if(j == 0){
							liveAroundMe += this.squareGrid[i][this.height-1].alive;
						}else{
							liveAroundMe += this.squareGrid[i][j -1].alive;
						}
						
						//right-up / northeast
						if(i == (this.width - 1) && j > 0){
							liveAroundMe += this.squareGrid[0][j-1].alive;
						}else if(i == (this.width - 1) && j == 0){
							liveAroundMe += this.squareGrid[0][this.height -1].alive;
						}else if(i < (this.width-1) && j > 0){
							liveAroundMe += this.squareGrid[i+1][j-1].alive;
						}
						
						//right
						if(i == (this.width-1)){
							liveAroundMe += this.squareGrid[0][j].alive;
						}else{
							liveAroundMe += this.squareGrid[i+1][j].alive;
						}
						
						//right-down / southeast
//						if(i == (this.width - 1) && j > 0){
//							liveAroundMe += this.squareGrid[0][j-1].alive;
//						}else if(i == (this.width - 1) && j == 0){
//							liveAroundMe += this.squareGrid[0][this.height -1].alive;
//						}else if(i < (this.width-1) && j > 0){
//							liveAroundMe += this.squareGrid[i+1][j-1].alive;
//						}
					}
					else//is alive
					{
						
					}
					
					
				}
			}
		}
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
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(squaresStrings != null)
			{
				for(int j = 0; j < width; j++)
				{
					Square square = new Square(Integer.parseInt(squaresStrings[j]));
					//squares.add(square);
					squareGrid[i][j] = square;
				}
			}
		}
		
		GameOfLife game = new GameOfLife(iterations, width, height, squareGrid);
		game.run();
	} 
}
