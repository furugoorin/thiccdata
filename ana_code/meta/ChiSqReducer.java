import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.commons.math3.stat.inference.ChiSquareTest;

public class ChiSqReducer extends Reducer<Text, Text, Text, Text> {
    final int categories = 5;//may be changed later
    @Override
    public void reduce(Text key, Iterable<Text> values, Context context)throws IOException, InterruptedException {
        //populate arrays for test
        long[] col1 = new long[categories];
        long[] col2 = new long[categories];
        int counter = 0;
        for (Text value : values) {
            String[] vals = value.toString().split(" ");
            col1[counter] = Long.parseLong(vals[0]);
            col2[counter++] = Long.parseLong(vals[1]);
        }
        ChiSquareTest test = new ChiSquareTest();
        double chiStat = test.chiSquareDataSetsComparison(col1, col2);
        double pVal = test.chiSquareTestDataSetsComparison(col1, col2);
        context.write(new Text("Chi-squared statistic"), new Text(String.valueOf(chiStat)));
        context.write(new Text("P-value"), new Text(String.valueOf(pVal)));
        
    }
}
