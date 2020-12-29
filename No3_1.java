import java.util.*;
import java.io.*;

class No3_1
{
    public static void main(String[] args) throws FileNotFoundException 
    {        
		File file = new File("./testcases/Day3.txt"); 
		Scanner in = new Scanner(file); 
		int currentIndex = 0, countTree = 0;

		in.next();
        
		while(in.hasNext())
		{
			String input = in.next();
			currentIndex += 3;
			if (currentIndex >= input.length())	currentIndex -= input.length();

			if (input.charAt(currentIndex) == '#')	countTree++;
		}

		System.out.println(countTree);
    }

}