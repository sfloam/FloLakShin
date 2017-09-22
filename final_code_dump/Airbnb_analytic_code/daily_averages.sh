#!/bin/bash
array=(nyc nas sfo lax nol)
for i in "${array[@]}"
do
	cd cleaning
	hdfs dfs -mkdir -p /user/ls4170/rbda_project/cleaning/$i/$i"_input"
	echo step1
	hdfs dfs -put $i/$i"_input"/* /user/ls4170/rbda_project/cleaning/$i/$i"_input"
	echo step2
	hadoop jar Cleaning.jar Cleaning /user/ls4170/rbda_project/cleaning/$i/$i"_input" /user/ls4170/rbda_project/cleaning/$i/$i"_output"
	echo step3
	hadoop fs -getmerge /user/ls4170/rbda_project/cleaning/$i/$i"_output"/ $i/$i"_output"/$i"_cleaned_data.csv"
	echo step4

	cp $i/$i"_output"/$i"_cleaned_data.csv" ../occupancy/$i/$i"_input/"
	echo step5



	cd ..
	echo step6

	cd occupancy
	echo step7

	hdfs dfs -mkdir -p /user/ls4170/rbda_project/occupancy/$i/$i"_input"
	echo step8

	hdfs dfs -put $i/$i"_input"/*.csv /user/ls4170/rbda_project/occupancy/$i/$i"_input"
	echo step9

	hadoop jar Occupancy.jar Occupancy /user/ls4170/rbda_project/occupancy/$i/$i"_input" /user/ls4170/rbda_project/occupancy/$i/$i"_output"
	echo step10

	hadoop fs -getmerge /user/ls4170/rbda_project/occupancy/$i/$i"_output/" $i/$i"_output"/$i"_cleaned_data.csv"
	echo step11


	cd ..
	echo step12

done




