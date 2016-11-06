package instructions;
import bonereader.Reader;
import bonereader.IfStack;
import bonereader.InstructionSet;

/*
 * Controls the reader reading the else branch
 */
public class Else extends Instruction {
	
	public Else(int i, String inst)
	{
		super(i, inst);
		InstructionSet falseBranch = IfStack.getRecentIf().getFalseBranch();
		
		//Else statements end the previous instruction set and define a new one.
		Reader.endInstructionSet();
		Reader.addInstructionSet(falseBranch);
		
	}

	@Override
	public void execute() {
		// Else statements don't do anything when they are run.

	}

}
