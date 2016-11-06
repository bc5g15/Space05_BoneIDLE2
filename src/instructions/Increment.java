package instructions;

import bonereader.Variables;

public class Increment extends Instruction {
	private String name;

	public Increment(int instNum, String inst)
	{
		super(instNum, inst);
		this.name = this.getArg(0);
		if(name==null)
		{
			this.highlightError();
		}
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		if(name!=null)
		{
			Variables.incVar(name);
		}
	}

}
