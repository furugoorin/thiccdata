# Data Ingest: twitterfakesubset

This obtains a subset of tweets labeled as fake from the cleaned output of `etl_code`.

### Steps to run: 
1. run command at `submission/ana_code/twitter`
   ```
   mvn clean package assembly:single
   ```
   This creates a JAR file. Be sure to use the one named with `jar-with-dependencies`
2. Login to Peel
3. Create directory in HDFS: 
   ```
   hdfs dfs -mkdir twitterfakeset
   hdfs dfs -mkdir twitterfakeset/input
   ```
4. On local, transfer JAR file from to Peel 
   ```
   scp /target/faketweets-1.0-SNAPSHOT-jar-with-dependencies.jar [netid]@peel.hpc.nyu.edu:/home/[netid]
   ```
5. On Peel, copy output of cleaned dataset from `etl/output/part-m-00000`. This will be used as the input file.
   ```
   hdfs dfs -copyToLocal etl/output/part-m-00000
   ```
6. On Peel, place input file, `part-m-00000` to `twitterfakeset/input` directory in HDFS 
    ```
    hdfs dfs -put fakesubset.txt twitterfakeset/input
    ```
7. On Peel, run the program 
   ```
   hadoop jar faketweets-1.0-SNAPSHOT-jar-with-dependencies.jar Twitter.FakeTweets twitterfakeset/input twitterfakeset/output
   ```
8. To find output file: 
   ```
   hdfs dfs -ls twitterfakeset/output
   ```
9.  To view contents of output file: 
   ```
   hdfs dfs -cat twitterfakeset/output/part-m-00000
   ```