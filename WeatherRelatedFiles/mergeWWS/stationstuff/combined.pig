

myWeatherData= LOAD '365Output/part-r-00000' USING PigStorage(',') AS
    (month:chararray, day:chararray, year:chararray, stationid:chararray,typeofdata:chararray, avgval:charArray);


myWeatherDataTrimmed = foreach myWeatherData generate FLATTEN(
    (month,day, year,TRIM(stationid), typeofdata, avgval));


myWeatherDataFilt = filter myWeatherDataTrimmed by (month is not null);


myStationData = LOAD 'StationOutput/part-r-00000' USING PigStorage(',') AS 
    (stationKey:chararray, latitude:chararray, longitude:chararray, state:chararray);


myStationDataTrimmed = foreach myStationData generate FLATTEN(
    (TRIM(stationKey), latitude, longitude, state));


combinedData = JOIN myWeatherDataFilt by $3 LEFT OUTER, myStationDataTrimmed BY $0;



stDat = foreach combinedData generate $0, $1, $2, $3, $4, $5, $7,$8,$9;

stOr = Order stDat by $0 ASC, $2 ASC, $1 ASC, $7 ASC;



STORE stOr INTO 'piggyWeath';