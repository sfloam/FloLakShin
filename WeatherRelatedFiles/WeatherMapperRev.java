import java.io.IOException;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import java.util.ArrayList;

public class WeatherMapperRev
extends Mapper<LongWritable, Text, Text, DoubleWritable> 
{
	private static final String MISSING = "-9999";
	private static final String YEARCHECK= "2016";
	@Override
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException 
	{
		
		//tmax tmin snow prcp
		String line = value.toString();
		int strLen = line.length();
		String[] weatherDataList =line.split(",");
		
		String country = weatherDataList[0].substring(0,2);
		String networkID = weatherDataList[0].substring(3, weatherDataList[0].length());
		String date = weatherDataList[1];
		String year = date.substring(0,4);
		String month = date.substring(4,6);
		String type = weatherDataList[2];
		String amount = weatherDataList[3];
		String mflag = weatherDataList[4];
		String qflag = weatherDataList[5];
		String sflag = weatherDataList[6];
		int bCount = 0;
		int Dcount = 0;
		int invalidValueCount = 0;
		
		//USC00046730_01_TMAX
		String myKey = month + "," + type  + "," +  weatherDataList[0];
		
		double amountToWrite = 0.0;
		
		//USC00046730,20160101,TMAX,128,,,7,0700
		
	
		if (type.equals("TMAX") || type.equals("TMIN")|| type.equals("PRCP"))
		{
			amountToWrite = (Double.parseDouble(amount)*.1); // gets a 10th of amt
		}
		else
		{
			amountToWrite = Double.parseDouble(amount);
		}
		
		
		if (!amount.matches(MISSING) 
			&& country.matches("US") 
			&& year.matches(YEARCHECK)
			&& (type.matches("PRCP")|| type.matches("SNOW")
									|| type.matches("TMIN")
									|| type.matches("TMAX")))
		{
			context.write(new Text(myKey), new DoubleWritable(amountToWrite));
		}
	}
}
