
-- To calculate the number of people flying into a specific city daily and monthly, the nearby airports were also considered.
-- SF : SFO, OAK, SJC
-- BOS: BOS, MHT, PVD
-- NASHVILLE: BNA
-- NY: JFK, EWR, LGA
-- LA: LAX, LGB, BUR, SNA, ONT

-- Select only airports that will be considered for analysis to reduce the mapreduce time
create table flightsub as select * from flight_all where toapt in ("SFO", "OAK", "SJC", "BOS", "MHT", "PVD", "BNA", "JFK", "EWR", "LGA", "TEB", "NYC", "LAX", "LGB", "BUR", "SNA", "ONT");

--NY daily
create table NYCdaily as select year, month, day, sum(seatcount) as passengers, city from flights_sub where toapt in ('JFK','EWR', 'LGA', 'TEB', 'NYC') group by year, month, day, city;

--LA daily
create table LAdaily as select year, month, day, city, sum(seatcount) as passengers from flights_sub where toapt in ('LAX','LGB', 'BUR', 'SNA', 'ONT') group by year, month, day, city;

--SF daily
create table SFdaily as select year, month, day, sum(seatcount) as passengers from flights_sub where toapt in ('SFO','OAK', 'SJC') group by year, month, day;

--NASHVILLE daily
create table NASHdaily as select year, month, day, sum(seatcount) as passengers from flights_sub where toapt in ('BNA') group by year, month, day;"

--BOSTON daily
create table BOSdaily as select year, month, day, sum(seatcount) as passengers from flights_sub where toapt in ('BOS', 'MHT', PVD') group by year, month, day;

--MONTHLY
create table Monthly as select year, month, toapt, sum(seatcount) as passengers, city from flights_sub group by year, month, toapt;


-- Find the year, month, day with the highest number of passengers 

create temporary table temp1 as select month, max(passengers) as passengers from NYCdaily group by month;
create table nycmax as select * from NYCdaily, temp1 where NYCdaily.passengers = temp1.passengers;

-- Find the year, month, day with the lowest number of passengers

create temporary table temp2 as select month, min(passengers) from NYCdaily group by month;
create table nycmin as select * from NYCdaily, temp2 where NYCdaily.passengers = temp2.passengers;


-- Find the details of average passengers

create temporary table temp3 as select month, avg(passengers) from NYCdaily group by month;
create table nycavg as select * from NYCdaily, temp3 where NYCdaily.passengers = temp3.passengers;


--to write into a file, execute the following format
hive -e "insert overwrite local directory '/home/cloudera/project/output2/' row format delimited fields terminated by ',' select year, month, day, sum(seatcount) as passengers from flights_sub where toapt in ('SFO','OAK', 'SJC') group by year, month, day;"

-- select the day the max temperature 
create temporary table wtemp as select month, max(value) from weather group by month;
create table weathermax as select * from weather, wtemp where weather.value = wtemp.value;

-- merge the weather data and the flight data based on the month/day
select * from nycmax, weathermax where nycmax.year = weathermax.year and nycmax.month = weathermax.month;


