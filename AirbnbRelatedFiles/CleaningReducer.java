import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.util.*;

public class CleaningReducer
  extends Reducer<Text, Text, Text, Text> 
  {
  
  @Override
  public void reduce(Text key, Iterable<Text> values,
      Context context)
      throws IOException, InterruptedException {

    char latest_version = '0';
    String v = "";

    // String [] key_arr = key.toString().split();

    // String city = key_arr[0];
    // String listing_id = key_arr[1];
    // String date = listing_id[2];

    for (Text value : values)
    {
      String val = value.toString();
      String [] arr = val.split(",");

      char this_version = arr[0].charAt(9);
      String available = arr[1];
      String price = arr[2];
      // String city = arr[0].substring(0,3);


      if (this_version > latest_version)
      {
        latest_version = this_version;
	
	if (available.equals("f"))
	{
		v = available;
	}
	else if (available.equals("t"))
	{
		v = available + ",$" + price;
	}

      }
      else if (this_version == latest_version)
      {
        System.err.println("VERSIONS ARE EQUAL - DUPLICATE PRESENT");
        System.exit(1);
      }

    }


    context.write(new Text(key.toString() + ","), new Text(v));


  }
}
