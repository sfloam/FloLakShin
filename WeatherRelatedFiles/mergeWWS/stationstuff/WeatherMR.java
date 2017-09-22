import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
public class WeatherMR 
{
	public static void main(String[] args) throws Exception 
	{
		if (args.length != 2) 
		{
			System.err.println("Usage: WeatherMR <input path> <output path>");
			System.exit(-1);
		}
			Job job = new Job();
			job.setJarByClass(WeatherMR.class);
			job.setJobName("Weater MR");
			MultipleInputs.addInputPath(job, new Path(args[0]),TextInputFormat.class);
			FileOutputFormat.setOutputPath(job, new Path(args[1]));
			job.setMapperClass(WeatherMapperRev.class);
			job.setReducerClass(WeatherReducerRev.class);
			job.setNumReduceTasks(1);
			job.setOutputKeyClass(Text.class);
			job.setOutputValueClass(Text.class);
			System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}

