package instructions;

import bonereader.Variables;

/*
 * Very easy. Simply decrement the single variable parameter;
 */
public class Decrement extends Instruction {
	String name;
	
	public Decrement(int instNum, String inst)
	{
		super(instNum, inst);
		this.name = this.getArg(0);
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		if(this.name!=null)
		{
			Variables.decVar(name);
		}

	}

}
