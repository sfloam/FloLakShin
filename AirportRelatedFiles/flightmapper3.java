import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class flightmapper3
extends Mapper<LongWritable, Text, Text, IntWritable>
{
	
	@Override
	public void map(LongWritable key, Text value, Context context)
	throws IOException, InterruptedException
	{
    		String line = value.toString();
		line = value.toString();
		String[] strarray = line.split(",");
		String month = strarray[0];
		String day =strarray[1];
		int days = Integer.parseInt(day);
		
//	context.write(new Text(month), new Text(day));	
      context.write(new Text(month), new IntWritable(days));
    
  	}
}



