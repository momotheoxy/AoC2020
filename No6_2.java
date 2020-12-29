import java.util.*;
import java.io.*;

class No6_2
{
    public static void main(String[] args) throws FileNotFoundException 
    {        
		File file = new File("./testcases/Day6.txt"); 
		Scanner in = new Scanner(file); 
		int count = 0;
        
		while(in.hasNext())
		{
			ArrayList<String> allAns = new ArrayList<>();
			
			innerloop:while(true)
			{
				if (!in.hasNext()) break innerloop;
				String input = in.nextLine();
				if (input.trim().isEmpty()) break innerloop;

				allAns.add(input);
			}

			boolean checking[][] = new boolean[allAns.size()][26];

			for (int j = 0; j < allAns.size(); j++)
			{
				for (int i = 0; i < allAns.get(j).length(); i++)
				{
					char c = allAns.get(j).charAt(i);
					int index = c - 'a';

					checking[j][index] = true;
				}
			}
			
			for (int i = 0; i < 26; i++)
			{
				int finalCheck = 0;
				for (int j = 0; j < allAns.size(); j++)
				{
					if (checking[j][i])	finalCheck++;
				}
				if (finalCheck == allAns.size()) count++;
			}
		}

		System.out.println(count);
    }

}