import java.util.*;
import java.io.*;

class No8_2
{
    public static void main(String[] args) throws FileNotFoundException 
    {        
		File file = new File("./testcases/Day8.txt"); 
		Scanner in = new Scanner(file); 

		ArrayList<Integer> lineVisited = new ArrayList<>();
		ArrayList<String> cmd = new ArrayList<>();

		int acc = 0;
		int line = 0;
		boolean isRunning = true;

		while(in.hasNext())
		{
			cmd.add(in.nextLine());
		}

		for (int i = 0; i < cmd.size(); i++)
		{			
			acc = 0;
			line = 0;
			isRunning = true;

			String checking = cmd.get(i);
			if(checking.charAt(0) == 'j')
			{
				String newInput = "nop" + checking.substring(3);
				cmd.remove(i);
				cmd.add(i, newInput);
			}
			else if(checking.charAt(0) == 'n')
			{
				String newInput = "jmp" + checking.substring(3);
				cmd.remove(i);
				cmd.add(i, newInput);
			}

			boolean[] visited = new boolean[cmd.size()];

			while (isRunning)
			{
				//check visited
				if (line >= cmd.size())
				{	
					isRunning = false;
					break;
				}
				if (visited[line])
				{				
					isRunning = false;
					break;
				}
				

				String input = cmd.get(line);
				
				String[] fields = input.split("[ ]+");

				visited[line] = true;

				if (fields[0].equals("acc"))
				{
					int sign = 1;
					if (fields[1].charAt(0) == '-')	sign = -1;

					acc += sign * Integer.parseInt(fields[1].substring(1));
					line++;
				}
				else if (fields[0].equals("jmp"))
				{				
					int sign = 1;
					if (fields[1].charAt(0) == '-')	sign = -1;

					line += sign * Integer.parseInt(fields[1].substring(1));
				}
				else if (fields[0].equals("nop"))
				{				
					line++;
				}
			}

			if (line < cmd.size())
			{
				cmd.remove(i);
				cmd.add(i, checking);
			}
			else 
			{
				System.out.println(i);
				break;
			}
		}

		System.out.println(acc);

    }

}