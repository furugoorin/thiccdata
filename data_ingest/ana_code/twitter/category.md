# Data Ingest: category 

This categorizes each tweet into their respective categories, which are defined in `DLMapper.java`.

`fakesubset.txt` is provided in this project as a subset of 344 tweets from the cleaned output obtained from `etl_code/twitter/src/main/java/Twitter`. See `README.md` in the root directory for instructions on how to obtain another random subset.

### Steps to run: 
1. Login to Peel 
2. Create directory in HDFS: 
   ```
   hdfs dfs -mkdir analytics 
   hdfs dfs -mkdir analytics/input 
   ```
3. On local, transfer `fakesubset.txt` to Peel 
   ```
   scp fakesubset.txt [netid]@peel.hpc.nyu.edu:/home/[netid]
   ```
4. On Peel, place input file, `fakesubset.txt`, to `analytics/input` directory in HDFS 
    ```
    hdfs dfs -put fakesubset.txt analytics/input
    ```
5. On Peel, run the program 
   ```
   hadoop jar faketweets-1.0-SNAPSHOT-jar-with-dependencies.jar category.DataLabel analytics/input analytics/output
   ```
6. To find output file: 
   ```
   hdfs dfs -ls analytics/output
   ```
7. To view contents of output file: 
   ```
   hdfs dfs -cat analytics/output/part-r-00000
   ```