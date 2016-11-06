package instructions;

import bonereader.Controller;
import bonereader.InstructionSet;
import bonereader.Reader;
import conditionals.Conditional;
import conditionals.ConditionInterpreter;

public class While extends Instruction {
	Conditional c;
	InstructionSet is;

	public While(int i, String inst)
	{
		super(i, inst);
		//Need to add a new instruction set to the reader.
		String temp = inst.replace("while", "").trim();
		this.c = ConditionInterpreter.interpret(temp);
		this.is = new InstructionSet();
		is.setCondition(c);
		Reader.addInstructionSet(is);
	}
	
	@Override
	public void execute() {
		// The instruction describes how to move to a new InstructionSet.
		if(c.evaluate())
		{
			Controller.addSet(is);
		}
	}

}
