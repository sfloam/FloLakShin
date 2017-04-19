---I have 1 2016all data set that has data regarding all flights that flew in 2016 jan 1st to 2016 dec 31st. It consist of month (1 to 12), date (1 to 31), airline (Delta, United, American airlines...), flight tail num (airplane model number), flight num (unique flight number), from airport, to airport, cancelled (0 for not cancelled, 1 for cancelled)

---I have created 3 mapReduce files to profile the data for:

a. Collecting the flight number data for all flights flying into JFK airport. This will be used to filter different airports/cities later on in the project to find correlation with other data set.

b.checking the number of flights flying into a specific airports in 2016. The amount of flights to an airport will be used to determine influence to occupancy

c.checking if the date format of each month is correct. the max date of Jan should be 31, Feb 29 (leap year), Mar 31 and so on. This checks for any errors

---Files

data set: 2016all.txt

a. flight numbers for JFK
flight.java
flightmapper.java
flightreducer.java

b. number of flights
flight2.java
flightmapper2.java
flightreducer2.java

c. max date for each month, check for error
flight3.java
flightmapper3.java
flightreducer3.java

---copying data into HDFS
1. Compiling the Java code:

yarn classpath
javac -classpath `yarn classpath` -d . flightmapper.java
javac -classpath `yarn classpath` -d . flightreducer.java
javac -classpath `yarn classpath`:. -d . flight.java

2. Create jar file
jar -cvf flight.jar *.class

3. create folder, put and check data file into HDFS

hdfs dfs -mkdir /user/cloudera/project
hdfs dfs -put 2016all.txt /user/cloudera/project
hdfs dfs -cat /user/cloudera/project/2016all.txt

4. Run your MapReduce program

hadoop jar flight.jar flight /user/cloudera/project/2016all.txt /user/cloudera/project/output

5. Verify that the program ran and the results are correct
hdfs dfs -ls /user/cloudera/project/output
hdfs dfs -cat /user/cloudera/project/output/part-r-00000


