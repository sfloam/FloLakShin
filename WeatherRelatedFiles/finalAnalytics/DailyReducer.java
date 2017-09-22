import java.io.IOException;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class DailyReducer extends Reducer<Text, Text, Text, Text> {

public void reduce(Text key, Iterable<Text> values, Context context) 
					throws 	 IOException, InterruptedException 
{
   String comboDat = ""; 
   for (Text s : values)
   {
	
	comboDat+=s;
   }
	

  
	context.write(new Text(key), new Text(comboDat)); 
}

}

