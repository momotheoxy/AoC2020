import java.util.*;
import java.io.*;

class No8_1
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

		boolean[] visited = new boolean[cmd.size()];

		while (isRunning)
		{
			//check visited
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

		System.out.println(acc);

    }

}