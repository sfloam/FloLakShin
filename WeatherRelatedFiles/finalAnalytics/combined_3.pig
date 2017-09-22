

myWeatherData= LOAD 'piggyWeath/part-r-00000' USING PigStorage() AS
    (month:chararray, day:chararray, year:chararray, stationid:chararray,typeofdata:chararray, avgval:charArray, lat:charArray, longit:charArray, state:charArray, supakey:charArray);

glim = limit myWeatherData 10;

dump glim;

