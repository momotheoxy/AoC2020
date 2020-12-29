import java.util.*;
import java.io.*;

class No13_1
{
    public static void main(String[] args) throws FileNotFoundException 
    {        
		File file = new File("./testcases/Day13.txt"); 
		Scanner in = new Scanner(file); 

		int timestamp = Integer.parseInt(in.nextLine());
		
		String[] buses = in.nextLine().split("[,]+");

		int minWait = Integer.MAX_VALUE;
		int minBusID = 0;

		for (int i = 0; i < buses.length; i++)
		{
			if (!buses[i].equals("x"))
			{
				int busID = Integer.parseInt(buses[i]);
				int wait = busID - (timestamp % busID);
				if (wait < minWait)
				{
					minWait = wait;
					minBusID = busID;
				}
			}
		}

		System.out.println(minBusID + " " + minWait);
		System.out.println(minBusID * minWait);
    }
}