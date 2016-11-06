package bonereader;

import java.lang.reflect.*;
import java.util.Stack;
import instructions.Instruction;
import instructions.If;

public class Reader 
{
	private static Stack<InstructionSet> current = new Stack<InstructionSet>();
	
	private static int counter = 0;
	
	private static Instruction readInst(String inst)
	{
		String opcode = inst.trim().split(" ")[0];
		String addr = CSVReader.getClassLocation(opcode);
		Instruction i = new instructions.Pass(counter, "");
		
		try
		{
			//The test of reflection
			//System.out.println(address);
			Class c = Class.forName(addr);
			if(c!=null)
			{
				Constructor proper = c.getConstructor(new Class[]{int.class, String.class});
				i = (Instruction)proper.newInstance(counter, inst);
			}
			else
			{
				i = new instructions.Pass(counter, "");
			}
			
		}
		catch(Exception e)
		{
			//e.printStackTrace();
			i = new instructions.Pass(counter, "");
		}
		counter++;
		return i;
	}
	
	public static void readBigFile(String text)
	{
		String betterText = text.trim();
		//First of all, lets populate the opcodes
		CSVReader.scanCodes();
		InstructionSet base = new InstructionSet();
		Controller.addSet(base);
		current.push(base);
		//Assume text is not sanitised.
		String [] code = betterText.toLowerCase().split(";");
		//Because we want the size of the code array to use as an instruction marker, we work with that.
		for(int x = 0; x<code.length;x++)
		{

			//Clean out that whitespace.
			String inst = code[x].trim();
			//First bit will always be the opcode.
			String opcode = inst.split(" ")[0];
			//We want the parameters of the instruction too.
			//Just pass the whole instruction and start from index one. What the hell.
			String address = CSVReader.getClassLocation(opcode);
			
			try
			{
				//The test of reflection
				//System.out.println(address);
				Class c = Class.forName(address);
				if(c!=null)
				{
					Constructor proper = c.getConstructor(new Class[]{int.class, String.class});
					Instruction i = (Instruction)proper.newInstance(x, inst);
				}
				else
				{
					Instruction i = new instructions.Pass(x, "");
				}
				
			}
			catch(Exception e)
			{
				//e.printStackTrace();
				Instruction i = new instructions.Pass(x, "");
			}
			
			counter++;
			
		}
		
	}
	
	
	//DISCLAIMER: This shouldn't be used. It doesn't work.
	public static void interpretSingleInstruction(String inst)
	{
		if (current.size() <1) current.push(new InstructionSet());
		inst = inst.replace(";", "").trim();
		Instruction i = readInst(inst);
		i.execute();
		
	}
	
	
	public static void addInstruction(Instruction i)
	{
		current.peek().addInstruction(i);
	}
	
	public static void endInstructionSet()
	{
		if(current.size()>1) current.pop();
	}
	
	public static void addInstructionSet(InstructionSet is)
	{
		current.push(is);
	}
	
	//For use by instructions that shouldn't really be executed
	public static void removeInstruction(Instruction i)
	{
		current.peek().removeInstruction(i);
	}
	
	public static void clear()
	{
		current.clear();
		counter = 0;
	}
	
	//Need some extra functions for the IF/ELSE statements.
}
