package instructions;

import bonereader.Subroutines;
import bonereader.Reader;
import bonereader.InstructionSet;

/*
 * Defines a subroutines for the reader
 */
public class Define extends Instruction {
	
	private String name;
	
	public Define(int i, String inst)
	{
		super(i, inst);
		this.name = this.getArg(0);
		InstructionSet is = new InstructionSet();
		Reader.addInstructionSet(is);
		Subroutines.add(name, is);
	}

	@Override
	public void execute() {
		// This doesn't need to do anything

	}

}
