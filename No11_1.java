import java.util.*;
import java.io.*;

class No11_1
{
    public static void main(String[] args) throws FileNotFoundException 
    {        
		File file = new File("./testcases/Day11.txt"); 
		Scanner in = new Scanner(file); 

		ArrayList<String> input = new ArrayList<>();

		while(in.hasNext())
		{
			input.add(in.nextLine());
		}

		char[][] nextSeat = new char[input.size()][input.get(0).length()];
		char[][] seat = new char[input.size()][input.get(0).length()];

		int row = input.size();
		int col = input.get(0).length();

		for (int i = 0; i < row; i++)
		{
			for (int j = 0; j < col; j++)
			{
				seat[i][j] = input.get(i).charAt(j);
			}
		}

		boolean isNoneChanged = false;
		int sumOccupied = 0;

		while (!isNoneChanged)
		{
			isNoneChanged = true;
			sumOccupied = 0;
			for (int i = 0; i < row; i++)
			{
				for (int j = 0; j < col; j++)
				{
					nextSeat[i][j] = seat[i][j];
					if (seat[i][j] == 'L')
					{
						boolean isSomeOccupied = false;
						for (int w = i - 1; w <= i + 1; w++)
						{
							for (int h = j - 1; h <= j + 1; h++)
							{
								if (w >= 0 && w < row &&
								h >= 0 && h < col &&
								seat[w][h] == '#' && !(w == i && h == j))	isSomeOccupied = true;
							}
						}
						if (!isSomeOccupied)	nextSeat[i][j] = '#';
					}
					else if (seat[i][j] == '#')
					{					
						int numOccupied = 0;
						for (int w = i - 1; w <= i + 1; w++)
						{
							for (int h = j - 1; h <= j + 1; h++)
							{
								if (w >= 0 && w < row &&
								h >= 0 && h < col &&
									seat[w][h] == '#' && !(w == i && h == j))	numOccupied++;
							}
						}
						if (numOccupied >= 4)	nextSeat[i][j] = 'L';
					}
				}
			}

			for (int i = 0; i < row; i++)
			{
				for (int j = 0; j < col; j++)
				{
					if (nextSeat[i][j] != seat[i][j]) isNoneChanged = false;
					if (nextSeat[i][j] == '#') sumOccupied++;
					seat[i][j] = nextSeat[i][j];
				}
			}
		}
		System.out.println(sumOccupied);
    }
}