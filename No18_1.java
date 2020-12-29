import java.util.*;

import java.io.*;

class No18_1
{
    public static void main(String[] args) throws FileNotFoundException 
    {        
		File file = new File("./testcases/Day18.txt"); 
		Scanner in = new Scanner(file); 

		long sum = 0;

		while(in.hasNext())
		{
			sum += (long)Double.parseDouble((findSolution(in.nextLine())));
		}
		
		System.out.println(sum);

	}

	public static String findSolution(String input)
	{
        //========INTERPRETER=======

        //lexing (sub string into Tokens)
        ArrayList<Token> tokens = ArithmeticInterpreter.lex(input);

        for (Token t: tokens)
        {
            System.out.print(t.toString() + "\t");
        }
        System.out.println();

        //parsing (convert Tokens into Expression)
        Expression parsed = ArithmeticInterpreter.parse(tokens);
        System.out.println(parsed.eval());

        //TREE TRAVERSAL
        //DESIGN PATTERNS: ITERATOR

        //========VISITOR=========
        //Print
        ExpressionPrinter ep = new ExpressionPrinter();
        ep.visit((BinaryExpression)parsed);
        System.out.println(ep);

        //Calculate
        ExpressionCalculator ec = new ExpressionCalculator();
        ec.visit((BinaryExpression)parsed);
        System.out.println(ec);

		return ec.toString();
	}
}

interface ExpressionVisitor
{
    void visit(DoubleExpression e);
    void visit(BinaryExpression e);
}

class ExpressionPrinter implements ExpressionVisitor
{
    private StringBuilder sb = new StringBuilder();

    @Override
    public void visit(DoubleExpression e)
    {
        sb.append(e.value);
    }

    @Override
    public void visit(BinaryExpression e)
    {
        //(left + right)
        sb.append("(");

        e.left.accept(this);
        
        switch(e.type)
        {
            case ADDITION:
                sb.append("+");
                break;
            case SUBTRACTION:
                sb.append("-");
                break;
			case MULTIPLY:
				sb.append("*");
				break;
            
        }

        e.right.accept(this);

        sb.append(")");
    }

    @Override
    public String toString()
    {
        return sb.toString();
    }
}

class ExpressionCalculator implements ExpressionVisitor
{
    private double result;
    
    @Override
    public void visit (DoubleExpression e)
    {
        result = e.value;
    }
    
    @Override
    public void visit (BinaryExpression e)
    {
        double left, right;
        e.left.accept(this);
        left = result;
        e.right.accept(this);
        right = result;

        switch (e.type)
        {
            case ADDITION:
                result = left + right;
                break;
            case SUBTRACTION:
                result = left - right;
                break;
			case MULTIPLY:
				result = left * right;
				break;
            
        }
    }

    @Override
    public String toString()
    {
        return "" + result;
    }
}

abstract class Expression
{
    public abstract double eval();
    public abstract void accept(ExpressionVisitor visitor);
}

//TERMINAL EXPRESSION
class DoubleExpression extends Expression
{
    public double value;

    public DoubleExpression(double value)
    {
        this.value = value;
    }

    @Override
    public double eval()
    {
        return value;
    }

    @Override
    public void accept(ExpressionVisitor visitor)
    {
        visitor.visit(this);
    }

    //Against SOLID
    //O - Open for extension / Closed for modification
    //S - Single Responsibility
    /*@Override
    public String toString()
    {

    }*/
}

//NON-TERMINAL EXPRESSION
class BinaryExpression extends Expression
{
    public enum Type
    {
        ADDITION, SUBTRACTION, MULTIPLY
    }

    public Type type;
    public Expression left, right;

    public BinaryExpression(Expression left, Expression right)
    {
        this.left = left;
        this.right = right;
    }

    @Override
    public double eval()
    {
        switch (type)
        {
            case ADDITION:
                return left.eval() + right.eval();
            case SUBTRACTION:
                return left.eval() - right.eval();
			case MULTIPLY:
				return left.eval() * right.eval();
            default:
                return 0;
        }
    }
    
    @Override
    public void accept(ExpressionVisitor visitor)
    {
        visitor.visit(this);
    }
}

