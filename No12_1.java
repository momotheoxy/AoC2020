import java.util.*;
import java.io.*;

class No12_1
{
    public static void main(String[] args) throws FileNotFoundException 
    {        
		File file = new File("./testcases/Day12.txt"); 
		Scanner in = new Scanner(file); 

		int latitude = 0;
		int longtitude = 0;

		char[] direction = {'E', 'S', 'W', 'N'};
		int currentDirection = 0;

		while(in.hasNext())
		{
			String input = in.next();

			switch (input.charAt(0))
			{
				case 'N':	latitude -= Integer.parseInt(input.substring(1)); break;
				case 'S':	latitude += Integer.parseInt(input.substring(1)); break;
				case 'W':	longtitude -= Integer.parseInt(input.substring(1)); break;
				case 'E':	longtitude += Integer.parseInt(input.substring(1)); break;
				case 'L':	
							if (Integer.parseInt(input.substring(1)) == 90)	currentDirection--;
							else if (Integer.parseInt(input.substring(1)) == 180)	currentDirection -= 2;
							else if (Integer.parseInt(input.substring(1)) == 270)	currentDirection -= 3;
							if (currentDirection < 0)	currentDirection += 4;
							break;
				case 'R':	
							if (Integer.parseInt(input.substring(1)) == 90)	currentDirection++;
							else if (Integer.parseInt(input.substring(1)) == 180)	currentDirection += 2;
							else if (Integer.parseInt(input.substring(1)) == 270)	currentDirection += 3;
							if (currentDirection > 3)	currentDirection -= 4;
							break;
				case 'F':	
							if (currentDirection == 0)	longtitude += Integer.parseInt(input.substring(1));
							else if  (currentDirection == 1)	latitude += Integer.parseInt(input.substring(1)); 
							else if  (currentDirection == 2)	longtitude -= Integer.parseInt(input.substring(1));
							else if  (currentDirection == 3)	latitude -= Integer.parseInt(input.substring(1)); 
							break;
			}
		}

		System.out.println((Math.abs(latitude) + Math.abs(longtitude)));
    }
}