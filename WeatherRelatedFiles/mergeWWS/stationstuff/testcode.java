import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ArrayList;

public class testcode
{
    private static final String MISSING = "-9999";
    private static final String YEARCHECK= "2008";
    
    public static void main(String[] args)
    {
	File obj = new File("test.txt");
	try
	{
	    Scanner input = new Scanner(obj);
	    while (input.hasNext())
	    {
	

		String inputLine = input.nextLine();

		
			
		boolean t = false;
		int count = 1;


		String updateSt = inputLine.trim().replaceAll(" +"," ");
		System.out.println(updateSt);
		for(String w:inputLine.split("\\s",0)){
		    System.out.println(w);
		}
		    ;
		System.out.println(wdl.length());

		System.out.println(wdlal);
		String supaKey = wdl[9].replaceAll("-",",");
		
		supaKey+="," + wdl[8] + "," + wdl[7] + "," + wdl[6];
		
		String data = "," + wdl[4] + "," + wdl[5] ;
		
		System.out.println (supaKey + data);
	    
		/*dateData = dateData.trim().replaceAll(" +", " ");
 * 				dateData = dateData.replaceAll("[^0-9-]"," ");
 * 								dateData = dateData.trim().replaceAll(" +", " ");
 * 												
 * 																String [] dailyVals = dateData.split(" ");
 * 																				System.out.println(year);
 * 																								String weatherKey = "";
 * 																												
 * 																																int count = 0;
 * 																																				
 * 																																								if (country.matches("US")
 * 																																													&& year.matches(YEARCHECK)
 * 																																																		&& (type.matches("PRCP")|| type.matches("SNOW")
 * 																																																													|| type.matches("TMIN")
 * 																																																																								|| type.matches("TMAX")))
 *
 * 																																																																												{
 *
 * 																																																																																	for (String amt : dailyVals)
 * 																																																																																						{
 * 																																																																																												count+=1;
 * 																																																																																																		String amountToWrite = "";
 * 																																																																																																								if (count<10)
 * 																																																																																																														{
 * 																																																																																																																					weatherKey = month + "," + "0" + count + "," + year + "," + stationID + "," + type +",";
 * 																																																																																																																											}
 * 																																																																																																																																	else
 * 																																																																																																																																							{
 * 																																																																																																																																														weatherKey = month + "," + count + "," + year + "," + stationID + "," + type+",";
 * 																																																																																																																																																				}
 *
 * 																																																																																																																																																										if (type.equals("TMAX") || type.equals("TMIN")|| type.equals("PRCP"))
 * 																																																																																																																																																																{
 * 																																																																																																																																																																							amountToWrite =  "" +(Double.parseDouble(amt)*.1);
 * 																																																																																																																																																																													}
 * 																																																																																																																																																																																			else
 * 																																																																																																																																																																																									{
 * 																																																																																																																																																																																																amountToWrite = "" + amt;
 * 																																																																																																																																																																																																						}
 * 																																																																																																																																																																																																												System.out.println(weatherKey + " " + amountToWrite); // gets a 10th of amt
 * 																																																																																																																																																																																																																	}
 * 																																																																																																																																																																																																																					}*/
	    }
	}
	catch(FileNotFoundException e){}
    }
    
    
}


    

