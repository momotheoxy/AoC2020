import java.util.*;
import java.math.*;

import java.io.*;

class No15_1
{
    public static void main(String[] args) throws FileNotFoundException 
    {        
		ArrayList<Integer> list = new ArrayList<Integer>();

		list.add(18);
		list.add(8);
		list.add(0);
		list.add(5);
		list.add(4);
		list.add(1);
		list.add(20);

		for (int i = list.size() + 1; i <= 2020; i++)
		{
			int last = list.get(i - 2);
			
			int index = i;

			for (index = i - 3; index >= 0; index--)
			{
				if (last == list.get(index))	break;
			}

			if (index < 0)	list.add(0);
			else	list.add(list.size() - index - 1);

			System.out.print(i + ": " + list.get(list.size() - 1) + "//");
		}

		System.out.println(list.get(list.size() - 1));
	}
}