import java.util.*;

import java.io.*;

class No18_2
{
    public static void main(String[] args) throws FileNotFoundException 
    {        
		File file = new File("./testcases/Day18.txt"); 
		Scanner in = new Scanner(file); 

		long sum = 0;

		while(in.hasNext())
		{
			sum += findSolution(in.nextLine());
		}
		
		System.out.println(sum);

	}

	public static long findSolution(String input)
	{
        Stack<String> expression = new Stack<>();

        
        System.out.println("Finding Solution => " + input);

        String number = "";

        for (int i = 0; i < input.length(); i++)
        {
            if (input.charAt(i) == '(')
            {
                expression.push(""+input.charAt(i));
            }
            else if (input.charAt(i) == ')')
            {
                if (!number.isEmpty())
                {
                    expression.push(number);
                    number = "";
                }

                ArrayList<String> calculating = new ArrayList<>();
                while (!expression.peek().equals("("))
                {
                    calculating.add(0, expression.pop());
                }
                expression.pop();

                long resultExp = calculate(calculating);

                expression.push("" + resultExp);
            }
            else if (Character.isDigit(input.charAt(i)))
            {
                number += input.charAt(i);
            }
            else if (Character.isWhitespace(input.charAt(i)))
            {
                if (!number.isEmpty())
                {
                    expression.push(number);
                    number = "";
                }
            }
            else if (input.charAt(i) == '+' || input.charAt(i) == '*')
            {
                expression.push(""+input.charAt(i));
            }
        }

        if (!number.isEmpty())
        {
            expression.push(number);
            number = "";
        }

        return calculate(expression);
    }
    
    public static long calculate(List<String> input)
	{

        System.out.println("Calculating => " + input.toString());

        Stack<String> calculatingStack = new Stack<>();

        //clear + first
        for (int i = 0; i < input.size(); i++)
        {
            String expression = input.get(i);
            try
            {
                long number = Long.parseLong(expression);

                if (!calculatingStack.empty() && calculatingStack.peek().equals("+"))
                {
                    calculatingStack.pop();
                    number += Long.parseLong(calculatingStack.pop());
                }

                calculatingStack.push("" + number);
            }
            catch (NumberFormatException e)
            {
                calculatingStack.push(expression);
            }
        }
        
        System.out.println("Still Calculating => " + calculatingStack.toString());
        
        long result = 1;
        
        for (int i = 0; i < calculatingStack.size(); i++)
        {
            String expression = calculatingStack.get(i);
            try
            {
                long number = Long.parseLong(expression);

                result *= number;
            }
            catch (NumberFormatException e)
            {
            }
        }

        return result;
    }
}