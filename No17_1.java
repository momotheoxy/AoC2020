import java.util.*;

import java.io.*;

class No17_1
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

		char[][][] map = new char[x][y][z];

		//initialise the array
		for (int i = 0; i < x; i++)
		{
			for (int j = 0; j < y; j++)
			{
				for (int k = 0; k < z; k++)
				{
					map[i][j][k] = '.';
				}
			}
		}

		System.out.println("Before any cycles:");
		System.out.println("z=0");
		//copy cycle 0 data
		for (int j = 0; j < input.size(); j++)
		{
			for (int i = 0; i < input.get(0).length(); i++)
			{
				map[x * 5 / 11 + i][y * 5 / 11 + j][(z + 2) / 2] = input.get(j).charAt(i);
				System.out.print(map[x * 5 / 11 + i][y * 5 / 11 + j][(z + 2) / 2]);
			}
			System.out.println();
		}

		//process 6 cycles
		for (int loop = 1; loop <= cycle; loop++)
		{
			System.out.println("After " + loop + " cycle(s)");
			char temp[][][] = new char[x][y][z];
			for (int i = 0; i < x; i++)
			{
				for (int j = 0; j < y; j++)
				{
					for (int k = 0; k < z; k++)
					{
						temp[i][j][k] = '.';
					}
				}
			}
			
			for (int k = 1; k < z - 1; k++)
			{
				System.out.println("z = " + (k - (z + 2)/2));
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
									if (a == i && b == j && c == k) continue;
									if (map[a][b][c] == '#')	count++;
								}
							}
						}

						//active
						if (map[i][j][k] == '#')	
						{
							if (count == 2 || count == 3)	temp[i][j][k] = '#';
						}
						//inactive
						else
						{
							if (count == 3)	temp[i][j][k] = '#';
						}
						
						System.out.print(temp[i][j][k]);
					}
					System.out.println();
				}
			}

			for (int i = 0; i < x; i++)
			{
				for (int j = 0; j < y; j++)
				{
					for (int k = 0; k < z; k++)
					{
						map[i][j][k] = temp[i][j][k];
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
					if (map[i][j][k] == '#')	count++;
				}
			}
		}

		System.out.println(count);
	}
}