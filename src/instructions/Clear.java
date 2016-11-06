package instructions;

import bonereader.Variables;

/*
 * Clears a variable for further use
 */
public class Clear extends Instruction {

	private String name;
	
	public Clear(int instNum, String instruction)
	{
		super(instNum, instruction);
		
		//First (only) argument is the name of the variable.
		this.name = this.getArg(0);
		if(this.name == null) this.highlightError();
	}
	
	@Override
	public void execute() {
		//If our parameter is null, just print an error and abandon it.
		if(name==null)
		{
			System.out.printf("%03d: Clear instruction needs a valid argument!\n", instNum);
			this.highlightError();
			return;
		}
		
		//Otherwise, just use the variable handler.
		Variables.clrVar(name);
		
	}

}
