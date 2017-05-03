import java.io.IOException;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import java.util.ArrayList;

public class DailyMapper
extends Mapper<LongWritable, Text, Text, Text> 
{
	private static final String YEARCHECK= "2016";
	@Override
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException 
	{
		
		String inputLine = value.toString();
		String updateSt = inputLine.trim();
		updateSt = inputLine.replaceAll("( )+"," ");
		String[] wdl = updateSt.split(" ");
		int leng = wdl.length;
	
		String supaKey = wdl[leng-1 ].replaceAll("-",",");
		
		supaKey+="," + wdl[leng-2] + "," + wdl[leng-3] + "," + wdl[leng-4];
		
		String data = "," + wdl[leng-5] + "," + wdl[leng-6] ;

		context.write( new Text(supaKey), new Text(data)); 
		
			
		//USC00046730,20160101,TMAX,128,,,7

		
	}
}
