import java.util.ArrayList;

public class Day7Bags 
{
    public String currentColour = "";
    public ArrayList<Integer> numbers = new ArrayList<>();
    public ArrayList<Day7Bags> childBags = new ArrayList<>();
    public ArrayList<Day7Bags> parentBags = new ArrayList<>();
    public Day7Bags(String current)
    {
        currentColour = current;
    }
    public int countParent()
    {
        int count = 0;
        for (Day7Bags day7Bags : parentBags) 
        {
            count += day7Bags.countParent();   
            System.out.println(day7Bags.currentColour+" has " + count); 
        }
        count++;
        System.out.println(currentColour+" has " + count); 
        return count;
    }

    public void print(int level)
    {
        /*for (int i = 1; i <= level; i++)
        {
            System.out.print("*");
        }*/
        System.out.println(currentColour);

        for (Day7Bags day7Bags : parentBags) 
        {
            day7Bags.print(level + 1);
        }
    }
    public int countChild()
    {
        int count = 0;
        for (int i = 0; i < childBags.size(); i++)
        {
            count += (numbers.get(i).intValue() * (childBags.get(i).countChild()+1));
            System.out.println(childBags.get(i).currentColour+" has " + count); 
        }
        return count;
    }
}
