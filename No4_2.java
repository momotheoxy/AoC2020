import java.util.*;
import java.io.*;

class No4_2
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

					if (data[0].equals("byr") && Integer.parseInt(data[1]) >= 1920 && Integer.parseInt(data[1]) <= 2002)	byr = true;
					else if (data[0].equals("iyr") && Integer.parseInt(data[1]) >= 2010 && Integer.parseInt(data[1]) <= 2020)	iyr = true;
					else if (data[0].equals("eyr") && Integer.parseInt(data[1]) >= 2020 && Integer.parseInt(data[1]) <= 2030)	eyr = true;
					else if (data[0].equals("hgt"))
					{
						String unit = data[1].substring(data[1].length() - 2);

						if (!(unit.equals("cm") || unit.equals("in")))	continue;

						int height = Integer.parseInt(data[1].substring(0, data[1].length() - 2));

						if (unit.equals("cm") && height >= 150 && height <= 193) hgt = true;
						if (unit.equals("in") && height >= 59 && height <= 76) hgt = true;
					}
					else if (data[0].equals("hcl"))
					{
						if (data[1].charAt(0) != '#')	continue;
						if (data[1].length() != 7)	continue;
						try
						{
							Integer.parseInt(data[1].substring(1), 16);
						}
						catch (NumberFormatException e)
						{
							continue;
						}
						hcl = true;
					}
					else if (data[0].equals("ecl"))
					{
						if (data[1].equals("amb") || 
						data[1].equals("blu") || 
						data[1].equals("brn") || 
						data[1].equals("gry") || 
						data[1].equals("grn") || 
						data[1].equals("hzl") || 
						data[1].equals("oth"))
							ecl = true;
					}
					else if (data[0].equals("pid"))
					{
						if (data[1].length() != 9)	continue;
						try
						{
							Integer.parseInt(data[1].substring(1));
						}
						catch (NumberFormatException e)
						{
							continue;
						}
						pid = true;
					}
				}
			}

			if (byr && iyr && eyr && hgt && hcl && ecl && pid)	countValid++;
		}

		System.out.println(countValid);
    }

}