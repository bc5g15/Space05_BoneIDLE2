package bonereader;

import java.util.ArrayList;

import conditionals.Conditional;
import instructions.Instruction;

public class InstructionSet {
	private Conditional c;
	private ArrayList<Instruction> inst;
	private int currentInst;
	
	
	public void setCondition(Conditional c)
	{
		this.c = c;
	}
	
	public void clearCondition()
	{
		this.c = null;
	}
	
	public InstructionSet()
	{
		this.inst = new ArrayList<Instruction>();
		this.currentInst = 0;
	}
	
	public void next()
	{
		if(currentInst < inst.size())
		{
			inst.get(currentInst).execute();
			if(Controller.isHighlighted()) inst.get(currentInst).highlightInst();
			currentInst++;
			
		}
		else
		{
			CheckEnd();
		}
	}
	
	private void CheckEnd()
	{
		if(c != null)
		{
			//If looping condition is true
			if(c.evaluate())
			{
				//Reset instruction counter;
				currentInst = 0;
				this.next();
			}
			else
			{
				//Otherwise pop this off of the controller stack
				Controller.removeSet();
				Controller.next();
			}
		}
		else
		{
			//Try and pop this off of the stack if we hit the end.
			//This is how subroutines and if statements function.
			//This if is specially to check if we have hit the end of the program.
			if(Controller.tryPop())
			{
				Controller.next();
				
			}
		}
	}
	
	public void addInstruction(Instruction i)
	{
		this.inst.add(i);
	}
	
	//Only for use by the reader
	public void removeInstruction(Instruction i)
	{
		this.inst.remove(i);
	}
	
	public void resetPosition()
	{
		this.currentInst = 0;
	}
	
	public boolean hasNext()
	{
		return (currentInst < inst.size());
	}
}
