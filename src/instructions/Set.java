package instructions;
import bonereader.Variables;
import bonereader.EquationEvaluator;
/*
 * I want this class to set a variable equal to an equation
 */
public class Set extends Instruction {
	
	String name;
	
	//This isn't a control structure so it doesn't need to do anything special to the reader;
	public Set(int i, String inst)
	{
		super(i, inst);
		this.name = this.getArg(0);
	}
	
	private String getEqn()
	{
		String output = "";
		String[] args = this.getArgs();
		for(int x = 1; x<args.length; x++)
		{
			output += args[x];
			//System.out.println(args[x]);
		}
		
		return output;
	}

	//Set the value of a variable
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		String eqn = getEqn();
		int value = EquationEvaluator.cheat(eqn);
		Variables.setVar(name, value);

	}

}
