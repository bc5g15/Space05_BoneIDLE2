package bonereader;

import instructions.If;
import java.util.Stack;

public class IfStack {
	private static Stack<If> ifs = new Stack<If>();
	
	public static void addIf(If inst)
	{
		ifs.push(inst);
	}
	
	public static If getRecentIf()
	{
		return ifs.pop();
	}
	
	public static void clear()
	{
		ifs.clear();
	}
}
