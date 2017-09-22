

myWeatherData= LOAD 'DailyParse/*' USING PigStorage(',') AS
    (month:chararray, day:chararray, year:chararray, stationid:chararray,typeofdata:chararray, avgval:charArray);


myWeatherDataTrimmed = foreach myWeatherData generate FLATTEN(
    (month,day, year,TRIM(UPPER(stationid)),UPPER(typeofdata), avgval));


myWeatherDataFilt = filter myWeatherDataTrimmed by (month is not null);


myStationData = LOAD 'StationOutput/part-r-00000' USING PigStorage(',') AS 
    (stationKey:chararray, latitude:chararray, longitude:chararray, state:chararray);


myStationDataTrimmed = foreach myStationData generate FLATTEN(
   (UPPER(TRIM(stationKey)), latitude, longitude, UPPER(state)));


combinedData = JOIN myWeatherDataFilt by $3 LEFT OUTER, myStationDataTrimmed BY $0;



stDat = foreach combinedData generate $0, $1, $2, $3, $4, $5, $7,$8,$9;


stOr = Order stDat by $0 ASC, $2 ASC, $1 ASC, $7 ASC;



myWeathUpd = foreach stOr generate
    ',',TRIM($4),',',TRIM($5), CONCAT($0, ',', $1,',',$2,',',$3,',',$6,',',$7,',',$8) AS (supKey:charArray);



myWG = GROUP myWeathUpd by supKey;


combinedDailyData = foreach myWG generate group as supaKey, BagToString(myWeathUpd);

combinedWOGK = foreach combinedDailyData generate supaKey,REPLACE($1,supaKey,'');

combinedWOSpace = foreach combinedWOGK generate supaKey, REPLACE($1,'^([A-Za-z0-9-]||.||,)','');

combinedWODash = foreach combinedWOSpace generate supaKey, REPLACE($1,'_','');


/*
combinedRMExcessSpace= foreach combinedWODash generate supaKey, REPLACE($1,'( )+','');

combinedRMExcessCommas = foreach combinedRMExcessSpace generate supaKey, REPLACE($1,',,',',');

*/

STORE combinedWODash INTO 'ComboDaily';
