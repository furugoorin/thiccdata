# Data Ingest: Twitter

This provides the number of records in the Twitter dataset before and after cleaning.

**Note**: The number of records after cleaning differs from before cleaning due to the removal of multi-line tweets (lines that do not fulfill the 3 columns requirement).

## Before cleaning
### Steps to run: 
1. Run command at `submission/profiling_code`
   ```
   mvn clean package assembly:single 
   ```
   This creates a JAR file. Be sure to use the one named with `jar-with-dependencies`
2. Login to Peel 
3. Create directory in HDFS: 
   ```
   hdfs dfs -mkdir profiling
   hdfs dfs -mkdir profiling/input 
   ```
4. On local, transfer JAR file to Peel 
   ```
   scp /target/profiling_code-1.0-SNAPSHOT-jar-with-dependencies.jar [netid]@peel.hpc.nyu.edu:/home/[netid]
   ```
5. On Peel, place input file, `result.csv`, to `profiling/input` directory in HDFS 
    ```
    hdfs dfs -put result.csv profiling/input
    ```
6. On Peel, run the program 
   ```
   hadoop jar profiling_code-1.0-SNAPSHOT-jar-with-dependencies.jar Twitter.CountRecs etl/input profiling/output_before_clean
   ```
7. To find output file: 
   ```
   hdfs dfs -ls profiling/output_before_clean
   ```
8. To view contents of output file: 
   ```
   hdfs dfs -cat profiling/output_before_clean/part-r-00000
   ```

## After cleaning
### Steps to run: 

1. On Peel, run the program 
   ```
   hadoop jar profiling_code-1.0-SNAPSHOT-jar-with-dependencies.jar Twitter.CountRecs etl/output profiling/output_after_clean
   ```
2. To find output file: 
   ```
   hdfs dfs -ls profiling/output_after_clean
   ```
3. To view contents of output file: 
   ```
   hdfs dfs -cat profiling/output_after_clean/part-r-00000
   ```

