import java.util.*;
import java.math.*;

import java.io.*;

class No14_2
{
    public static void main(String[] args) throws FileNotFoundException 
    {        
		File file = new File("./testcases/Day14.txt"); 
		Scanner in = new Scanner(file); 

		String mask = "";

		Hashtable<String, Integer> dict = new Hashtable<String, Integer>();

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
				StringBuilder addressStr = new StringBuilder(String.format("%36s", Integer.toBinaryString(address)).replace(' ', '0'));
				
				int value = Integer.parseInt(input.substring(input.indexOf('=')+2));

				int sumFloating = 0;

				for (int i = 0; i < mask.length(); i++)
				{
					if (mask.charAt(i) == '1')
					{
						addressStr.setCharAt(i, '1');
					}
					else if (mask.charAt(i) == 'X')
					{
						addressStr.setCharAt(i, 'X');
						sumFloating++;
					}
				}

				int floatingRound = (int) Math.pow(2, sumFloating);

				for (int i = 0; i < floatingRound; i++)
				{
					String currentMask = String.format("%"+sumFloating+"s", Integer.toBinaryString(i)).replace(' ', '0');
					StringBuilder currentAddress = new StringBuilder(addressStr);
					for (int j = 0; j < currentMask.length(); j++)
					{
						currentAddress.setCharAt(currentAddress.indexOf("X"), currentMask.charAt(j));
					}
					dict.put(currentAddress.toString(), value);
					System.out.println("key : " + currentAddress.toString() + ",value : " + value);
				}

				//dict.put(address, intValue);
			}
		}

		long sum = 0;
		for (String key: dict.keySet()) {
			sum += dict.get(key);
		}
		System.out.println(sum);
	}
}