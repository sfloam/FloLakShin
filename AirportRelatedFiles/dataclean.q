--CREATE THE TABLE
create external table nnumseat (n_num string, code string, seats int)
          row format delimited fields terminated by ','
          location '/user/cloudera/SeatInput/';

create external table flightdetail (month string, date string, carrier string, tailnum string, flightnum string, fromapt string, toapt string, cancelled int)
          row format delimited fields terminated by ','
          location '/user/cloudera/FlightInput/';

create external table airport (airport_name string, city string, state string, aircode string)
          row format delimited fields terminated by ','
          location '/user/cloudera/AirportInput/';

-- SEE THE TABLE COLUMNS
describe nnumseat;
describe flightdetail;
describe airport;


-- SELECT REQUIRED COLUMNS AND JOIN THE TABLES
create table temp as select month, date, tailnum, fromapt, toapt, seats from flightdetail, nnumseat where tailnum=n_num;
 create table flight_all as select month, date, tailnum, fromapt, toapt, seats, airport_name, city, state from temp, airport where toapt = aircode;


--SAVE THE OUTPUT ON HDFS
insert overwrite directory '/user/cloudera/flightoutput' select * from flight_all;
--SAVE THE OUTPUT ON LOCAL DIRECTORY
insert overwrite local directory '/home/cloudera/project/output/' row format delimited fields terminated by ',' select * from flight_all;


