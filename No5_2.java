import java.util.*;
import java.io.*;

class No5_2
{
    public static void main(String[] args) throws FileNotFoundException 
    {        
		File file = new File("./testcases/Day5.txt"); 
		Scanner in = new Scanner(file); 
		boolean allID[] = new boolean[8 * 128];
        
		while(in.hasNext())
		{
			String input = in.next();

			int row = 0, col = 0;

			int currentID = 0;

			//find row
			int min = 0, max = 127;
			for (int i = 0; i <= 6; i++)
			{
				char c = input.charAt(i);

				if (c == 'F')	max = (min + max) / 2;
				else if (c == 'B')	min = (min + max) / 2 + 1;
			}

			row = min;

			//find col
			min = 0; max = 7;
			for (int i = 7; i <= 9; i++)
			{
				char c = input.charAt(i);

				if (c == 'L')	max = (min + max) / 2;
				else if (c == 'R')	min = (min + max) / 2 + 1;
			}

			col = min;

			currentID = row * 8 + col;

			System.out.println(row + " " + col + " " + currentID);

			allID[currentID] = true;
		}

		System.out.println("======");
		//find missing ID
		for (int i = 8; i < allID.length - 8; i++)
		{
			if (!allID[i])
			{
				System.out.println(i);
		//		break;
			}
		}
    }

}