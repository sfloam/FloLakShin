

myWeatherData= LOAD '365Output/part-r-00000' USING PigStorage(',') AS (month:chararray, typeofdata:chararray, stationid:chararray, count:chararray, maxval:chararray, minval:chararray, avgval:chararray);
myWeatherDataTrimmed = foreach myWeatherData generate FLATTEN((month, typeofdata, TRIM(stationid), count, maxval, minval, avgval));
myWeatherDataFilt = filter myWeatherDataTrimmed by (month is not null);

myStationData = LOAD 'StationOutput/part-r-00000' USING PigStorage(',') AS (stationKey:chararray, latitude:chararray, longitude:chararray, state:chararray);
myStationDataTrimmed = foreach myStationData generate FLATTEN((TRIM(stationKey), latitude, longitude, state));

combinedData = JOIN myWeatherDataFilt by $2 LEFT OUTER, myStationDataTrimmed BY $0;

describe combinedData;
stDat = foreach combinedData generate $0, $1, $2, $3, $4, $5, $6, $8, $9 , $10;

stOr = Order stDat by $0 ASC, $2 ASC, $1 ASC, $9 ASC;



STORE stOr INTO 'piggyWeath';
