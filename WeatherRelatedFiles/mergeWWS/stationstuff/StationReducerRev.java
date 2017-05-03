
import java.io.IOException;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class StationReducerRev extends Reducer<Text, Text, Text, Text> {


public void reduce(Text key, Text values, Context context) 
					throws 	 IOException, InterruptedException 
	{
		String line = values.toString();
		String[] stationData = line.split("-");
		String latitude = stationData[0];
		String longitude = stationData[1];
		String state = stationData[2];
		
		String output = "Station:" + key + " Latitude:" + latitude + " Longitude:" + longitude + " State:" + state + "\n";
		context.write(key, new Text(output));
	}
}
