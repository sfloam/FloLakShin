

myWeatherData= LOAD 'ComboDaily/*' USING PigStorage(',');
    /*AS (month:chararray, day:chararray, year:chararray, stationid:chararray,latitutde:chararray, longitude:charArray,state:chararray,typeofdata:chararray  );*/


myWeatherDataFilt = filter myWeatherData by
		     ((TRIM($6) Matches  '.*NY.*') 
		    OR (TRIM($6) MATCHES '.*CA.*') 
		    OR (TRIM($6) MATCHES '.*MA.*') 
		    OR  (TRIM($6) MATCHES '.*TN.*')) ;

/*nyc 	40.712, -74.005
  bo 	42.3601, -71.0589
  sf	37.7749, -122.4194
  nash	36.1627, -86.7816
  la    34.0522342,-118.2436849
*/

myGPSFilt = filter myWeatherDataFilt by (


/*ny*/    ((40.6<$4) and ($4<40.71) AND (-74.2 < $5)  and ($5 <-73.0))or
/*bo*/    ((42.1<$4) and ($4<42.5) AND (-72.0 < $5)  and ($5 < -70.0))or
/*sf*/    ((37.2<$4) and ($4<38.9) AND (-123.0 < $5)  and ($5 < -122.0))or
/*la*/	  ((33.9<$4) and ($4<34.2) AND (-118.4< $5)   and ($5 <-118.2)) or
/*nash*/  ((36.1<$4) and ($4<36.26) AND (-87.0 < $5)  and ($5 < -86.0))
  

);

STORE myGPSFilt INTO 'FinalDaily' USING PigStorage(',');
