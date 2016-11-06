package bonereader;

import java.util.Stack;

public class Controller 
{
	private static Stack<InstructionSet> current = new Stack<InstructionSet>();
	
	//This value is referenced by the InstructionSet
	private static boolean highlightCode = true;
	
	public static void setHighlighted(boolean on)
	{
		highlightCode = on;
	}
	
	public static boolean isHighlighted()
	{
		return highlightCode;
	}
	
	public static void addSet(InstructionSet is)
	{
		current.push(is);
	}
	
	public static void removeSet()
	{
		if(current.size() > 1)
		{
			current.pop();
		}
	}
	
	public static void next()
	{
		current.peek().next();
	}
	
	public static void clear()
	{
		current.clear();
		Variables.Clear();
		Subroutines.clear();
		IfStack.clear();
	}
	
	public static boolean tryPop()
	{
		if(current.size() > 1)
		{
			current.pop();
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static boolean hasNext()
	{
		if(current.size() > 1)
		{
			return true;
		}
		else
		{
			return current.peek().hasNext();
		}
	}
	
	public static void fullRun()
	{
		while(hasNext())
		{
			next();
		}
	}
	
	public static void handleSimpleInstruction(String inst)
	{
		Reader.interpretSingleInstruction(inst);
	}
}
