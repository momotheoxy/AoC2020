import java.util.*;
import java.io.*;

class No2
{
    public static void main(String[] args) throws FileNotFoundException 
    {        
		File file = new File("./testcases/Day1.txt"); 
		Scanner in = new Scanner(file); 
		Vector<Integer> vector = new Vector<>();
        
		while(in.hasNext())
		{
			vector.add(in.nextInt());
		}

		for (int i = 0; i < vector.size() - 2; i++)
		{
			int integer1 = vector.get(i).intValue();

			for (int j = i + 1; j < vector.size() - 1; j++)
			{
				int integer2 = vector.get(j).intValue();

				for (int k = j + 1; k < vector.size() - 1; k++)
				{
					int integer3 = vector.get(k).intValue();
					if (integer1 + integer2 + integer3 == 2020)
					{
						System.out.println(integer1 + " " + integer2 + " " + integer3);
						System.out.println((integer1 * integer2 * integer3));
						break;
					}
				}				
			}
		}
    }

}