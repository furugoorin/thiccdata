package Twitter;

import java.io.IOException;
import com.opencsv.CSVParser;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class FakeTweetsMapper 
    extends Mapper<LongWritable, Text, Text, NullWritable>{
    
    @Override
    public void map(LongWritable key, Text value, Context context)
        throws IOException, InterruptedException {
        
        String line = value.toString(); 
        
        CSVParser parser = new CSVParser();
        String[] fields = parser.parseLineMulti(line);

        if(fields.length != 3 || fields[2].equals("real")){
            return;
        } 

        context.write(new Text(fields[1]), NullWritable.get());
    }     
}
