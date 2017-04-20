the dataclean.q hive script cleans the data related to the airports and the all flights to and from each airport in 2016. There are 3 separate files that data is collected from. The Airport file that contains details of each airport, the flight model file that contains data of the airplane model and the capacity and the flights data that contains data regarding which airlines, from and to airport, the month and day it flew etc.
The script joins the data based on the airplane tailnumber and the airport code and filters out all non required columns.
The columns of the final are month, day, tailnum, from airport, to airport, city, state and the number of passengers in each flight.
This will help us retrieve data of the number of flights into each city as well as the passengers flying into each city to be able to study the inlfuence on airbnb occupancy.


--CREATE THE FOLDERS
$ hdfs dfs -mkdir SeatInput
$ hdfs dfs -mkdir FlightInput
$ hdfs dfs -mkdir AirportInput

--ADD DATA TO FOLDERS
$ hdfs dfs -put nnum_seat.txt SeatInput
$ hdfs dfs -put 2016all.txt FlightInput
$ hdfs dfs -put airportcode.txt AirportInput

--VIEW THE FILES IN EACH FOLDER
$ hdfs dfs -cat SeatInput/nnum_seat.txt
$ hdfs dfs -cat FlightInput/2016all.txt
$ hdfs dfs -cat AirportInput/airportcodes.txt

--RUN THE HIVE SCRIPT
$hive -f /dataclean.q

--CHECK IF THE OUTPUT EXIST
$hdfs dfs -ls /user/cloudera/flightoutput
$hdfs dfs -cat /user/cloudera/flightoutput
