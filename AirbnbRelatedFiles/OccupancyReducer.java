import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
// import org.apache.hadoop.conf.Configured;

public class OccupancyReducer
  extends Reducer<Text, Text, Text, Text> 
  {
  
  @Override
  public void reduce(Text key, Iterable<Text> values,
      Context context)
      throws IOException, InterruptedException {

    // Configuration conf = getConf();
    // conf.set("mapred.output.textoutputformat.separator", ",");


    String [] keyArr = key.toString().split(",");

    String city = keyArr[0].toUpperCase();
    String date = keyArr[1];

    String day = date.substring(0,4);
    String month = date.substring(5,7) ;
    String year = date.substring(8,10);
    
    int totalDays = 0;
    int totalPrices = 0;

    int daysOccupied = 0;
    float pricesSum = 0;
    for (Text value : values) 
    {
      String val = value.toString();
      if (val.charAt(0)=='1')
      {
        daysOccupied += 1;
      }
      else
      {
        totalPrices += 1;
        pricesSum += Float.parseFloat(val.substring(1, val.length()));
      }

      totalDays += 1;
    }


    float occupancy_rate = (float) daysOccupied / totalDays;
    float avg_price = pricesSum / totalPrices;

    String out_key = date + "," + day + "," + month + "," + year + "," + city + ",";


    String out_val = Float.toString(occupancy_rate) + "," + "$" + Float.toString(avg_price);

    context.write(new Text(out_key), new Text(out_val));
  }
}
