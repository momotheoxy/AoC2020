import java.util.*;
import java.io.*;

class No5_1
{
    public static void main(String[] args) throws FileNotFoundException 
    {        
		File file = new File("./testcases/Day5.txt"); 
		Scanner in = new Scanner(file); 
		int maxID = 0;
        
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

			System.out.println(row + " " + col);

			currentID = row * 8 + col;

			if (currentID > maxID)	maxID = currentID;
		}

		System.out.println(maxID);
    }

}