package bonereader;
import java.util.HashMap;

/*
 * A big static class that is used for storing subroutines.
 * 
 */
public class Subroutines {
	private static HashMap<String, InstructionSet> subs = new HashMap<String, InstructionSet>();
	
	public static InstructionSet getSub(String name)
	{
		return subs.get(name);
	}
	
	public static void add(String name, InstructionSet is)
	{
		subs.put(name, is);
	}
	
	public static void clear()
	{
		subs.clear();
	}
	
}
