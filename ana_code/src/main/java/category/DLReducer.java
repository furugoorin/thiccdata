package category;

import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class DLReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
    private IntWritable result = new IntWritable();
    
    @Override
    public void reduce(Text key, Iterable<IntWritable> values, Context context)throws IOException, InterruptedException {
        int sum = 0;
        //counts number of messages in each category
        for (IntWritable value : values) {
            sum += value.get();
        }
        this.result.set(sum);
        context.write(key, this.result);
    }
}
