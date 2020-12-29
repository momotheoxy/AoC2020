import java.util.*;
import java.io.*;

class No7_2
{
    public static void main(String[] args) throws FileNotFoundException 
    {        
		File file = new File("./testcases/Day7.txt"); 
		Scanner in = new Scanner(file); 

		ArrayList<Day7Bags> bags = new ArrayList<>();
        
		while(in.hasNext())
		{
			String input = in.nextLine();
			
			String[] fields = input.split("[ ]+");

			String currentBagColour = fields[0] + " " + fields[1];

			//find existing bag
			boolean isFound = false;
			for (Day7Bags aBag : bags) 
			{
				if (aBag.currentColour.equals(currentBagColour))
				{
					//add child bag
					for (int i = 4; i < fields.length; i += 4)
					{
						if (fields[i].equals("no"))	continue;
						int childNumber = Integer.parseInt(fields[i]);				
						String childBagColour = fields[i + 1] + " " + fields[i + 2];
						
						boolean isChildFound = false;
						for (int j = 0; j < bags.size(); j++) 
						{
							if (bags.get(j).currentColour.equals(childBagColour))
							{
							//	System.out.println("yes "+bags.get(j).currentColour);
								aBag.numbers.add(childNumber);
								aBag.childBags.add(bags.get(j));
								bags.get(j).parentBags.add(aBag);
								isChildFound = true;
								break;
							}
						}

						if (!isChildFound)
						{
							Day7Bags childBag = new Day7Bags(childBagColour);
							aBag.numbers.add(childNumber);
							aBag.childBags.add(childBag);
							childBag.parentBags.add(aBag);
							bags.add(childBag);
						}
					}
					isFound = true;
					break;
				}
			}

			if (!isFound)
			{
				Day7Bags currentBag = new Day7Bags(currentBagColour);
				//add child bag
				for (int i = 4; i < fields.length; i += 4)
				{
					if (fields[i].equals("no"))	continue;
					int childNumber = Integer.parseInt(fields[i]);				
					String childBagColour = fields[i + 1] + " " + fields[i + 2];
					
					boolean isChildFound = false;
					for (int j = 0; j < bags.size(); j++) 
					{
						if (bags.get(j).currentColour.equals(childBagColour))
						{
							currentBag.numbers.add(childNumber);
							currentBag.childBags.add(bags.get(j));
							bags.get(j).parentBags.add(currentBag);
							isChildFound = true;
							//break;
						}
					}

					if (!isChildFound)
					{
						Day7Bags childBag = new Day7Bags(childBagColour);
						currentBag.numbers.add(childNumber);
						currentBag.childBags.add(childBag);
						childBag.parentBags.add(currentBag);
						bags.add(childBag);
					}
				}
				bags.add(currentBag);
			}	
		}

		/*for (Day7Bags aBag : bags) 
		{
			System.out.print(aBag.currentColour + " has ");

			for (int i = 0; i < aBag.childBags.size(); i++)
       		{
				System.out.print(aBag.numbers.get(i).intValue() + " " + aBag.childBags.get(i).currentColour + ", ");
			}
			System.out.println();
		}*/
	
		for (Day7Bags aBag : bags) 
		{
			if (aBag.currentColour.equals("shiny gold"))
			{
				//aBag.print(0);
				System.out.println(aBag.countChild());
				//System.out.println(aBag.countParent());
			}
		}
    }

}