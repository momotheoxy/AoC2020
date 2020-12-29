import java.util.*;
import java.io.*;

class No3_2
{
    public static void main(String[] args) throws FileNotFoundException 
    {        
		File file = new File("./testcases/Day3.txt"); 
		Scanner in = new Scanner(file); 
		int index11 = 0, index31 = 0, index51 = 0, index71 = 0, index12 = 0;
		int countTree11 = 0, countTree31 = 0, countTree51 = 0, countTree71 = 0, countTree21 = 0;

		int countLine = 0;

		in.next();
        
		while(in.hasNext())
		{
			String input = in.next();
			countLine++;

			index11 += 1;			
			if (index11 >= input.length())	index11 -= input.length();
			if (input.charAt(index11) == '#')	countTree11++;
			
			index31 += 3;			
			if (index31 >= input.length())	index31 -= input.length();
			if (input.charAt(index31) == '#')	countTree31++;
			
			index51 += 5;			
			if (index51 >= input.length())	index51 -= input.length();
			if (input.charAt(index51) == '#')	countTree51++;
			
			index71 += 7;			
			if (index71 >= input.length())	index71 -= input.length();
			if (input.charAt(index71) == '#')	countTree71++;

			if (countLine % 2 == 0)
			{				
				index12 += 1;			
				if (index12 >= input.length())	index12 -= input.length();
				if (input.charAt(index12) == '#')	countTree21++;
			}
		}

		System.out.println(countTree11);
		System.out.println(countTree31);
		System.out.println(countTree51);
		System.out.println(countTree71);
		System.out.println(countTree21);
    }

}