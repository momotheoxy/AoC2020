import java.util.*;
import java.io.*;

class No10_1
{
    public static void main(String[] args) throws FileNotFoundException 
    {        
		File file = new File("./testcases/Day10.txt"); 
		Scanner in = new Scanner(file); 

		ArrayList<Integer> jolts = new ArrayList<>();

		while(in.hasNext())
		{
			int adding = Integer.parseInt(in.next());
			jolts.add(adding);
		}

		int[] joltsArray = jolts.stream().mapToInt(i -> i).toArray();

		Arrays.sort(joltsArray);

		int diff[] = new int[3];

		//first one
		diff[joltsArray[0] - 1]++;

		for (int i = 1; i < joltsArray.length; i++)
		{
			diff[joltsArray[i] - joltsArray[i - 1] - 1]++;
		}

		//last one
		diff[2]++;

		System.out.println(diff[0] * diff[2]);
    }

}