import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class flightreducer
  extends Reducer<Text, Text, Text, Text> {
  
  @Override
   public void reduce(Text key, Iterable<Text> values, Context context)
          throws IOException, InterruptedException 
          	{
    	    		String inputValue = "";
			for(Text value : values)
			{	 
				inputValue=key.toString();
				if(inputValue.equals("JFK"))
				{	
				context.write(new Text(inputValue),new Text(value));
				}			//	 String inputValue =values.toString();
          	    	}
	
          	}    							  		}
  
