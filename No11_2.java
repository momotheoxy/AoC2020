import java.util.*;
import java.io.*;

class No11_2
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
						//top
						for (int w = i - 1; w >= 0; w--)
						{
							if (seat[w][j] == '#')	
							{
								isSomeOccupied = true;
								break;
							}
							else if (seat[w][j] == 'L')	break;
						}
						//bottom
						for (int w = i + 1; w < row; w++)
						{
							if (seat[w][j] == '#')	
							{
								isSomeOccupied = true;
								break;
							}
							else if (seat[w][j] == 'L')	break;
						}						
						//left
						for (int h = j - 1; h >= 0; h--)
						{
							if (seat[i][h] == '#')	
							{
								isSomeOccupied = true;
								break;
							}
							else if (seat[i][h] == 'L')	break;
						}					
						//right
						for (int h = j + 1; h < col; h++)
						{
							if (seat[i][h] == '#')	
							{
								isSomeOccupied = true;
								break;
							}
							else if (seat[i][h] == 'L')	break;
						}
						//top left
						for (int k = 1; i - k >= 0 && j - k >= 0; k++)
						{
							if (seat[i - k][j - k] == '#')	
							{
								isSomeOccupied = true;
								break;
							}
							else if (seat[i - k][j - k] == 'L')	break;
						}
						
						//top right
						for (int k = 1; i - k >= 0 && j + k < col; k++)
						{
							if (seat[i - k][j + k] == '#')	
							{
								isSomeOccupied = true;
								break;
							}
							else if (seat[i - k][j + k] == 'L')	break;
						}
						//bottom left
						for (int k = 1; i + k < row && j - k >= 0; k++)
						{
							if (seat[i + k][j - k] == '#')	
							{
								isSomeOccupied = true;
								break;
							}
							else if (seat[i + k][j - k] == 'L')	break;
						}
						
						//bottom right
						for (int k = 1; i + k < row && j + k < col; k++)
						{
							if (seat[i + k][j + k] == '#')	
							{
								isSomeOccupied = true;
								break;
							}
							else if (seat[i + k][j + k] == 'L')	break;
						}
						if (!isSomeOccupied)	nextSeat[i][j] = '#';
					}
					else if (seat[i][j] == '#')
					{					
						int numOccupied = 0;
						
						//top
						for (int w = i - 1; w >= 0; w--)
						{
							if (seat[w][j] == '#')	
							{
								numOccupied++;
								break;
							}
							else if (seat[w][j] == 'L')	break;
						}
						//bottom
						for (int w = i + 1; w < row; w++)
						{
							if (seat[w][j] == '#')	
							{
								numOccupied++;
								break;
							}
							else if (seat[w][j] == 'L')	break;
						}						
						//left
						for (int h = j - 1; h >= 0; h--)
						{
							if (seat[i][h] == '#')	
							{
								numOccupied++;
								break;
							}
							else if (seat[i][h] == 'L')	break;
						}					
						//right
						for (int h = j + 1; h < col; h++)
						{
							if (seat[i][h] == '#')	
							{
								numOccupied++;
								break;
							}
							else if (seat[i][h] == 'L')	break;
						}
						//top left
						for (int k = 1; i - k >= 0 && j - k >= 0; k++)
						{
							if (seat[i - k][j - k] == '#')	
							{
								numOccupied++;
								break;
							}
							else if (seat[i - k][j - k] == 'L')	break;
						}
						
						//top right
						for (int k = 1; i - k >= 0 && j + k < col; k++)
						{
							if (seat[i - k][j + k] == '#')	
							{
								numOccupied++;
								break;
							}
							else if (seat[i - k][j + k] == 'L')	break;
						}
						//bottom left
						for (int k = 1; i + k < row && j - k >= 0; k++)
						{
							if (seat[i + k][j - k] == '#')	
							{
								numOccupied++;
								break;
							}
							else if (seat[i + k][j - k] == 'L')	break;
						}
						
						//bottom right
						for (int k = 1; i + k < row && j + k < col; k++)
						{
							if (seat[i + k][j + k] == '#')	
							{
								numOccupied++;
								break;
							}
							else if (seat[i + k][j + k] == 'L')	break;
						}
						if (numOccupied >= 5)	nextSeat[i][j] = 'L';
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