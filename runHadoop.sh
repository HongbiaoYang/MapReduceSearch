#!/bin/bash

hadoop dfs -rmr /user/hyang22/wordcount/output
hadoop dfs -rmr /user/hyang22/wordcount/tmp
hadoop dfs -rmr /user/hyang22/wordcount/stopword
jar -cvf wordcount.jar -C out/production/mapred560/ .
#../bin/hadoop jar wordcount.jar org.myorg.WordCount /user/hyang22/wordcount/input /user/hyang22/wordcount/output
hadoop jar wordcount.jar WordCount /user/hyang22/wordcount/input /user/hyang22/wordcount/output
hadoop dfs -cp /user/hyang22/wordcount/output/part* output/
rm -rf index
hadoop dfs -get /user/hyang22/wordcount/output/part-r-00000 index
