
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
      return;
    }

    String city = arr[0];
    
    String date = arr[2];
    // String this_date = date.substring(0,4) + date.substring(5,7) + date.substring(8,10);
    
    String available = arr[3].replace("\t", "");

    float price;

    if (arr.length == 5)
    {
        price = Float.parseFloat(arr[4].substring(1, arr[4].length()));
    }
    else
    {
      price = -1;
    }

    String k = city + "," + date;
    String v = "";

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
