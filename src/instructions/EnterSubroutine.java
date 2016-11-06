package instructions;
import bonereader.Subroutines;
import bonereader.Controller;
import bonereader.InstructionSet;

public class EnterSubroutine extends Instruction {
	private String name;
	private InstructionSet is;
	
	public EnterSubroutine(int i, String inst)
	{
		super(i, inst);
		this.name = this.getArg(0);
		this.is = Subroutines.getSub(name);
	}

	@Override
	public void execute() {
		// Jump the controller to the relevant subroutine.
		is.resetPosition();
		Controller.addSet(is);

	}

}
