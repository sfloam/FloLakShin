
import java.io.IOException;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import java.util.ArrayList;

public class StationMapperRev extends Mapper<LongWritable, Text, Text, Text> 
{

	@Override
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException 
	{
		
		String line = value.toString();
		line = line.replaceAll("\\s+"," "); 
		line = line.trim();
		String[] weatherDataList = line.split(" ");
		    
		String country = weatherDataList[0].substring(0,2);
		String station = weatherDataList[0];
		String latitutde = weatherDataList[1];
		String longitude = weatherDataList[2];
		String elevation = weatherDataList[3];
		String state = weatherDataList[4]; //not state for all stations

		
		//USC00046730_01_TMAX
		String myKey = station;
		String val = "";
		if (country.equals("US"))
		{
			val = "," + latitutde + "," + longitude + "," + state;
			
			context.write(new Text(station), new Text(val));
		}
		

	}
}
