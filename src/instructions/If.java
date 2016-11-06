package instructions;

import bonereader.Controller;
import bonereader.InstructionSet;
import conditionals.ConditionInterpreter;
import conditionals.Conditional;
import bonereader.Reader;
import bonereader.IfStack;

/*
 * The If control structure.
 */
public class If extends Instruction {
	
	private InstructionSet trueBranch;
	private InstructionSet falseBranch;
	private Conditional c;
	
	public If(int i, String inst)
	{
		super(i, inst);
		String temp = inst.replace("if ", "");
		c = ConditionInterpreter.interpret(temp);
		
		//The reader now switches to reading the true branch.
		trueBranch = new InstructionSet();
		Reader.addInstructionSet(trueBranch);
		
		//This is just for the else statement to use if it exists.
		falseBranch = new InstructionSet();
		IfStack.addIf(this);
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		if(c.evaluate())
		{
			Controller.addSet(trueBranch);
		}
		else
		{
			Controller.addSet(falseBranch);
		}
		

	}
	
	public InstructionSet getFalseBranch()
	{
		return this.falseBranch;
	}

}
