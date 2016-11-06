package boneIDLE;
import bonereader.Reader;

public class TextSender {
	
	/*
	 * This is what will allow comments.
	 */
	public static void sendText(String text)
	{
		String output = removeComments(text);
		Reader.readBigFile(output);
		
	}
	
	//Removes the single-line comments
	private static String removeComments(String text)
	{
		//Split it by line.
		String output = "";
		String[] lines = text.split("\n");
		
		for(String item : lines)
		{
			if(item.contains("#"))
			{
				int i = item.indexOf("#");
				output += item.substring(0, i);
			}
			else
			{
				output += item;
			}
		}
		
		return output;
	}

}
