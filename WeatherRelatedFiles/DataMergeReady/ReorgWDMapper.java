import java.io.IOException;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import java.util.ArrayList;

public class ReorgWDMapper
extends Mapper<LongWritable, Text, Text, Text> 
{
	private static final String YEARCHECK= "2016";
	@Override
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException 
	{
		
		String inputLine = value.toString();
		String [] wdArr = inputLine.split(",");
		int leng = wdArr.length;
	
	    	String precipVal = "NULL";
		String snowVal = "NULL";
		String tempMaxVal = "NULL";
		String tempMinVal = "NULL";

		for (int i = leng-1; i > 6; i-=2)
		{
		    
		    if (wdArr[i-1].equals("PRCP"))
		    {
			if (wdArr[i].matches("-999.9000000000001")||wdArr[i].matches("-9999"))
			{
			    precipVal = "NULL";
			}
			else
			{
			    precipVal = wdArr[i];
			}
		    }

		      
		    else if (wdArr[i-1].equals("SNOW"))
		    {
			if (wdArr[i].matches("-999.9000000000001")||wdArr[i].matches("-9999"))
			{
			    snowVal = "NULL";
			}
			else
			{
			    snowVal = wdArr[i];
			}
		    }
		    

		    else if (wdArr[i-1].equals("TMAX"))
		    {
			if (wdArr[i].matches("-999.9000000000001")||wdArr[i].matches("-9999"))
			{
			    tempMaxVal = "NULL";
			}
			else
			{
			    tempMaxVal = wdArr[i];
			}
		    }
		    
		    else if (wdArr[i-1].equals("TMIN"))
		    {
			if (wdArr[i].matches("-999.9000000000001")||wdArr[i].matches("-9999"))
			{
			    tempMinVal = "NULL";
			}
			else
			{
			    tempMinVal = wdArr[i];
			}
		    }

		}
		
		String month = wdArr[0];
		String day = wdArr[1];
		String year = wdArr[2];
		String station = wdArr[3];
		String lat = wdArr[4];
	    	String longit = wdArr[5];
		String state =  wdArr[6].trim();		
		
		if (state.matches("MA"))
		{
		    state = "BOS";
		}
		else if (state.matches("CA"))
		{
		    state = "SF";
		}
    		else if (state.matches("TN"))
		{
		    state = "NAS";
		}
		else if (state.matches("NY"))
		{
		    state = "NYC";
		}
		

    
		String supaKey = month + "," + day + "," + year + "," + station + "," + lat + "," + longit + "," + state + ",";
		
		
		String data =  precipVal + "," + snowVal + "," + tempMaxVal + "," + tempMinVal ;

		context.write( new Text(supaKey), new Text(data)); 
		
			
		//USC00046730,20160101,TMAX,128,,,7

		
	}
}
