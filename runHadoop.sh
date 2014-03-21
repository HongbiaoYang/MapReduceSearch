#!/bin/bash

../bin/hadoop dfs -mkdir /local_scratch/wordcount
../bin/hadoop dfs -put input /local_scratch/wordcount

../bin/hadoop dfs -rmr /local_scratch/wordcount/output
../bin/hadoop dfs -rmr /local_scratch/wordcount/tmp
../bin/hadoop dfs -rmr /local_scratch/wordcount/stopword
rm -rf classes/*.class
javac -classpath ../hadoop-core-1.2.1.jar:../lib/commons-cli-1.2.jar -d classes GenIndex.java
jar -cvf GenIndex.jar -C classes .
#../bin/hadoop jar wordcount.jar org.myorg.WordCount /user/hyang22/wordcount/input /user/hyang22/wordcount/output
../bin/hadoop jar GenIndex.jar GenIndex /local_scratch/wordcount/input /local_scratch/wordcount/output
#hadoop dfs -cp /user/hyang22/wordcount/output/part* output/
rm -rf index
../bin/hadoop dfs -get /local_scratch/wordcount/output/part-r-00000 index
javac SearchEngine.java
java -classpath . SearchEngine
