
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class OccupancyMapper
  extends Mapper<LongWritable, Text, Text, Text> {

  
  @Override
  public void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {
    
    String s = value.toString();


    String [] arr = s.split(",");

    if (arr[0].equals("listing_id"))
    {
      continue;
    }
    
    String date = arr[1];
    
    if (!date.substring(0,7).equals("2016-04") && !date.equals("2016-05-01"))
    {
        return;
    }

    
    String available = arr[2];
    float price;

    if (arr.length == 4)
    {
        price = Float.parseFloat(arr[3].substring(1, arr[3].length()));
    }
    else
    {
      price = -1;
    }

    String k = "nyc_2016apr03_2016may01";
    String v;

    if (available.equals("f"))
    {
      v = "1" + "0";
    }
    else
    {
      v = "0" + Float.toString(price);
    }

    
    context.write(new Text(k), new Text(v));

  }
}
