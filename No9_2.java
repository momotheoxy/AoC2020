import java.util.*;
import java.io.*;

class No9_2
{
    public static void main(String[] args) throws FileNotFoundException 
    {        
		File file = new File("./testcases/Day9.txt"); 
		Scanner in = new Scanner(file); 

		ArrayList<Integer> numbers = new ArrayList<>();
		int checking = 0;
		int firstIndex = 0;

		for (int i = 1; i <= 25; i++)
		{
			int adding = Integer.parseInt(in.next());
			numbers.add(adding);
		}

		while(in.hasNext())
		{
			checking = Integer.parseInt(in.next());

			boolean foundSum = false;

			loop: for (int i = firstIndex; i < numbers.size() - 1; i++)
			{
				for (int j = i + 1; j < numbers.size(); j++)
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

			//numbers.remove(0);
			numbers.add(checking);
			firstIndex++;
		}

		//find contiguous range

		int first = -1, last = -1;
		
		System.out.println("===");
		System.out.println(numbers.size());
		System.out.println(checking);

		loop_range: for (first = 0; first < numbers.size() - 1; first++)
		{
			long sum = numbers.get(first);
			for (last = first + 1; last < numbers.size(); last++)
			{
				sum += numbers.get(last);
				//System.out.println(first+" "+last+" "+sum);
				if (sum == checking) 
				{
					System.out.println(sum);
					break loop_range;
				}
			}
		}

		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;

		for (int i = first; i <= last; i++)
		{
			min = numbers.get(i) < min? numbers.get(i): min;
			max = numbers.get(i) > max? numbers.get(i): max;
		}
		
		int sum = min + max;

		System.out.println(sum);

    }

}