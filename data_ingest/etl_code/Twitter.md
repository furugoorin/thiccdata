# Data Ingest: Twitter

This cleans the Twitter dataset so that it removes multi-line tweets that may cause issues. 

### Steps to run: 
1. Run command at `submission/etl_code/twitter`
   ```
   mvn clean package assembly:single 
   ```
   This creates a JAR file. Be sure to use the one named with `jar-with-dependencies`
2. Login to Peel 
3. Create directory in HDFS: 
   ```
   hdfs dfs -mkdir etl
   hdfs dfs -mkdir etl/input 
   ```
4. On local, transfer JAR file to Peel 
   ```
   scp /target/etl_code-1.0-SNAPSHOT-jar-with-dependencies.jar [netid]@peel.hpc.nyu.edu:/home/[netid]
   ```
5. On Peel, place input file, `result.csv`, to `etl/input` directory in HDFS 
    ```
    hdfs dfs -put result.csv etl/input
    ```
6. On Peel, run the program 
   ```
   hadoop jar etl_code-1.0-SNAPSHOT-jar-with-dependencies.jar Twitter.Clean etl/input etl/output
   ```
7. To find output file: 
   ```
   hdfs dfs -ls etl/output
   ```
8. To view contents of output file: 
   ```
   hdfs dfs -cat etl/output/part-m-00000
   ```