import java.util.*;
import java.io.*;

class No4_1
{
    public static void main(String[] args) throws FileNotFoundException 
    {        
		File file = new File("./testcases/Day4.txt"); 
		Scanner in = new Scanner(file); 
		int countValid = 0;
        
		while(in.hasNext())
		{
			boolean	byr = false, iyr = false, eyr = false, hgt = false, hcl = false, ecl = false, pid = false;

			innerloop:while(true)
			{
				if (!in.hasNext()) break innerloop;
				String input = in.nextLine();
				if (input.trim().isEmpty()) break innerloop;
				String[] fields = input.split("[ ]+");

				for (int i = 0; i < fields.length; i++)
				{
					String[] data = fields[i].split("[:]+");

					if (data[0].equals("byr"))	byr = true;
					else if (data[0].equals("iyr"))	iyr = true;
					else if (data[0].equals("eyr"))	eyr = true;
					else if (data[0].equals("hgt"))	hgt = true;
					else if (data[0].equals("hcl"))	hcl = true;
					else if (data[0].equals("ecl"))	ecl = true;
					else if (data[0].equals("pid"))	pid = true;
				}
			}

			if (byr && iyr && eyr && hgt && hcl && ecl && pid)	countValid++;
		}

		System.out.println(countValid);
    }

}