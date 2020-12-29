import java.util.*;

import java.io.*;

class No17_2
{
    public static void main(String[] args) throws FileNotFoundException 
    {        
		File file = new File("./testcases/Day17.txt"); 
		Scanner in = new Scanner(file); 

		ArrayList<String> input = new ArrayList<>();

		//pool input
		while(in.hasNext())
		{
			input.add(in.nextLine());
		}

		int cycle = 6;

		int x = input.get(0).length() * 11;
		int y = input.size() * 11;
		int z = 4 * cycle - 1;
		int w = 4 * cycle - 1;

		char[][][][] map = new char[x][y][z][w];

		//initialise the array
		for (int i = 0; i < x; i++)
		{
			for (int j = 0; j < y; j++)
			{
				for (int k = 0; k < z; k++)
				{
					for (int l = 0; l < w; l++)
					{
						map[i][j][k][l] = '.';
					}
				}
			}
		}

		//copy cycle 0 data
		for (int j = 0; j < input.size(); j++)
		{
			for (int i = 0; i < input.get(0).length(); i++)
			{
				map[x * 5 / 11 + i][y * 5 / 11 + j][(z + 2) / 2][(w + 2) / 2] = input.get(j).charAt(i);
			}
		}

		//process 6 cycles
		for (int loop = 1; loop <= cycle; loop++)
		{
			char temp[][][][] = new char[x][y][z][w];
			for (int i = 0; i < x; i++)
			{
				for (int j = 0; j < y; j++)
				{
					for (int k = 0; k < z; k++)
					{
						for (int l = 0; l < w; l++)
						{
							temp[i][j][k][l] = '.';
						}
					}
				}
			}
			
			for (int l = 1; l < w - 1; l++)
			{
				for (int k = 1; k < z - 1; k++)
				{
					for (int j = 1; j < y - 1; j++)
					{
						for (int i = 1; i < x - 1; i++)
						{
							int count = 0;

							for (int a = i - 1; a <= i + 1; a++)
							{
								for (int b = j - 1; b <= j + 1; b++)
								{
									for (int c = k - 1; c <= k + 1; c++)
									{
										for (int d = l - 1; d <= l + 1; d++)
										{
											if (a == i && b == j && c == k && d == l) continue;
											if (map[a][b][c][d] == '#')	count++;
										}
									}
								}
							}

							//active
							if (map[i][j][k][l] == '#')	
							{
								if (count == 2 || count == 3)	temp[i][j][k][l] = '#';
							}
							//inactive
							else
							{
								if (count == 3)	temp[i][j][k][l] = '#';
							}
						}
					}
				}
			}

			for (int i = 0; i < x; i++)
			{
				for (int j = 0; j < y; j++)
				{
					for (int k = 0; k < z; k++)
					{
						for (int l = 0; l < w; l++)
						{
							map[i][j][k][l] = temp[i][j][k][l];
						}
					}
				}
			}
		}

		//final count for actives
		int count = 0;
		for (int i = 0; i < x; i++)
		{
			for (int j = 0; j < y; j++)
			{
				for (int k = 0; k < z; k++)
				{
					for (int l = 0; l < w; l++)
					{
						if (map[i][j][k][l] == '#')	count++;
					}
				}
			}
		}

		System.out.println(count);
	}
}