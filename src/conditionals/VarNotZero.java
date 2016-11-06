package conditionals;
import bonereader.Variables;

public class VarNotZero implements Conditional {
	private String name;
	
	public VarNotZero(String name)
	{
		this.name = name;
	}
	
	@Override
	public boolean evaluate() {
		// TODO Auto-generated method stub
		//System.out.print(Variables.GetVal(name));
		if(Variables.GetVal(name)==0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}

}
