import java.util.*;
import java.io.*;

class No9_1
{
    public static void main(String[] args) throws FileNotFoundException 
    {        
		File file = new File("./testcases/Day9.txt"); 
		Scanner in = new Scanner(file); 

		ArrayList<Integer> numbers = new ArrayList<>();
		int checking = 0;

		for (int i = 1; i <= 25; i++)
		{
			numbers.add(Integer.parseInt(in.next()));
		}

		while(in.hasNext())
		{
			checking = Integer.parseInt(in.next());

			boolean foundSum = false;

			loop: for (int i = 0; i < numbers.size() - 1; i++)
			{
				for (int j = i; j < numbers.size(); j++)
				{
					int sum = numbers.get(i) + numbers.get(j);
					if (sum == checking)
					{
						foundSum = true;
						break loop;
					}
				}
			}

			if (!foundSum) break;

			numbers.remove(0);
			numbers.add(checking);
		}

		System.out.println(checking);

    }

}