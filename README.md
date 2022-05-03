# Covid-19 Misinformation 
## Crystal Chu and Allen Li 
**NOTES FOR TWITTER:**
* Maven is used to build project JAR. Dependencies are located in `pom.xml`
* Before proceeding to run any of the programs, be sure to transfer the input files (see [Input data](#input-data)) to Peel


### Code Organization
* `/ana_code` - contains source code for analytics 
  * `/twitter`
    * `/src/main/java/category` - code for Twitter analytics
    * `/src/main/java/twitterfakesubset` - code for extraction of a subset (344) of tweets labeled as fake 
* `/data_ingest` - commands for code ingestion 
* `/etl_code` - ETL/cleaning 
  * `/twitter`
    * `/src/main/java/Twitter` - code for cleaning Twitter dataset 
* `/profiling_code` - profiling 
  * `/twitter`
    * `/src/main/java/Twitter` - code for profiling Twitter dataset
* `/screenshots` - screenshots for every step
  * `/ana_code `
    * `/twitter`
  * `/etl_code`
    * `/twitter`
  * `/profiling_code`
    * `/twitter`
  * `/twitterfakeset`

### Commands to run the programs 
Instructions located in `data_ingest`.

### Input data 
#### Twitter
* [result.csv](https://github.com/diptamath/covid_fake_news/blob/main/Results/result.csv) - CSV file that contains tweets labeled as "real" or "fake
  * transfer to Peel: 
  ```
  scp result.csv [netid]@peel.hpc.nyu.edu:/home/[netid]
  ```
  * this input data can also be found in HDFS
  ```
  hdfs dfs -ls dirToShareAccess
  ```
* `fakesubset.txt` - text file that contains a subset (344) of fake tweets. Obtained by using Hive queries on the cleaned output from `etl_code`
  * transfer to Peel: 
  ```
  scp fakesubset.txt [netid]@peel.hpc.nyu.edu:/home/[netid]
  ```

To obtain another random subset: 
1. Access Hive 
2. On Hive, create external table for all tweets labeled as fake
   ```
   create external table [choiceofname]([col_name] STRING) row format delimited fields terminated by ',' stored as textfile location '/user/[netid]/twitterfakeset/output';
   ```
3. On Hive, load data into table
   ```
   load data inpath '/user/[netid]/twitterfakeset/output/part-m-00000' into table [choiceofname]; 
   ```
4. On HDFS, Create desired directory to place subset output
   ```
   hdfs dfs -mkdir [dir_name]/output
   ```   
5. On Hive, overwrite newly-created directory with query results
   ```
   insert overwrite directory '/user/cc6019/[dir_name]/output' row format delimited fields terminated by ',' select * from [choiceofname] distribute by rand() sort by rand() limit 344;
   ```


