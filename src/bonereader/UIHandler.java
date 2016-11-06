package bonereader;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JTextArea;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.Highlight;
import javax.swing.text.Highlighter.HighlightPainter;

public class UIHandler {
	private static JTextArea code;
	private static Highlighter h1;
	private static HighlightPainter hp;
	private static HighlightPainter he;
	
	private static Highlight position;
	
	public static void setCodeText(JTextArea _code)
	{
		code = _code;
		h1 = code.getHighlighter();
		position = null;
		hp = new DefaultHighlighter.DefaultHighlightPainter(Color.yellow);
		he = new DefaultHighlighter.DefaultHighlightPainter(Color.red);
	}
	
	public static void highlightInstruction(int num)
	{
		ArrayList<Integer> semiPos = new ArrayList<Integer>();
		//Initial position of code is always 0;
		semiPos.add(0);
		
		//Ideally I'd like to remove an individual highlight rather than all of them.
		if(position!=null) h1.removeHighlight(position);
		
		String stuff = code.getText();
		int index = 0;
		
		while(index != -1)
		{
			index = stuff.indexOf(";", index+1);
			semiPos.add(index);
		}
		
		if(num < semiPos.size())
		{
			//hp = new DefaultHighlighter.DefaultHighlightPainter(Color.yellow);
			int p0 = semiPos.get(num);
			p0 = (p0==0)? -1 : p0;
			int p1 = semiPos.get(num+1);
			try
			{
				position = (Highlight)h1.addHighlight(p0+1, p1, hp);
			}
			catch(Exception e)
			{
				//I don't want anything to happen here.
			}
		}

	}
	
	public static void highlightError(int num)
	{
		ArrayList<Integer> semiPos = new ArrayList<Integer>();
		//Initial position of code is always 0;
		semiPos.add(0);
		
		String stuff = code.getText();
		int index = 0;
		
		while(index != -1)
		{
			index = stuff.indexOf(";", index+1);
			semiPos.add(index);
		}
		
		if(num < semiPos.size()+1)
		{
			//System.out.println(num);
			int p0 = semiPos.get(num);
			p0 = (p0==0)? -1 : p0;
			int p1 = semiPos.get(num+1);
			try
			{
				h1.addHighlight(p0+1, p1, he);
			}
			catch(Exception e)
			{
				//I don't want anything to happen here.
				System.out.println(e);
				System.out.println("Something is wrong!");
			}
		}
	}
	
}
