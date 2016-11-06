package conditionals;
import bonereader.EquationEvaluator;

public class LessThan implements Conditional {
	private String eqn1;
	private String eqn2;
	
	public LessThan(String eqn1, String eqn2)
	{
		this.eqn1 = eqn1;
		this.eqn2 = eqn2;
	}

	@Override
	public boolean evaluate() {
		// TODO Auto-generated method stub
		int first = EquationEvaluator.cheat(eqn1);
		int second = EquationEvaluator.cheat(eqn2);
		return (first<second);
	}

}
