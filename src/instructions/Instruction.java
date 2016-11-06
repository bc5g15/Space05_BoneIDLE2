package instructions;

import bonereader.UIHandler;
import java.util.ArrayList;
import bonereader.Reader;

/*
 * My abstract parent Instruction class
 * Holds all the commands I think every instruction will need
 */
public abstract class Instruction {
	protected int instNum;
	protected String instruction;
	
	private ArrayList<String> args;

	public Instruction(int instNum, String instruction)
	{
		this.instNum = instNum;
		this.instruction = instruction.trim();
		this.args = new ArrayList<String>();
		populateArgs();
		Reader.addInstruction(this);
	}
	
	private void populateArgs()
	{
		String[] temp = instruction.split(" ");
		for(int i = 1; i<temp.length; i++)
		{
			args.add(temp[i]);
		}
	}
	
	/*
	 * Returns arguments if they exist.
	 * Remember, each class is going to have to error check for null arguments.
	 */
	protected String getArg(int num)
	{
		if(args.size() > num)
		{
			return args.get(num);
		}
		else
		{
			UIHandler.highlightError(instNum);
			return null;
		}
	}
	
	protected String[] getArgs()
	{	
		String[] temp = instruction.split(" ");
		//The output array is always going to have 1 less value.
		String[] output = new String[temp.length-1];
		
		for(int x = 1; x<temp.length; x++)
		{
			output[x-1] = temp[x];
		}
		
		return output;
	}
	
	//Each instruction is responsible for highlighting itself.
	//Sort of. It just hands it off to the HighlightHandler
	public void highlightInst()
	{
		UIHandler.highlightInstruction(instNum);
	}
	
	//If there is a problem, highlight the bad code
	public void highlightError()
	{
		UIHandler.highlightError(instNum);
	}
	
	abstract public void execute();
	
}
