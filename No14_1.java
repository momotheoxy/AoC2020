import java.util.*;
import java.math.*;

import java.io.*;

class No14_1
{
    public static void main(String[] args) throws FileNotFoundException 
    {        
		File file = new File("./testcases/Day14.txt"); 
		Scanner in = new Scanner(file); 

		String mask = "";

		Hashtable<Integer, Long> dict = new Hashtable<Integer, Long>();

		while(in.hasNext())
		{
			String input = in.nextLine();
			if (input.charAt(1) == 'a')
			{
				mask = input.substring(7);
			}
			else 
			{
				int address = Integer.parseInt(input.substring(input.indexOf('[')+1,input.indexOf(']')));
				
				StringBuilder value = new StringBuilder(String.format("%36s", Integer.toBinaryString(Integer.parseInt(input.substring(input.indexOf('=')+2)))).replace(' ', '0'));

				//System.out.println(value);

				for (int i = 0; i < mask.length(); i++)
				{
					if (mask.charAt(i) != 'X')
					{
						value.setCharAt(i, mask.charAt(i));
					}
				}

				long intValue = parseLong(value.toString(), 2);

				//System.out.println(intValue);

				dict.put(address, intValue);
			}
		}

		long sum = 0;
		for (Integer key: dict.keySet()) {
			System.out.println("key : " + key);
			System.out.println("value : " + dict.get(key));
			sum += dict.get(key);
		}
		System.out.println(sum);
	}
	
	private static long parseLong(String s, int base) {
		return new BigInteger(s, base).longValue();
	}
}