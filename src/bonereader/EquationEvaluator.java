package bonereader;

import java.util.Stack;
import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;

import bonereader.Variables;

public class EquationEvaluator {
	
	private static String[] tokens = {"*", "+", "-", "/", "(", ")"};
	
	//Fill in the variables and add whitespace around the tokens.
	private static String precheck(String eqn)
	{
		String temp = Variables.insertVars(eqn);
		for(String item : tokens)
		{
			//temp = temp.replace(item, " "+ item+" ");
		}
		
		return temp;
	}
	
	public static int cheat(String eqn)
	{
		String properEqn = precheck(eqn);
		
		ScriptEngineManager mgr = new ScriptEngineManager();
		ScriptEngine engine = mgr.getEngineByName("JavaScript");
		int output = 0;
		try
		{
			output = (Integer)engine.eval(properEqn);
		}
		catch(Exception e)
		{
			System.out.println("Couldn't evaluate the expression!");
		}
		
		
		return output;
	}
	
	public static int evaluate(String eqn)
	{
		Stack<String> ops = new Stack<String>();
		Stack<Integer> vals = new Stack<Integer>();
		
		String vars = precheck(eqn);
		
		String[] bits = vars.split(" ");
		
		//This is all basically stolen from stack-overflow.
		for(String s : bits)
		{
			if(!s.equals(""))
			{
				if		(s.equals("(")); //Do nothing I guess;
				else if	(s.equals("+")) ops.push(s);
				else if (s.equals("-")) ops.push(s);
				else if (s.equals("*")) ops.push(s);
				else if (s.equals("/")) ops.push(s);
				else if(s.equals(")"))
				{
					String op = ops.pop();
					int v = vals.pop();
					if		(op.equals("+")) v = vals.pop() + v;
					else if (op.equals("-")) v = vals.pop() - v;
					else if (op.equals("*")) v = vals.pop() * v;
					else if (op.equals("/")) v = vals.pop() / v;
					vals.push(v);
				}
				else vals.push(Integer.parseInt(s));
			}
		}
		
		return vals.pop();
	}

}
