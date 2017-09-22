import java.io.IOException;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import java.util.ArrayList;

public class WeatherMapperRev
extends Mapper<LongWritable, Text, Text, Text> 
{
	private static final String YEARCHECK= "2016";
	@Override
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException 
	{
		
		String line = value.toString();
	    	int strLen = line.length();
                String country = line.substring(0,2);
                String stationID = line.substring(0,11);
                String year = line.substring(11,15);
                String month = line.substring(15,17);
		String type = line.substring(17,21);
		String dateData = line.substring(21,line.length());
		
		dateData = dateData.trim().replaceAll(" +", " ");
		dateData = dateData.replaceAll("[^0-9-]"," ");
		dateData = dateData.trim().replaceAll(" +", " ");
		
		String [] dailyVals = dateData.split(" ");
		System.out.println(year);
		String weatherKey = "";
		

		int count = 0;
		if (country.matches("US") 
			&& year.matches(YEARCHECK)
			&& (type.matches("PRCP")|| type.matches("SNOW")
									|| type.matches("TMIN")
									|| type.matches("TMAX")))
									
		{
			
			for (String amt : dailyVals)
			{
				count+=1;
				String amountToWrite = "";
				if (count<10)
				{
					weatherKey = month + "," + "0" + count + "," + year + "," + stationID + "," + type +",";
				}
				else
				{
					weatherKey = month + "," + count + "," + year + "," + stationID + "," + type+",";
				}
				
				if (type.equals("TMAX") || type.equals("TMIN")|| type.equals("PRCP"))
				{
					amountToWrite =  "" +(Double.parseDouble(amt)*.1);
				}
				else
				{
					amountToWrite = "" + amt;
				}
				context.write( new Text(weatherKey), new Text(amountToWrite)); // gets a 10th of amt
			}
		}
			
		//USC00046730,20160101,TMAX,128,,,7

		
	}
}
