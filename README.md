MapReduceSearch
===============
Use Hadoop to do Map Reduce and create simple search engine

This project programs the Hadoop parallel data processing platform, developed by Apache.
The Hadoop Environment is configured on Hydra machine in the EECS department. The configured
Hadoop cluster consists of two paralle systems: the Hadoop Distributed File System (HDFS) and
the MapReduce framework. This project designs and implements an important component of an
online search engine - the capability for users to search full-text from a document. The input
of the file are a series of text files. The MapReduce-based algorithm is implemented to
calculate the inverted index, which maps words to their document and line number in the
document. Words are sorted by the frequency from high to low, and the top 1% words are
selected as stop words and are removed from the inverted index. In addition, a query program
is written which accepts a user-specified query (one or more words) and returns the document
name, the line number, and offset of the word in the line.

runHadoop.sh
---------------------------------------------------
Please make sure to put the whole folder under the ${HADOOP_HOME} path! Otherwise it may fail
because of not able to access "../bin/hadoop".
To run the program, type "sh runHadoop.sh". It will make a directory under Hadoop file system
/local_scratch/wordcount and put a folder "input/" containing series of text file as input 
files into Hadoop file system under /local_scratch/wordcount. The source code is then compiled
into java executable and executed. The output of inverted index is stored under 
/local_scratch/wordcount/output.

GenIndex.java
---------------------------------------------------
The java code that implements MapReduce application. It takes the text files under Hadoop file
system /local_scratch/wordcount/input, performs the MapReduce functions, and output Inverted
Index under /local_scratch/wordcount/output. Upon the completion of the program, this index is 
copied from the HDFS to local file for later use.

SearchEngine.java
---------------------------------------------------
This is a query program, which accepts a user-specified query (one or more words) and returns
the document  name, the line number, and offset of the word in the line. To test this program, 
use "java SearchEngine <keyword>" in console. For example, you can type "java SearchEngine destiny", 
and the expected output will be:

==============================================
Results for destiny:

As You Like It.txt,2696,7
The Tragedie of Othello the Moore of Venice.txt,2711,2
The Tragedy of Coriolanus.txt,2438,3
A Midsummer Night's Dream.txt,562,7

About 4 results,(88.0 milliseconds)

==============================================

The above message means this word "destiny" is found in 4 different files, and the corresponding 
file names and locations are also displayed. The running time of this query is also calculated.


Some Design Decisions
---------------------------------------------------
This project use 3 consecutive map/reduce pairs to perform the task. The 1st map/reduce include 2 static inner java class. The funcion is similar with the WordCount example, which is to count the occurence of each word in the input files. The 2nd map/reduce include 3 static inner java class, which is used to use the output of the 1st map/reduce and generate the stop words. The additional class is a Comparater used for sort the words by frequency from high to low. The 3rd map/reduce read the raw input files again, and generate the index of each word while at the same time ignoreing the stop words according to the output of the 2nd map/reduce pair. 
What's worth mentioning is the message passing among the map/reduce classes in the fully distributed hadoop setting. Since the map and reduce take place in different machines within the Hadoop cluster, the traditional way of passing message or sharing data is not guaranteed to be still valid. We tried the classic Java data sharing method, the Hadoop customized counter, and Hadoop configuration setting, to achieve this goal but the experiments on those attemps showed that those methods only work in Standalone Hadoop setting but failed in Fully distributed setting. Finally we resorted to the HDFS, by writing the shared data into a temp file in HDFS and read it out in other class, and this way proved to be feasible.





