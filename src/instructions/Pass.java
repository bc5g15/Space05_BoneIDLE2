package instructions;

/*
 * The Pass command, only summoned if something went wrong.
 * This will serve as a template of all future instructions.
 */
public class Pass extends Instruction {
	
	//Sadly, all of my instructions will need something like this.
	//All of this will happen when the instruction is created by the reader.
	public Pass(int instNum, String code)
	{
		super(instNum, code);
		this.highlightError();
	}

	
	@Override
	//All of this will happen when the instruction is run by the controller.
	public void execute() {
		// TODO Auto-generated method stub
		System.out.println("Bad! Running an empty instruction at " + instNum);
		this.highlightError();

	}

}
