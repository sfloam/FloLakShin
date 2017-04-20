
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;



public class CleaningMapper
  extends Mapper<LongWritable, Text, Text, Text> {

 
  
  @Override
  public void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {

    final int MAXDATE = 20170331;
 
    final int MINDATE =20160402;
    
    String s = value.toString();


    String [] arr = s.split(",");

    if (arr[0].equals("listing_id"))
    {
      return;
    }

    String listing_id = arr[0];
    
    String date = arr[1];
    
    String this_date = date.substring(0,4) + date.substring(5,7) + date.substring(8,10);

    if (Integer.parseInt(this_date)<MINDATE || Integer.parseInt(this_date)>MAXDATE)
    {
      return;
    }

    String available = arr[2];
    Double price;



    if (arr.length == 4)
    {
        price = Double.parseDouble(arr[3].substring(1, arr[3].length()));
    }
    else
    {
      price = (double)-1.0;
    }
    
    FileSplit fileSplit = (FileSplit)context.getInputSplit();
    String filename = fileSplit.getPath().getName();

    String city = filename.substring(0, 3);

    String k = city + "," + listing_id + "," + date;
    String v = filename + "," + available + "," + price;

    context.write(new Text(k), new Text(v));
    

  }
}
