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
document. Words with frequency higher than 1% of the total word number in all the files are
selected as stop words and are removed from the inverted index. In addition, a query program
is written which accepts a user-specified query (one or more words) and returns the document
name, the line number, and offset of the word in the line.

runHadoop.sh
To run the program, type "sh runHadoop.sh". It will make a directory under Hadoop file system
/local_scratch/wordcount and put a series of text file as input files into Hadoop file system
under /local_scratch/wordcount. The source code is then compiled into java executable and
executed. The output of inverted index is stored under /local_scratch/wordcount/output.

GenIndex.java
The java code that implements MapReduce application. It takes the text files under Hadoop file
system /local_scratch/wordcount/input, performs the MapReduce functions, and output Inverted
Index under /local_scratch/wordcount/output.

SearchEngine.java
This is a query program, which accepts a user-specified query (one or more words) and returns
the document  name, the line number, and offset of the word in the line.
