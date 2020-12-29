import java.util.*;
import java.io.*;

class No6_1
{
    public static void main(String[] args) throws FileNotFoundException 
    {        
		File file = new File("./testcases/Day6.txt"); 
		Scanner in = new Scanner(file); 
		int count = 0;
        
		while(in.hasNext())
		{
			boolean checking[] = new boolean[26];
			innerloop:while(true)
			{
				if (!in.hasNext()) break innerloop;
				String input = in.nextLine();
				if (input.trim().isEmpty()) break innerloop;

				for (int i = 0; i < input.length(); i++)
				{
					char c = input.charAt(i);
					int index = c - 'a';

					checking[index] = true;
				}
			}

			for (int i = 0; i < 26; i++)
			{
				if (checking[i])	count++;
			}
		}

		System.out.println(count);
    }

}