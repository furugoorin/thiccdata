import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import java.lang.StringBuilder;

public class DLMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    /*
     *   Rows correspond to categories, entries in rows are keywords pertaining to category
    */
    private static String[][] keywords={
        {"肺", "蚊子", "狗"},
        {"疫苗", "医", "药", "审"}, 
        {"总统", "川普", "政府", "CDC", "总理"}, 
        {"死亡", "人们", "百分", "%", "全复", "诊断", "感染", "总体"}, 
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
        String line = value.toString();
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
        if(cont) context.write(new Text("Virus event"), new IntWritable(1));
        
    }
}
