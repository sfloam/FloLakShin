import java.io.IOException;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
//public class WeatherReducer extends Reducer<Text, DoubleWritable, Text, DoubleWritable> {
public class WeatherReducerRev extends Reducer<Text, DoubleWritable, Text, Text> {
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
		String output = ","+count + "," + String.format("%.2f",maxValue) + "," + String.format("%.2f",minValue) + "," + String.format("%.2f",averageVal) + "\n";
		//context.write(key, new IntWritable(count), new DoubleWritable(maxValue),new DoubleWritable(minValue), new DoubleWritable(averageVal));
		context.write(key, new Text(output));//may  need to put new Text() for key
	}
}


/*
max
min
avg
number of items processed (for error checking)
	look into expanding contextwrite  to multiple fields
		example, context.write(key, new DoubleWritable(maxValue),
									new DoubleWritable(minVal), 
									new DoubleWritable(avgMax), 
									new DoubleWritable(avgMin),
									new IntWritable(itemsProcessedPerMonth))



*/
