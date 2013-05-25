import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GameOfLife 
{
	private int iterations;
	private int width;
	private int height;
	
	public GameOfLife(int iterations, int width, int height)
	{
		this.iterations = iterations;
		this.width = width;
		this.height = height;
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
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		try 
		{
			iterations = Integer.parseInt(br.readLine());
		} catch (IOException ioe) {
		     System.out.println("IO error trying to read number of iterations.");
		     System.exit(1);
		}
		
		try 
		{
			String [] widthHeight = br.readLine().split(" ");
			if(widthHeight.length != 2)
				throw new IOException("expecting two values in second line representing width and height separated by a space");
			
			width = Integer.parseInt(widthHeight[0]);
			height = Integer.parseInt(widthHeight[1]);
			
		} catch (IOException ioe) {
		     System.out.println("IO error trying to read number of iterations.");
		     System.exit(1);
		}
		
		GameOfLife game = new GameOfLife(iterations, width, height);
		game.run();
	} 
}
