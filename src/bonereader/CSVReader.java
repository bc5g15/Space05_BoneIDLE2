package bonereader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import java.util.HashMap;

/*
 * For reading the CSV file in which I store my instructions.
 */
public class CSVReader {
	private static HashMap<String, String> opcodes = new HashMap<String, String>();
	
	public static void scanCodes()
	{
		try
		{
			//There we go!
			//Bloody hell, this took forever
			Scanner sc = new Scanner(new File("opcodes/file.csv"));
			while(sc.hasNextLine())
			{
				String line = sc.nextLine();
				String[] codes = line.split(",");
				opcodes.put(codes[0], codes[1]);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public static String getClassLocation(String code)
	{
		if(opcodes.containsKey(code))
		{
			return opcodes.get(code);
		}
		else
		{
			//Return this blank command if you don't recognise the command
			return opcodes.get("pass");
		}
	}
	
//	public static void getCodes()
//	{
//		String file = "/opcodes/codes.csv";
//		String line = "";
//		String cvsSplitBy = ",";
//	}

}
