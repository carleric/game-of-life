import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class GameOfLife 
{
	private int iterations;
	private int width;
	private int height;
	private ArrayList <Square>squares;
	
	public GameOfLife(int iterations, int width, int height, ArrayList<Square> squares)
	{
		this.iterations = iterations;
		this.width = width;
		this.height = height;
		this.squares = squares;
	}
	
	public void run()
	{
		
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
		
		ArrayList<Square> squares = new ArrayList<Square>();
		
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
					squares.add(square);
				}
			}
		}
		
		GameOfLife game = new GameOfLife(iterations, width, height, squares);
		game.run();
	} 
}
