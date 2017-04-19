import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class flightmapper2
extends Mapper<LongWritable, Text, Text, IntWritable>
{
	
	@Override
	public void map(LongWritable key, Text value, Context context)
	throws IOException, InterruptedException
	{
    		String line = value.toString();

		String[] strarray = line.split(",");
		String tailnum= strarray[3];
		String airport=strarray[6];
		//String month = line.substring(1,2);
		//String day = line.substring(3,4);
		
	

      context.write(new Text(airport), new IntWritable(1));
    
  	}
}



