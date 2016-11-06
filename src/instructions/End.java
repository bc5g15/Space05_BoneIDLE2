package instructions;
import bonereader.Reader;

public class End extends Instruction {
	
	public End(int i, String inst)
	{
		super(i, inst);
		
		//I need to tell the reader to stop reading into the current InstructionSet.
		//Reader.removeInstruction(this);
		Reader.endInstructionSet();
		
	}

	@Override
	public void execute() {
		//This function doesn't do anything
		//Controller.next();
	}

}
