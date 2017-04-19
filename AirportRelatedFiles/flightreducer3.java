import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class flightreducer3
  extends Reducer<Text, IntWritable, Text, IntWritable> 
{
  
  @Override
   public void reduce(Text key, Iterable<IntWritable> values, Context context)      throws IOException, InterruptedException 
          	{
    	    		int maxDay = Integer.MIN_VALUE;
				
			for(IntWritable value : values)
			{	 
		//		String inputValue= value.toString();
				maxDay = Math.max(maxDay, value.get());
			//	context.write(key, new Text(inputValue));
			//	context.write(key, new IntWritable(maxDay));
			}
			
			context.write(key,new IntWritable(maxDay));
		}			  	
}  