class Token
{
    public enum Type
    {
        NUMBER, PLUS, MINUS, MUTIPLY, LPAREN, RPAREN
    }

    public Type type;
    public String text;

    public Token (Type type, String text)
    {
        this.type = type;
        this.text = text;
    }

    @Override
    public String toString()
    {
        return "\\" + text + "\\";
    }
}

class ArithmeticInterpreter
{
    static ArrayList<Token> lex (String input)
    {
        ArrayList<Token> result = new ArrayList<>();

        for (int i = 0; i < input.length(); i++)
        {
            switch (input.charAt(i))
            {
                case '+':
                    result.add(new Token(Token.Type.PLUS,"+"));
                    break;
                case '-':
                    result.add(new Token(Token.Type.MINUS,"-"));
                    break;
				case '*':
					result.add(new Token(Token.Type.MUTIPLY,"*"));
					break;
                case '(':
                    result.add(new Token(Token.Type.LPAREN,"("));
                    break;
                case ')':
                    result.add(new Token(Token.Type.RPAREN,")"));
					break;
				case ' ':
					break;
                default:
                    StringBuilder sb = new StringBuilder("" + input.charAt(i));

                    for (int j = i + 1; j < input.length(); j++)
                    {
                        if (Character.isDigit(input.charAt(j)))
                        {
                            sb.append(input.charAt(j));
                            i++;
                        }
                        else
                        {
                            break;
                        }
                    }
                    
                    result.add(new Token(Token.Type.NUMBER, sb.toString()));

                    break;
            }
        }

        return result;
    }

    static Expression parse (ArrayList<Token> tokens)
    {
        if (tokens.size() == 1) return new DoubleExpression(Double.parseDouble(tokens.get(0).text));
        

        Expression left = null, right = null; 
        BinaryExpression.Type type = null;

        Expression tempExpression = null;

        ArrayList<Token> preExpression, sufExpression;

        for (int i = tokens.size() - 1; i >= 0; i--)
        {
            Token token = tokens.get(i);

            switch (token.type)
            {
                case PLUS:
                    preExpression = new ArrayList<Token>(tokens.subList(0, i));
                    sufExpression = new ArrayList<Token>(tokens.subList(i + 1, tokens.size()));
                    left = parse(preExpression);
                    right = tempExpression != null? tempExpression: parse(sufExpression);
                    type = BinaryExpression.Type.ADDITION;
                    i = -1;
                    break;
                case MINUS:
                    preExpression = new ArrayList<Token>(tokens.subList(0, i));
                    sufExpression = new ArrayList<Token>(tokens.subList(i + 1, tokens.size()));
                    left = parse(preExpression);
                    right = tempExpression != null? tempExpression: parse(sufExpression);
                    type = BinaryExpression.Type.SUBTRACTION;
                    i = -1;
                    break;
				case MUTIPLY:
					preExpression = new ArrayList<Token>(tokens.subList(0, i));
					sufExpression = new ArrayList<Token>(tokens.subList(i + 1, tokens.size()));
					left = parse(preExpression);
					right = tempExpression != null? tempExpression: parse(sufExpression);
					type = BinaryExpression.Type.MULTIPLY;
					i = -1;
					break;
                case RPAREN:
                    ArrayList<Token> subexpression = new ArrayList<>();
                    int rParenCount = 0;
                    for (int j = i - 1; j >= 0; j--)
                    {
                        if (tokens.get(j).type == Token.Type.RPAREN)
                        {
                            subexpression.add(0, tokens.get(j));
                            rParenCount++;
                        }
                        else if (tokens.get(j).type == Token.Type.LPAREN && rParenCount > 0)
                        {
                            subexpression.add(0, tokens.get(j));
                            rParenCount--;
                        }
                        else if (tokens.get(j).type == Token.Type.LPAREN && rParenCount == 0)
                        {
                            i = j;
                            break;
                        }
                        else
                        {
                            subexpression.add(0, tokens.get(j));
                        }
                    }
    
                    tempExpression = parse(subexpression);

                    break;
                
            }
        }

        //parenthesis without operators
        if (tempExpression != null && type == null) return tempExpression;

        BinaryExpression result = new BinaryExpression(left, right);
        result.type = type;
        return result;
    }
}