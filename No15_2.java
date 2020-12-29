import java.util.*;
import java.math.*;

import java.io.*;

class No15_2
{
    public static void main(String[] args) throws FileNotFoundException 
    {        
		ArrayList<Integer> list = new ArrayList<Integer>();
		Hashtable<Integer, Integer> dict = new Hashtable<>();

		list.add(18);
		dict.put(18, 0);
		list.add(8);
		dict.put(8, 1);
		list.add(0);
		dict.put(0, 2);
		list.add(5);
		dict.put(5, 3);
		list.add(4);
		dict.put(4, 4);
		list.add(1);
		dict.put(1, 5);
		list.add(20);

		for (int i = list.size() + 1; i <= 30000000; i++)
		{
			int last = list.get(i - 2);

			if (dict.get(last) == null) 
			{
				list.add(0);
			}
			else
			{
				int index = dict.get(last);
				list.add(list.size() - index - 1);
			}
			dict.put(last, i - 2);

			//System.out.print(i + ": " + list.get(list.size() - 1) + " " + dict.get(last) + " // ");
		}

		System.out.println(list.get(list.size() - 1));
	}
}