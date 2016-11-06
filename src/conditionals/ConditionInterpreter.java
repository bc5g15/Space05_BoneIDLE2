package conditionals;

public class ConditionInterpreter {

	public static Conditional interpret(String s)
	{
		//I'm cheating, Still only one conditional for now.
		s = s.toLowerCase();
		
		//Should use regex for this, but I prefer this method.
		if(s.contains("not 0"))
		{
			String[] k = s.split("not 0");
			return new VarNotZero(k[0]);
		}
		else if(s.contains("equals"))
		{
			String[] k = s.split("equals");
			return new Equality(k[0], k[1]);
		}
		else if(s.contains("greater than"))
		{
			String[] k = s.split("greater than");
			return new GreaterThan(k[0], k[1]);
		}
		else if(s.contains("less than"))
		{
			String[] k = s.split("less than");
			return new LessThan(k[0], k[1]);
		}
		else
		{
			return new AlwaysFalse();
		}
	}
}
