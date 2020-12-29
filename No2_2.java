import java.util.*;
import java.io.*;

class No2_2
{
    public static void main(String[] args) throws FileNotFoundException 
    {        
		File file = new File("./testcases/Day2.txt"); 
		Scanner in = new Scanner(file); 
		Vector<String> vector = new Vector<>();

		int validCount = 0;
        
		while(in.hasNext())
		{
			String boundary = in.next();
			int min = Integer.parseInt(boundary.substring(0, boundary.indexOf('-')));
			int max = Integer.parseInt(boundary.substring(boundary.indexOf('-') + 1));

			char character = in.next().charAt(0);

			String pass = in.next();

			int count = 0;
			for (int i = 0; i < pass.length(); i++)
			{
				if (pass.charAt(i) == character) count++;
			}

			if (count >= min && count <= max)	validCount++;
		}

		System.out.println(validCount);
    }

}