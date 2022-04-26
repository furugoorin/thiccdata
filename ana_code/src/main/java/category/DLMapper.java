package category;

import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class DLMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    /*
     * Rows correspond to categories, entries in rows are keywords pertaining to category
    */
    private static String[][] keywords={
        {"lung", "mosquito"},
        {"vaccine", "cure", "drug", "trial"}, 
        {"president", "trump", "government", "cdc", "state", "prime minister"}, 
        {"death", "population", "percent", "%", "recovered", "cases", "diagnosed", "infected", "statistics"}, 
    };
    //contains names of categories
    private static String[] categories={
        "Virus itself",
        "Pharmaceutical Updates", 
        "Government", 
        "Statistics", 
    };
    
    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString().toLowerCase();
        boolean cont = true;
        //check if keywords are in string
        for(int i=0; i<keywords.length; i++){
            if(!cont) break;
            for(int j=0; j<keywords[i].length; j++){
                if(line.contains(keywords[i][j])){//categorize message
                    context.write(new Text(categories[i]), new IntWritable(1));
                    cont = false;
                    break;
                }
            }
        }
        if(cont) context.write(new Text("Virus Event"), new IntWritable(1));
    }
}
