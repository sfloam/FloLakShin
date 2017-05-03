import java.io.IOException;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class AnalyticReducer extends Reducer<Text, Text, Text, Text> {
@Override
public void reduce(Text key, Iterable<DoubleWritable> values, Context context) 
		    throws 	 IOException, InterruptedException 
    {
	Double maxValue = -Double.MAX_VALUE;
	Double minValue = Double.MAX_VALUE;
	Double total = 0.0;
	Double averageVal = 0.0;
	int count = 0;
	for (DoubleWritable value : values) 
	{
	    maxValue = Math.max(maxValue, value.get());
	    minValue = Math.min(minValue, value.get());	
	    total+= value.get();
	    count +=1;
	}
	
	averageVal = total/count;
	String output = "Count:" + count + " MaxVal:" + String.format("%.2f",maxValue) + " MinVal:" + String.format("%.2f",minValue) + " Avg:" + String.format("%.2f",averageVal) + "\n";
	context.write(key, new Text(output));
    }
}

