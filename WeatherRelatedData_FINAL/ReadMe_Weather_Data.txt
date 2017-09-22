Read Me:

Contents:

FinalWDCleanup, StationFolder, ParseWeatherFolder, WeatherData_Station_Merge


To use the programs in the folders listed above, please follow the instructions below:

1. Download the 20+ GB Weather Data Files from: ftp://ftp.ncdc.noaa.gov/pub/data/ghcn/daily/all/

2. Upload these files into HDFS under the name FloLakShin_Weather. You could also update the bash script that I wrote, parseExec, to meet your naming conventions. However, be sure to modify the pig file if you do this later on! By default the output for the MapReduce job in parseExec will put the parsed data into DailyParse.

3. Run bash script .parseExec. This bash script runs ParseWeatherMapper.java, ParseWeatherReducer.java, and ParseWeatherMR.java (job file). 

4. Create an HDFS folder called AllStations (or whatever you want (see step 2 for more information about customization).

5. Put the AllStations HDFS folder ghcnd-stations.txt. You can access this folder via ftp://ftp.ncdc.noaa.gov/pub/data/ghcn/daily.  This file contains a list of all weather stations around the globe and their GPS locations. 

6. Run stationExec. This bash script runs StationMapper.java, StationReducer.java, and StationMR.java (job file). The results from this execution will output into a folder on HDFS called StationOutput.

7. Go to WeatherData_Station_Merge folder. If you changed your folder names in the previous steps, make sure you fix them in Combined_1.pig and Combined_2.pig. These files will merge the weather station data with the station location information.

8. Run Combined_1.pig (note, this program will reference the DailyParse folder unless you made name modifications). This program will output its results into a folder on HDFS called ComboDaily.

9. Run Combined_2.pig (note, this will gather data from ComboDaily). This will output weather data for speicific cities in a clean format. Note, you can modify the coordinates to meet your needs in this file. By default, this program captures data for LA, Boston, Nashville, San Francisco, and New York. Combined_2.pig will output its results into a folder called FinalDaily on HDFS.

10. Go to the FinalWDCleanup folder.

11. Run reorgExec. This bash script runs ReorgWDMapper.java, ReorgWDReducer.java, and ReorgWDMR.java (job file). These programs make the data clean and organized by cities. This program is required in order to merge this data with the Airbnb and Flight data. The output will be placed in a folder called ReorgWD on HDFS. Note, the data for this MR program will come from FinalDaily's part files. 


12. See FinalDataProcessing folder for next steps.