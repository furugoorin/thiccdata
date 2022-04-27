import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.conf.Configuration;

//to be run on cleaned dataset
public class DataLabel {
    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.err.println("Usage: DataLabel <input path> <output path>");
            System.exit(-1);
        }
        //Configuration conf = new Configuration();
        //returns a CSV
        //conf.set("mapred.textoutputformat.separator", ",");
        Job job = new Job();
        job.setJarByClass(DataLabel.class);
        job.setJobName("DataLabel");
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        job.setMapperClass(DLMapper.class);
        job.setNumReduceTasks(1);//added so that output is in 1 place
        job.setReducerClass(DLReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
    