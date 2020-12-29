import java.util.*;
import java.io.*;

class No10_2
{
    public static void main(String[] args) throws FileNotFoundException 
    {        
		File file = new File("./testcases/Day10.txt"); 
		Scanner in = new Scanner(file); 

		ArrayList<Integer> jolts = new ArrayList<>();
		int max = Integer.MIN_VALUE;

		while(in.hasNext())
		{
			int adding = Integer.parseInt(in.next());
			jolts.add(adding);
			max = adding > max? adding: max;
		}

		max += 3;
		jolts.add(0);
		jolts.add(max);

		int[] joltsArray = jolts.stream().mapToInt(i -> i).toArray();

		Arrays.sort(joltsArray);

		long totalSetCount = 1;
		int count = 0;

		for (int i = 1; i < joltsArray.length; i++)
		{
			/*for (int j = 1; j <= 3 && i + j < joltsArray.length ; j++)
			{
				if (joltsArray[i + j] - joltsArray[i] <= 3) count++;
			}*/
			//totalSetCount += count;
			if (joltsArray[i] - joltsArray[i - 1] == 1)
			{
				count++;
			}
			else
			{
				switch (count)
				{
					case 4: totalSetCount *= 7; break;
					case 3: totalSetCount *= 4; break;
					case 2: totalSetCount *= 2; break;
				}
				count = 0;
			}
		}


		System.out.println(totalSetCount);
    }

}