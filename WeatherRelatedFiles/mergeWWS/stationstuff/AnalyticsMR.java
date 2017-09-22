import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
public class AnalyticsMR{
	public static void main(String[] args) throws Exception 
	{
		if (args.length != 2) 
		{
			System.err.println("Usage: AnalyticsMR <input path> <output path>");
			System.exit(-1);
		}
			Job job = new Job();
			job.setJarByClass(AnalyticsMR.class);
			job.setJobName("Analytics MR");
			MultipleInputs.addInputPath(job, new Path(args[0]),TextInputFormat.class);
			FileOutputFormat.setOutputPath(job, new Path(args[1]));
			job.setMapperClass(AnalyticsMapper.class);
			job.setReducerClass(AnalyticsReducer.class);
			job.setNumReduceTasks(1);
			job.setOutputKeyClass(Text.class);
			job.setOutputValueClass(DoubleWritable.class);
			System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}

