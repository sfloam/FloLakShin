import java.io.IOException;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class WeatherReducerRev extends Reducer<Text, Text, Text, Text> {

public void reduce(Text key, Text values, Context context) 
					throws 	 IOException, InterruptedException 
	
	{
	    context.write(new Text(key), new Text(values));
	}
}
