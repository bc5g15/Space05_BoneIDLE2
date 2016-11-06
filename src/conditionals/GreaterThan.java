package conditionals;
import bonereader.EquationEvaluator;

public class GreaterThan implements Conditional {
	
	private String eqn1;
	private String eqn2;
	
	public GreaterThan(String first, String second)
	{
		this.eqn1 = first;
		this.eqn2 = second;
	}

	@Override
	public boolean evaluate() {
		// TODO Auto-generated method stub
		int first = EquationEvaluator.cheat(eqn1);
		int second = EquationEvaluator.cheat(eqn2);
		return (first>second);
	}

}
